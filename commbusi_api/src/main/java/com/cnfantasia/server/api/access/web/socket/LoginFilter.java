package com.cnfantasia.server.api.access.web.socket;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.http.util.TextUtils;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

import com.cnfantasia.server.api.access.codec.SocketBeanUtil;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message.MessageType;
import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;
import com.cnfantasia.server.api.access.codec.ake.bean.request.LoginReq;

public class LoginFilter extends IoFilterAdapter {

    private boolean mIsLogin = false;

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession session, Object obj) throws Exception {
        if (!mIsLogin) {
            if (obj instanceof Message) {
                final Message msg = (Message) obj;

                final Message.Builder msgBuilder = Message.newBuilder();
                msgBuilder.setMessageId(msg.getMessageId());
                msgBuilder.setMessageType(MessageType.SERVICE_RESP);
                msgBuilder.setServiceType(msg.getServiceType());
                final Message.RespMessage.Builder respBuilder = Message.RespMessage.newBuilder();
                respBuilder.setVer("1.0");
                respBuilder.setRespTime(new Date().getTime());

                int status = ParkRequest.STATUS_FAIL; // 1：业务成功，2：业务失败，3：系统异常
                String desc = "业务失败";
                int result_code = 1; // 只有status==2时才生效（具体编码，请参加具体接口的定义），status!=2时业务结果为0

                if (MessageType.SERVICE_REQ == msg.getMessageType()) {
                    try {
                        LoginReq login = SocketBeanUtil.convertKeyStore(LoginReq.class, msg.getReqMeesage().getBusDataList());
                        // 登录业务
                        if (null != login && !TextUtils.isEmpty(login.getParkAccount()) && !TextUtils.isEmpty(login.getParkPwd())) {
                            // 验证账号
                            mIsLogin = login(login.getParkAccount(), login.getParkPwd());
                            if (mIsLogin) {
                                status = ParkRequest.STATUS_SUCCESS;
                                respBuilder.setResultCode(0);
                                desc = "登陆成功";
                            } else {
                                status = ParkRequest.STATUS_FAIL;
                                respBuilder.setResultCode(1); // 无效账号
                                desc = "账号或密码错误";
                            }
                        } else {
                            status = ParkRequest.STATUS_FAIL;
                            respBuilder.setResultCode(1); // 无效账号
                            desc = "账号或密码不能为空";
                        }
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    } catch (InstantiationException e1) {
                        e1.printStackTrace();
                    } catch (InvocationTargetException e1) {
                        e1.printStackTrace();
                    } catch (IntrospectionException e1) {
                        e1.printStackTrace();
                    }
                } else {
                }

                respBuilder.setStatus(status);
                respBuilder.setDesc(desc);
                respBuilder.setResultCode(result_code);
                msgBuilder.setRespMessage(respBuilder);

                session.write(msgBuilder.build());
            } else {
            }

            // 登陆不成功，直接关闭
            if (mIsLogin) {
                // TODO 回复积压的数据
            } else {
                session.closeOnFlush();
            }
        } else {
            nextFilter.messageReceived(session, obj);
        }
    }

    /**
     * @author wangzhe
     * @date 2016年3月30日
     * @description 账户验证
     *
     * @param user
     * @param pwd
     * @return
     */
    private boolean login(String user, String pwd) {
        return true;
    }
}
