package com.cnfantasia.server.api.access.web.socket;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.cnfantasia.server.api.access.codec.SocketBeanUtil;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message.MessageType;
import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;
import com.cnfantasia.server.api.access.codec.ake.bean.request.CarInReq;
import com.cnfantasia.server.api.access.codec.ake.bean.request.CarOutReq;
import com.cnfantasia.server.api.access.codec.ake.bean.request.CorrectParkCarCodeReq;
import com.cnfantasia.server.api.access.codec.ake.bean.request.ReportEptCarportReq;
import com.cnfantasia.server.api.access.codec.ake.bean.request.SyncParkBillReq;
import com.cnfantasia.server.api.access.codec.ake.bean.request.SyncParkInfoReq;
import com.cnfantasia.server.api.access.codec.ake.bean.request.UploadCameraImageReq;
import com.cnfantasia.server.api.access.codec.ake.bean.resp.CarInResp;
import com.cnfantasia.server.api.access.codec.ake.bean.resp.CorrectParkCarCodeResp;
import com.cnfantasia.server.api.access.codec.ake.bean.resp.SyncParkBillResp;
import com.cnfantasia.server.api.access.codec.ake.bean.resp.SyncServerTimeResp;
import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;
import com.cnfantasia.server.domainbase.carGroupBuilding.service.ICarGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumList.service.ICarNumListBaseService;
import com.cnfantasia.server.domainbase.parkCache.entity.ParkCache;
import com.cnfantasia.server.domainbase.parkCache.service.IParkCacheBaseService;
import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;
import com.cnfantasia.server.domainbase.parkingRecord.service.IParkingRecordBaseService;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author wangzhe
 * @date 2016年3月22日
 * @description 车禁数据源服务器（艾科）
 *
 */
public class AccessAkeHandler implements IoHandler {

    private static final String PARK_CODE = "ake_park_code"; // 艾科的停车场编码的key

    private final Map<String, ParkCache> mMsgCacheMap = new HashMap<String, ParkCache>();

    @Resource
    private IAccessService accessService;

    @Resource
    private ICarGroupBuildingBaseService carGroupBuildingBaseService;

    @Resource
    private ICarNumListBaseService carNumListBaseService;

    protected final Logger mLogger = Logger.getLogger(getClass());

    private SSLTcpServer mTcpServer = new SSLTcpServer();

    @Resource
    private IParkCacheBaseService parkCacheBaseService;

    @Resource
    private IParkingRecordBaseService parkingRecordBaseService;

    @Resource
    private IUuidManager uuidManager;

    @Resource
    private ISysParamManager sysParamManager;

    public AccessAkeHandler() {
        System.out.println("AccessAkeHandler new instance");
    }

    public void destroy() {
        mTcpServer.destroy();
    }

    @Override
    public void exceptionCaught(final IoSession session, final Throwable cause) throws Exception {
        mLogger.info("exceptionCaught..." + session);
    }

    public void init() {
        mTcpServer.init(new AccessAkeEncoder(), new AccessAkeDecoder()).setHandler(this);
        int port = 8234;
        try {
            port = Integer.valueOf(sysParamManager.getSysParaValue("accessTcpPort"));
        } catch (NumberFormatException e) {
        }
        mTcpServer.bind(port);
    }

    @Override
    public void inputClosed(final IoSession session) throws Exception {
        mLogger.info("inputClosed..." + session);
        session.closeOnFlush();
    }

    @Override
    public void messageReceived(final IoSession session, final Object obj) throws Exception {
        mLogger.info("messageReceived..." + session);
        if (obj instanceof Message) {
            final Message msg = (Message) obj;
            final MessageType mt = msg.getMessageType();
            switch (mt) {
            case SERVICE_REQ:
                processReq(session, msg);
                break;
            case SERVICE_RESP:
                if (null != msg.getRespMessage() && ParkRequest.STATUS_SUCCESS == msg.getRespMessage().getStatus()) {
                    ParkCache parkCache = mMsgCacheMap.remove(msg.getMessageId());
                    if (null != parkCache) {
                        parkCache.setSys0DelState(1);
                        parkCacheBaseService.updateParkCache(parkCache);
                    } else {
                    }
                } else {
                }
                break;
            case HEARTBEAT_REQ:
                processHeartBeat(session, msg);
                break;
            case HEARTBEAT_RESP:
                // do nothing
                break;
            default:
                break;
            }
        } else {
        }
    }

    @Override
    public void messageSent(final IoSession session, final Object message) throws Exception {
        mLogger.info("messageSent..." + session);
    }

    /**
     * @author wangzhe
     * @date 2016年3月30日
     * @description 处理心跳
     *
     * @param reqMsg
     * @return
     */
    private void processHeartBeat(final IoSession session, final Message reqMsg) {
        final Message.Builder respMsgBuilder = Message.newBuilder();
        respMsgBuilder.setMessageId(reqMsg.getMessageId());
        respMsgBuilder.setMessageType(MessageType.HEARTBEAT_RESP);
        session.write(respMsgBuilder.build());

        // 发送缓存信息
        if (null != session.getAttribute(PARK_CODE)) {
            List<BigInteger> remainList = new ArrayList<BigInteger>();
            for (Map.Entry<String, ParkCache> entry : mMsgCacheMap.entrySet()) {
                remainList.add(entry.getValue().getId());
            }
            List<ParkCache> pcs = accessService.getCache(session.getAttribute(PARK_CODE).toString(), remainList);
            if (null != pcs && pcs.size() > 0) {
                for (ParkCache parkCache : pcs) {
                    Message msg = null;
                    try {
                        msg = Message.parseFrom(ArrayUtils.toPrimitive(parkCache.getBuffer()));
                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                    }
                    if (null != msg) {
                        mMsgCacheMap.put(msg.getMessageId(), parkCache);
                        session.write(msg);
                    } else {
                        // 删掉多余的
                        parkCache.setSys0DelState(1);
                        parkCacheBaseService.updateParkCache(parkCache);
                    }
                }
            } else {
            }
        } else {
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @author wangzhe
     * @date 2016年3月29日
     * @description 处理请求
     * @param msg 请求
     * @return 回复
     */
    private void processReq(final IoSession session, final Message msg) {
        final Message.Builder msgBuilder = Message.newBuilder();
        msgBuilder.setMessageId(msg.getMessageId());
        msgBuilder.setMessageType(MessageType.SERVICE_RESP);
        msgBuilder.setServiceType(msg.getServiceType());
        final Message.RespMessage.Builder respBuilder = Message.RespMessage.newBuilder();
        respBuilder.setVer("1.0");
        respBuilder.setRespTime(new Date().getTime());

        int status = ParkRequest.STATUS_FAIL; // 1：业务成功，2：业务失败，3：系统异常
        String desc = "业务失败";
        int result_code = 0; // 只有status==2时才生效（具体编码，请参加具体接口的定义），status!=2时业务结果为0

        switch (msg.getServiceType()) {
        case LOGIN:
            // 由loginfilter接手，此处不做处理，直接认为登陆成功
            break;
        case SEARCH_CAR:
            break;
        case SYNC_PARK:
            // 停车场信息同步接口
            try {
                final SyncParkInfoReq spi = SocketBeanUtil.convertKeyStore(SyncParkInfoReq.class, msg.getReqMeesage().getBusDataList());
                if (!TextUtils.isEmpty(spi.getParkCode())) {
                    session.setAttribute(PARK_CODE, spi.getParkCode());
                    CarGroupBuilding carGroupBuilding = accessService.getCarGroupBuilding(spi.getParkCode());
                    if (null != carGroupBuilding) {
                        status = ParkRequest.STATUS_SUCCESS;
                        desc = "业务成功";
                    } else {
                        status = ParkRequest.STATUS_FAIL;
                        desc = "未找到该编号对应停车场";
                    }
                } else {
                    status = ParkRequest.STATUS_FAIL;
                    desc = "停车场编号不能为空";
                }
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final InvocationTargetException e) {
                e.printStackTrace();
            } catch (final IntrospectionException e) {
                e.printStackTrace();
            }
            break;
        case REPORT_CARPORTS:
            // 空车位
            try {
                final ReportEptCarportReq rec = SocketBeanUtil.convertKeyStore(ReportEptCarportReq.class,
                        msg.getReqMeesage().getBusDataList());
                System.out.println(rec);
                String parkcode = rec.getParkCode();
                session.setAttribute(PARK_CODE, rec.getParkCode());
                if (!TextUtils.isEmpty(parkcode)) {
                    if (null != rec.getEptCarports()) {
                        CarGroupBuilding carGroupBuilding = accessService.getCarGroupBuilding(parkcode);
                        if (null != carGroupBuilding) {
                            long emptyCarport = rec.getEptCarports().longValue();

                            if (emptyCarport < 0) {
                                emptyCarport = 0;
                            } else if (emptyCarport > carGroupBuilding.getParkingTotal()) {
                                emptyCarport = carGroupBuilding.getParkingTotal();
                            } else {
                            }

                            carGroupBuilding.setParkingCrnt(emptyCarport);
                            if (0 < carGroupBuildingBaseService.updateCarGroupBuilding(carGroupBuilding)) {
                                // 成功
                                status = ParkRequest.STATUS_SUCCESS;
                                desc = "业务成功";
                            } else {
                                // DB更新失败
                                status = ParkRequest.STATUS_FAIL;
                                desc = "数据库更新失败";
                            }
                        } else {
                            status = ParkRequest.STATUS_FAIL;
                            desc = "未找到该编号对应停车场";
                        }
                    } else {
                        // 空车位数为空
                        status = ParkRequest.STATUS_FAIL;
                        desc = "空车位数为空";
                    }
                } else {
                    // 停车场编码为空
                    status = ParkRequest.STATUS_FAIL;
                    desc = "停车场编码为空";
                }
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final InvocationTargetException e) {
                e.printStackTrace();
            } catch (final IntrospectionException e) {
                e.printStackTrace();
            }
            break;
        case CAR_IN:
            // 上报入场信息
            final CarInResp resp = new CarInResp();
            try {
                final CarInReq carIn = SocketBeanUtil.convertKeyStore(CarInReq.class, msg.getReqMeesage().getBusDataList());
                System.out.println(carIn);
                String parkcode = carIn.getParkCode();
                if (!TextUtils.isEmpty(parkcode)) {
                    session.setAttribute(PARK_CODE, carIn.getParkCode());
                    resp.setParkingCode(carIn.getParkCode());
                    CarGroupBuilding carGroupBuilding = accessService.getCarGroupBuilding(parkcode);
                    
                    Map<String, Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("carNum", carIn.getCarNo());
                	paramMap.put("gbId", carGroupBuilding.gettGroupBuildingId());
                    CarNumList carnum = accessService.getCardetail(paramMap);

                    if (null == carnum) {
                        // 没有行车记录
                        carnum = new CarNumList();
                        carnum.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
                        carnum.setLockStatus(1);
                        carnum.setStatus(0);
                        carnum.setLockStatus(0);
                        carnum.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
                        carNumListBaseService.insertCarNumList(carnum);
                    } else {
                    }

                    if (null != carGroupBuilding) {
                        BigInteger gbId = carGroupBuilding.gettGroupBuildingId();
                        ParkingRecord parkingRecord = new ParkingRecord();
                        parkingRecord.setParkingStartTime(DateUtils.formatDate(carIn.getInDate()));
                        parkingRecord.setParkingEndTime(null);
                        parkingRecord.setParkingFee(0L);
                        parkingRecord.settCarNumId(carnum.getId());
                        parkingRecord.settGroupBuildingId(gbId);
                        parkingRecord.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_parking_record));
                        if (0 < parkingRecordBaseService.insertParkingRecord(parkingRecord)) {
                            // 成功
                            resp.setParkingCode(parkingRecord.getId().toString());
                            // resp.setMoney(new BigDecimal(0));
                            // //此谓临时车主预存金额数，月卡车为空
                            status = ParkRequest.STATUS_SUCCESS;
                            desc = "业务成功";
                        } else {
                            // 数据库保存失败
                            status = ParkRequest.STATUS_FAIL;
                            desc = "数据库保存失败";
                        }
                    } else {
                        // 未找到该编号对应停车场
                        status = ParkRequest.STATUS_FAIL;
                        desc = "未找到该编号对应停车场";
                    }
                } else {
                    // 停车场编码为空
                    status = ParkRequest.STATUS_FAIL;
                    desc = "停车场编码为空";
                }
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final InvocationTargetException e) {
                e.printStackTrace();
            } catch (final IntrospectionException e) {
                e.printStackTrace();
            }
            respBuilder.addAllBusData(SocketBeanUtil.convert2KeyStore(resp));
            break;
        case CAR_OUT:
            // 上报离场信息
            try {
                final CarOutReq carOut = SocketBeanUtil.convertKeyStore(CarOutReq.class, msg.getReqMeesage().getBusDataList());
                System.out.println(carOut);
                if (!TextUtils.isEmpty(carOut.getParkingCode())) {
                    ParkingRecord pr = parkingRecordBaseService.getParkingRecordBySeqId(new BigInteger(carOut.getParkingCode()));
                    if (null != pr) {
                        // 有入场记录
                        pr.setParkingEndTime(DateUtils.formatDate(carOut.getOutDate()));
                        if (0 < parkingRecordBaseService.updateParkingRecord(pr)) {
                            // 成功
                            status = ParkRequest.STATUS_SUCCESS;
                            desc = "业务成功";
                        } else {
                            // 数据库update失败
                            status = ParkRequest.STATUS_FAIL;
                            desc = "数据库保存失败";
                        }
                    } else {
                        // 没有入场记录
                        status = ParkRequest.STATUS_FAIL;
                        desc = "没有入场记录";
                    }
                } else {
                }
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final InvocationTargetException e) {
                e.printStackTrace();
            } catch (final IntrospectionException e) {
                e.printStackTrace();
            }
            break;
        case PREPAY_FEE:
            break;
        case QUERY_FEE:
            break;
        case SYNC_PARK_BILL:
            // 账单同步信息
            SyncParkBillResp parkResp = new SyncParkBillResp();
            try {
                final SyncParkBillReq park = SocketBeanUtil.convertKeyStore(SyncParkBillReq.class, msg.getReqMeesage().getBusDataList());
                System.out.println(park);
                if (!TextUtils.isEmpty(park.getParkCode())) {
                    session.setAttribute(PARK_CODE, park.getParkCode());
                    parkResp.setParkCode(park.getParkCode());
                    status = ParkRequest.STATUS_SUCCESS;
                    desc = "业务成功";
                } else {
                    status = ParkRequest.STATUS_FAIL;
                    desc = "停车场编码为空";
                }
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final InvocationTargetException e) {
                e.printStackTrace();
            } catch (final IntrospectionException e) {
                e.printStackTrace();
            }
            respBuilder.addAllBusData(SocketBeanUtil.convert2KeyStore(parkResp));
            break;
        case SYNC_YDT_BILL:
            break;
        case SYNC_SERVER_TIME:
            // 时间同步时间
            respBuilder.setResultCode(0);
            final SyncServerTimeResp syncTimeResp = new SyncServerTimeResp();
            syncTimeResp.setTime(CnfantasiaCommbusiUtil.getNowTime());
            status = ParkRequest.STATUS_SUCCESS;
            desc = "业务成功";
            respBuilder.addAllBusData(SocketBeanUtil.convert2KeyStore(syncTimeResp));
            break;
        case QUERY_COUPON:
            break;
        case CORRECT_PARK_CAR_CODE:
            // 校正车入场信息接口
            CorrectParkCarCodeResp correctResp = new CorrectParkCarCodeResp();
            try {
                final CorrectParkCarCodeReq correct = SocketBeanUtil.convertKeyStore(CorrectParkCarCodeReq.class,
                        msg.getReqMeesage().getBusDataList());
                System.out.println(correct);
                ParkingRecord parkingRecord = parkingRecordBaseService.getParkingRecordBySeqId(new BigInteger(correct.getParkingCode()));
                if (null != parkingRecord) {
                    // 有入场记录
                    CarNumList carnum = carNumListBaseService.getCarNumListBySeqId(parkingRecord.gettCarNumId());
                    if (null == carnum) {
                        // 没有行车记录
                        carnum = new CarNumList();
                        carnum.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
                        carnum.setLockStatus(1);
                        carnum.setStatus(0);
                        carnum.setLockStatus(0);
                        carnum.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
                        carNumListBaseService.insertCarNumList(carnum);
                    } else {
                    }
                    parkingRecord.settCarNumId(carnum.getId());
                    parkingRecord.setParkingStartTime(DateUtils.formatDate(correct.getInDate()));
                    parkingRecord.setSys0UpdTime(DateUtils.formatDate(correct.getModifyDate()));
                    if (0 < parkingRecordBaseService.updateParkingRecord(parkingRecord)) {
                        // 成功
                        correctResp.setParkingCode(parkingRecord.getId().toString());
                        status = ParkRequest.STATUS_SUCCESS;
                        desc = "业务成功";
                    } else {
                        // 更新数据库失败
                        status = ParkRequest.STATUS_FAIL;
                        desc = "更新数据库失败";
                    }
                } else {
                    // 没有入场记录
                    status = ParkRequest.STATUS_FAIL;
                    desc = "没有入场记录";
                }
                respBuilder.addAllBusData(SocketBeanUtil.convert2KeyStore(correctResp));
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final InvocationTargetException e) {
                e.printStackTrace();
            } catch (final IntrospectionException e) {
                e.printStackTrace();
            }
            break;
        case CORRECT_YDT_CAR_CODE:
            break;
        case UPLOAD_CAMERA_IMAGE:
            // 上传摄像头车牌图片
            try {
                final UploadCameraImageReq ci = SocketBeanUtil.convertKeyStore(UploadCameraImageReq.class,
                        msg.getReqMeesage().getBusDataList());
                status = ParkRequest.STATUS_SUCCESS;
                desc = "业务成功";
                System.out.println(ci);
                // TODO 回复
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final InvocationTargetException e) {
                e.printStackTrace();
            } catch (final IntrospectionException e) {
                e.printStackTrace();
            }
            break;
        case QUERY_MONTH_CARD:
            break;
        case RENEWALFEES_OF_MONTH_CARD:
            break;
        case OPEN_GATE_REQ:
            break;
        case UPLOAD_CAR_LOCATION:
            break;
        case SEND_TRADER_COUPON:
            break;
        case UPDATE_TRADER_COUPON_STATUS:
            break;
        default:
            break;
        }

        // 必填
        respBuilder.setStatus(status);
        respBuilder.setDesc(desc);
        respBuilder.setResultCode(result_code);
        msgBuilder.setRespMessage(respBuilder);
        session.write(msgBuilder.build());
    }

    @Override
    public void sessionClosed(final IoSession session) throws Exception {
        mLogger.info("sessionClosed..." + session);
    }

    @Override
    public void sessionCreated(final IoSession session) throws Exception {
        mLogger.info("sessionCreated..." + session);
    }

    @Override
    public void sessionIdle(final IoSession session, final IdleStatus status) throws Exception {
        mLogger.info("sessionIdle..." + session + ",status:" + status);
        session.closeOnFlush();
    }

    @Override
    public void sessionOpened(final IoSession session) throws Exception {
        mLogger.info("sessionOpened..." + session);
    }

    public void setAccessService(final IAccessService accessService) {
        this.accessService = accessService;
    }

    public void setCarGroupBuildingBaseService(final ICarGroupBuildingBaseService carGroupBuildingBaseService) {
        this.carGroupBuildingBaseService = carGroupBuildingBaseService;
    }

    public void setCarNumListBaseService(final ICarNumListBaseService carNumListBaseService) {
        this.carNumListBaseService = carNumListBaseService;
    }

    public void setParkCacheBaseService(final IParkCacheBaseService parkCacheBaseService) {
        this.parkCacheBaseService = parkCacheBaseService;
    }

    public void setParkingRecordBaseService(final IParkingRecordBaseService parkingRecordBaseService) {
        this.parkingRecordBaseService = parkingRecordBaseService;
    }

    public void setUuidManager(final IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

}
