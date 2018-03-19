/**
 * 文件名		：SysParamKey.java
 * 创建日期	：2013-11-26
 * Copyright (c) 2003-2013 北京联龙博通

 * All rights reserved.
 */
package com.cnfantasia.server.api.commSysPara.constant;


/**
 * 描述:系统参数Key值
 * 
 * @author syl
 * @version 1.00
 */
public final class SysParamKey {
	/**
	 * 重置后默认密码对应的系统参数key
	 */
	public static final String DEFAULT_PASSWORD = "DefaultPassword";
	// /**用户每天最大抽奖记录数对应的系统参数key*/
	// public static final String MAX_PRIZE_COUNT ="MaxPirzeCount";
	/**
	 * 用户门牌图片信息配置，本地路径，图片格式，长，宽
	 */
	public static final String ROOM_INFO_PIC_CONFIG = "RoomInfoPicConfig";
	/**
	 * 产品图片信息根路径
	 */
	public static final String PRODUCT_PIC_BASE_PATH = "ProductPicBasePath";
	/**
	 * 淘宝AppKey和Secret信息
	 */
	public static final String APP_APPLY_INFO_TAOBAO = "AppApplyInfo_TAOBAO";
	/**
	 * QQ的AppKey和Secret信息
	 */
	public static final String APP_APPLY_INFO_QQ = "AppApplyInfo_QQ";
	/**
	 * 短信验证码参数规则
	 */
	public static final String VALICODE_RULE = "ValicodeRule";
	/**
	 * 今晚吃什么图片信息根路径
	 */
	public static final String EatToday_PIC_BASE_PATH = "EatTodayPicBasePath";
	/**
	 * 用户个人图片上传配置信息
	 */
	public static final String USER_IMAGE_PROFILE_CONFIG = "UserImageProfileConfig";
	/**
	 * 文件服务器配置信息
	 */
	public static final String FILE_SERVER_CONFIG = "FileServerConfig";
	/**
	 * 物业公告图片路径
	 **/
	public static final String NOTICE_PIC_PATH = "NoticePicPath";
	/**
	 * 登录的SessionKey配置
	 **/
	public static final String LOGIN_SESSION_KEY = "LoginSessionKey";
	/**
	 * 公司信息
	 **/
	public static final String COMPANY_INFO = "CompanyInfo";
	/**
	 * 认证图片相对地址
	 **/
	public static final String EBUY_AUTH_PIC_CONFIG = "EbuyAuthPicConfig";
	// /**应用下载地址配置**/
	// public static final String APP_DOWNLOAD_CONFIG = "AppDownloadConfig";
	/**
	 * 微博图片地址
	 **/
	public static final String MICROBLOG_PIC_BASE_PATH = "MicroblogPicBasePath";
	/**
	 * 微博类别图标地址
	 **/
	public static final String MICROBLOG_TYPE_ICO_BASE_PATH = "MicroblogTypeIcoBasePath";
	/**
	 * 微博类别图标选择前地址
	 **/
	public static final String MICROBLOG_TYPE_ICO_BEFORE_BASE_PATH = "MicroblogTypeIcoBeforeBasePath";
	/**
	 * 微博类别图标选择后地址
	 **/
	public static final String MICROBLOG_TYPE_ICO_AFTER_BASE_PATH = "MicroblogTypeIcoAfterBasePath";

	/**
	 * 小区图标地址
	 **/
	public static final String GROUP_BUILDING_ICON_BASEPATH = "GroupBuildingIconBasePath";
	/**
	 * 小区描述图片地址
	 **/
	public static final String GROUP_BUILDING_PICDESC_BASEPATH = "GroupBuildingPicDescBasePath";

	/** 应用下载引导页面 **/
	// public static final String BASE_DOWNLOAD_GUIDE_PAGE =
	// "BaseDownloadGuidePage";

	/**
	 * 产品特殊图片地址
	 **/
	public static final String PRODUCT_PIC_SPECIALPATH = "ProductPicSpecialPath";

	/**
	 * 厨房菜谱图片地址根路径
	 **/
	public static final String KITCHEN_COOKBOOK_PIC_BASEPATH = "KitchenCookBookPicBasePath";
	/**
	 * 厨房菜谱图片类别根路径
	 **/
	public static final String KITCHEN_COOKBOOK_TYPEPIC_BASEPATH = "KitchenCookBookTypePicBasePath";
	/**
	 * 厨房菜谱步骤图片根路径
	 **/
	public static final String KITCHEN_COOKBOOK_STEPPIC_BASEPATH = "KitchenCookBookStepPicBasePath";

	/**
	 * 社区论坛帖子图片地址
	 **/
	public static final String CommunityForum_Pic_BasePath = "CommunityForumPicBasePath";
	/**
	 * 社区论坛帖子类别图标地址
	 **/
	public static final String CommunityForum_Type_Ico_BasePath = "CommunityForumTypeIcoBasePath";
	/**
	 * 社区论坛帖子小图地址
	 **/
	public static final String CommunityForum_Small_Pic_BasePath = "CommunityForumSmallPicBasePath";

	/**
	 * IOS设备关键字
	 */
	public static final String IOS_Device_KeyWords = "IOSDeviceKeyWords";
	/**
	 * 微信登录配置
	 */
	public static final String WeiXin_Login_Config = "WeiXinLoginConfig";

	/**
	 * 门牌校验上传的图片地址
	 */
	public static final String ROOM_VALIDATE_PIC_BASEPATH = "RoomValidatePicBasePath";

	// /**支付成功短信通知的管理员手机号*/
	// public static final String PAY_SUCC_NOTIFY_PHONE = "PaySuccNotifyPhone";
	/**
	 * 订单支付通知-电商
	 */
	public static final String PAY_SUCC_NOTIFY_PHONE_EBUY = "PaySuccNotifyPhone_Ebuy";
	/**
	 * 订单支付通知-物业费
	 */
	public static final String PAY_SUCC_NOTIFY_PHONE_PAYBILL = "PaySuccNotifyPhone_PayBill";

	/**
	 * 订单支付通知-疏通服务费
	 */
	public static final String PAY_SUCC_NOTIFY_PHONE_DredgeBill = "PaySuccNotifyPhone_DredgeBill";

	/**
	 * 订单支付通知-生活缴费
	 */
	public static final String PAY_SUCC_NOTIFY_PHONE_LivingPayBill = "PaySuccNotifyPhone_LivingPayBill";

	/**
	 * 订单支付通知-车禁缴费
	 */
	public static final String PAY_SUCC_NOTIFY_PHONE_CarKeyBill = "PaySuccNotifyPhone_CarKeyBill";

	/**
	 * 订单支付通知-积分
	 */
	public static final String PAY_SUCC_NOTIFY_PHONE_POINT = "PaySuccNotifyPhone_Point";
	/**
	 * 支付成功是否短信通知管理员
	 */
	public static final String PAY_SUCC_NOTIFY_SWITCH = "PaySuccNotifySwitch";

	/**
	 * 社区商家图片列表根路径
	 */
	public static final String COMMUNITY_SUPPLY_PIC_LIST_BASEPATH = "CommunitySupplyPicBasePath";
	/**
	 * 支付成功是否短信通知管理员
	 */
	public static final String COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH = "CommunitySupplyTypeIcoBasePath";

	/**
	 * 百度推送消息的配置
	 */
	public static final String BAIDU_PUSH_CONFIG = "BaiduPushConfig";
	/**
	 * 百度推送消息新的配置
	 */
	public static final String NEW_PUSH_CONFIG = "BaiduNewPushConfig";

	/**
	 * 百度推送消息的配置(师傅端Android)
	 */
	public static final String Baidu_PUSH_CONFIG_Master_Android = "BaiduPushConfigMasterAndroid";

	/**
	 * 百度推送消息的配置(师傅端iOS)
	 */
	public static final String Baidu_PUSH_CONFIG_Master_iOS = "BaiduPushConfigMasterIOS";

	/**
	 * IOS消息推送的部署状态,1：开发状态;2：生产状态
	 */
	public static final String IOS_DEPLOY_STATUS = "IOS_deploy_status";

	/**
	 * 个人成就图标根路径
	 */
	public static final String ACHIEVEMENT_PIC_BASEPATH = "AchievementPicBasePath";

	/**
	 * 个人爱好图片根路径
	 */
	public static final String HOBBY_PIC_BASEPATH = "HobbyPicBasePath";

	/**
	 * 拼一拼图片保存根路径
	 */
	public static final String COMMUNITY_PINYIPIN_PIC_BASEPATH = "CommunityPinyipinPicBasePath";

	/**
	 * 换一换图片保存根路径
	 */
	public static final String COMMUNITY_EXCHANGE_PIC_BASEPATH = "CommunityExchangePicBasePath";

	/**
	 * 商家基本信息的图片根路径
	 */
	public static final String COMMUNITY_SUPPLY_BASIC_PICPATH = "CommunitySupplyBasicPicPath";

	/**
	 * 解放区二维码图片地址
	 */
	public static final String JIEFANGQU_QRCODE_PICPATH = "JiefangquQrcodePicPath";

	/**
	 * 默认厨房推荐菜谱图片地址
	 */
	public static final String DEFAULT_KITCHEN_COOKBOOK_PIC = "DefaultKitchenCookbookPic";

	/**
	 * 支付通知服务器根路径
	 */
	public static final String PAYNOTIFY_BASEURL = "WeinXinPayNotifyBaseUrl";

	/**
	 * 广告条图片存放地址
	 */
	public static final String ADVERTICE_PIC_BASEPATH = "AdverticePicBasePath";
	/**
	 * 世外桃源广告图片地址
	 */
	public static final String XANADU_ADS_PICPATH = "XanaduAdsPicPath";

	/**
	 * 百度LbsAPI的AK密钥
	 */
	public static final String BAIDU_LBS_AK = "BaiDu_Lbs_AK";

	/**
	 * 解放区应用下载根路径
	 */
	public static final String APP_DOWNLOAD_BASEURL = "AppDownloadBaseUrl";

	/**
	 * 运营平台的登录地址
	 */
	public static final String Oos_Connect_Info = "OosConnectInfo";
	/** oos根目录 */
	public static final String OOS_PATH = "oosPath";
	/**
	 * 海吉星给解放区分配的source_tag，订单推送时使用
	 */
	public static final String HJX_Source_tag = "source_tag";
	/**
	 * 海吉星给解放区分配的salt，订单推送时使用
	 */
	public static final String HJX_Salt = "salt";
	/**
	 * 海吉星订单推送接口版本
	 */
	public static final String HJX_Version = "version";

	/**
	 * 海吉星订单推送目标URL
	 */
	public static final String OrderPushURL = "OrderPushURL";

	/**
	 * 社区商户的图片列表保存目录
	 */
	public static final String COMMUNITY_COMPANY_LISTPIC_BASEPATH = "CommunityCompanyListPicBasePath";

	/**
	 * 家庭留言板列表图片url根路径
	 */
	public static final String FAMILY_MSG_LIST_PIC_BASEPATH = "FamilyMsgListPicBasePath";

	/**
	 * 家庭抽奖默认图片Url
	 */
	public static final String Family_Discount_Default_PicUrl = "FamilyDiscountDefaultPicUrl";

	/**
	 * 物业服务图标服务器根路径
	 */
	public static final String PropertyService_Icon_BasePath = "PropertyServiceIconBasePath";

	/**
	 * 物业服务图标服务器根路径
	 */
	public static final String SurpriseGift_Pic_BasePath = "SurpriseGiftPicBasePath";

	/**
	 * 物业报修图标根据路径
	 */
	public static final String Repair_Pic_BasePath = "RepairPicBasePath";

	/**
	 * 当前服务器访问根路径
	 */
	public static final String Curr_Server_BaseUrl = "CurrServerBaseUrl";

	/**
	 * 最新的API访问路径，用于api_manager访问API使用
	 */
	public static final String Last_Api_BaseUrl = "LastApiBaseUrl";

	/**
	 * 产品介绍图片的地址
	 */
	public static final String Product_Pic_Introduce_Path = "ProductPicIntroducePath";

	/**
	 * 优惠全金额使用百分比,1表示100%
	 */
	public static final String Coupon_User_Percent = "CouponUserPercent";
	/**
	 * 优惠全金额使用百分比,1表示100%(解放区轻应用)
	 */
	public static final String Coupon_User_Percent_Jfq_LightApp = "CouponUserPercent_Jfq_LightApp";

	/**
	 * 意外奖项失效图标，使用surpriseGiftPicBasePath/根路径
	 */
	public static final String Coupon_ExpiredIcon_Url = "CouponExpiredIconUrl";
	/**
	 * 意外奖项可用图标，使用surpriseGiftPicBasePath/根路径
	 */
	public static final String Coupon_AvailableIcon_Url = "CouponAvailableIconUrl";

	/**
	 * 运维图片文案信息存放根路径
	 */
	public static final String OperationData_Pic_BasePath = "OperationDataPicBasePath";

	/**
	 * 家庭财富相关图标根路径
	 */
	public static final String FamilyWealth_BaseIconUrl = "FamilyWealthBaseIconUrl";

	/**
	 * 抽奖通知超时处理时间(秒)
	 */
	public static final String PrizeTmpData_TimeOut = "PrizeTmpDataTimeOut";

	//
	public static final String AD_PIC_BASE_PATH = "AdPicBasePath";

	/**
	 * 楼下店铺头相和营业执照
	 */
	public static final String EBUY_STORE_PIC = "EbuyStorePic";

	/**
	 * 广告模块图片刷新时间戳
	 */
	public static final String AdvertisePicTmpStmp = "AdvertisePicTmpStmp";

	/**
	 * 楼下店铺一个小区最多抓多少个
	 */
	public static final String ebuySupplyFetchMaxCount = "ebuySupplyFetchMaxCount";

	/**
	 * 便利店;超市', '楼下店铺抓取的关键字
	 */
	public static final String ebuySupplyFetchKey = "ebuySupplyFetchKey";

	/**
	 * 垂直O2O开关
	 */
	public static final String HO2O_Config = "HO2OConfig";

	/**
	 * 疏通服务图标路径
	 */
	public static final String DredgePicBasePath = "DredgePicBasePath";

	// 是否发送短信，用于测试环境不发短信配置
	public static final String IS_MESSAGE_SEND = "IsMessageSend";

	/**
	 * 街坊消息推送时间点
	 */
	public static final String MicroBlogPushTime = "MicroBlogPushTime";
	/**
	 * 奖项图标存储路径
	 */
	public static final String MicroQueue_PushType = "MicroQueuePushType";

	/**
	 * 抽奖活动图标存储路径
	 */
	public static final String PrizeActivity_Icon_BasePath = "PrizeActivityIconBasePath";
	/**
	 * 奖项图标存储路径
	 */
	public static final String PrizeOption_Icon_BasePath = "PrizeOptionIconBasePath";

	/**
	 * 奖项图标存储路径
	 */
	public static final String SQPAY_NOTIFY_BASEURL = "SqPayNotifyBaseUrl";
	/**
	 * 双乾支付的名称
	 */
	public static final String SQ_PAY_NAME = "SqPayName";
	/**
	 * 双乾支付的名称
	 */
	public static final String SQ_PAY_NAME_345 = "SqPayName345";
	/**
	 * 双乾支付的名称（带"9.8折"优惠信息）
	 */
	public static final String SQ_PAY_NAME_348 = "SqPayName348";
	/**
	 * 双乾支付的名称（带"9.5折"优惠信息）
	 */
	public static final String SQ_PAY_NAME_350 = "SqPayName350";

	/**
	 * 物业打印广告图片根路径
	 */
	public static final String PROPERTY_GBAD_BASEURL = "PropertyGbAdBaseUrl";

	/**
	 * 新增维修单后通知的邮件地址
	 */
	public static final String AddDredgeBillNotifyEmail = "addDredgeBillNotifyEmail";

	/**
	 * 依谷网code
	 **/
	public static final String egu_order_code = "eguTradeCode";
	/**
	 * 依谷网订单同步url
	 */
	public static final String egu_order_push_url = "eguOrderPush";
	/**
	 * 依谷网key
	 */
	public static final String egu_order_key = "eguorderKey";
	/**
	 * 依谷物流同步url
	 */
	public static final String egu_express_trace_url = "eguExpressTraceUrl";

	/**
	 * 百度消息推送支持的消息类型
	 */
	public static final String MSG_PUSH_TYPES = "msgpushTypes";
	/**
	 * 百度消息推送定时任务支持的类型
	 */
	public static final String MSG_TASK_TYPES = "msgTaskTypes";
	/**
	 * 门禁广告图片地址
	 */
	public static final String doorKeyPicUrl = "doorKeyPic";

	/**
	 * 为师傅自动请求新订单数，刷新时间间隔，单位秒
	 */
	public static final String qryNewDredgeBillCountTimeInterval = "qryNewDredgeBillCountTimeInterval";

	/**
	 * 轻应用的根路径
	 */
	public static final String LA_BASE_URL = "LA_BASE_URL";

	/**
	 * 轻应用的维修单状态发生变化模板消息id
	 */
	public static final String LA_DREDGE_BILL_STATUE_CHANGE_TEMP_ID = "LA_DREDGE_BILL_STATUE_CHANGE_TEMP_ID";

	/**
	 * 停车宝相关begin
	 */
	public final static String FINANCE_CAR_APP_KEY = "financeCarAppKey";// appKey
	public final static String FINANCE_CAR_TOKEN = "financeCarToken"; // token
	public final static String FINANCE_CAR_URL = "financeCarUrl"; // url
	public final static String FINANCE_CAR_INDEX = "financeCarIndex"; // index
	public final static String PARKING_URL = "parking_url"; // 停车宝获取当日收益提交地址
	/**
	 * 系统是否开通停车宝开关标志位参数===>{0：关闭；1：开通}
	 */
	public final static String IS_FINANCE_CAR_OPENED = "isFinanceCarOpened";
	/**
	 * 停车宝相关end
	 */
	public final static String QUERY_PARKING_FEE_WAITING_TIME = "queryParkingFeeWaitingTime";

	// 物业维修内转外天数
	public static final String REPAIR_CONVERT_DAY = "repairConvertDay";
	/**
	 * 在线订座
	 */
	public static final String FILE_PAY_URL = "filmPayUrl";
	/**
	 * 查询影院
	 */
	public static final String FILE_LOOK_URL = "filmLookUrl";

	/**
	 * 易豪生客户端redis消息发布查询最大等待时间
	 */
	public static final String QUERY_WAITING_MAX_TIME = "yhs_query_max_time";
	
	/**
	 *华庭车禁客户端redis消息发布查询最大等待时间
	 */
	public static final String HT_QUERY_WAITING_MAX_TIME = "ht_query_max_time";
	/**
	 * 查询易豪生所有连接等待时间
	 */
	public static final String QUERY_ALL_LINKS_WAITING_MAX_TIME = "query_all_links_waiting_time";
	/**
	 * 购买胡萝卜车险链接
	 */
	public static final String HLB_CAR_INSURANCE_URL = "hlb_car_insurance_url";
	/**
	 * 胡萝卜加密密钥
	 */
	public static final String HLB_KEY = "hlb_key";
	/**
	 * 险萝卜图片地址
	 */
	public static final String HLB_AD_IMG_URL = "hlb_ad_img_url";
	/**
	 * API结点个数
	 */
	public static final String API_NODE_COUNT = "api_node_count";

	/**
	 * 师傅非首次设置维修类型时中否看到所有维修类型，0：不行，1可以
	 */
	public static final String MASTER_CAN_QRY_ALL_DREDGE_TYPE = "master_can_qry_all_dredge_type";

	/**
	 * EAS报销单 可配置参数
	 */
	public static final String EASBizAccountPushParam = "EASBizAccountPushParam";

	/**
	 * 推送报销单或付款单到EAS服务器的连接地址
	 */
	public static final String EAS_Server_URL = "EAS_Server_URL";

	/**
	 * 是否启动EAS订单推送功能,0不启动，1启动
	 */
	public static final String Is_enable_EAS_Push = "Is_enable_EAS_Push";

	/**
	 * EAS登录账号配置
	 */
	public static final String EAS_login_account = "EAS_login_account";

	/**
	 * EAS推送时是否使用测试预算编号，1用测试的，0用正式的
	 */
	public static final String Is_use_test_budgetNumber = "Is_use_test_budgetNumber";

	/**
	 * 公司年度活动开始日期
	 */
	public static final String Company_Activity_Start_Date = "Company_Activity_Start_Date";
	/**
	 * 公司年度活动截止日期
	 */
	public static final String Company_Activity_End_Date = "Company_Activity_End_Date";

	/**
	 * 街坊社区活动链接地址
	 */
	public static final String Neighbour_Activity_LinkDetail = "Neighbour_Activity_LinkDetail";

	/**
	 * 街坊社区活动链接标题
	 */
	public static final String Neighbour_Activity_LinkTitle = "Neighbour_Activity_LinkTitle";

	/**
	 * 隐私图片服务器
	 */
	public static final String PrivateImageServerUrl = "privateImageServerUrl";

	/**
	 * 代扣卡优惠方案等级
	 */
	public static final String DISCOUNTRANGE = "discountRange";
	/**
	 * 双乾支付补贴双乾公钥
	 */
	public static final String SQ_PAY_BT_SQ_PUBLIC_KEY = "sq_pay_bt_sq_public_key";
	/**
	 * 双乾支付补贴解放区私钥
	 */
	public static final String SQ_PAY_BT_JFQ_PRIVATE_KEY = "sq_pay_bt_jfq_private_key";

	/**
	 * 缴费次数上限
	 */
	public static final String MAXPAYTIMES = "maxPayTimes";
	/**
	 * 双乾支付商户号
	 */
	public static final String SQ_BT_MER_NO = "sq_bt_mer_no";
	/**
	 * app崩溃通知邮件
	 */
	public static final String App_Crash_Notify_Mail = "appCrashNotyfyEmail";
	/**
	 * 选择周期缴费月数
	 */
	public static final String ALTERPERIODMONTHS = "alterPeriodMonths";
	/**
	 * 一元夺宝图片位置
	 */
	public static final String flashDealActivityPicBasePath = "flashDealActivityPicBasePath";

	/**
	 * 十分到家 参数配置
	 */
	public static final String SFDJPushParam = "SFDJPushParam";

	/**
	 * 轻松到家 参数配置
	 */
	public static final String QSDJPushParam = "QSDJPushParam";

	/**
	 * 物业账单卡片处车禁小图标
	 */
	public static final String CAR_BILL_ICON = "car_bill_icon";

	public static final String PropertySoftwareNotifyFailMail = "propertySoftwareNotifyFailMail";

	/**
	 * 物业代扣卡自动划扣日期
	 */
	public static final String PropertyCardDeducationDate = "propertyCardDeducationDate";

	// 楼下店做为自提点的楼下店ID
	public static final String Service_Point_Supply_Merchant_Ids = "ServicePointSupplyMerchantIds";

	// 楼下店内限时购是否在商品列表内重复
	public static final String Repeat_LimitBuy_Product_InStore = "repeatLimitBuyProductInStore";

	// 理财类图标，json字符串
	public static final String Community_Supply_Type_Finance = "CommunitySupplyTypeFinance";

	/**
	 * 子账号不能分配的角色集
	 */
	public static final String SubUser_Exclue_Role = "SubUserExclueRole";

	// 功能栏图标目录
	public static final String App_Function_Bar_PicBase = "AppFunctionBarPicBase";

	// 首页消息图标目录
	public static final String Home_Message_Icon_PicBase = "HomeMessageIconPicBase";

	// 短链接--首页
	public static final String SHORTURL_HOME = "shorturl_home";

	// 用户寻求帮助没片区经理时发的邮件
	public static final String UserQeustion_NotifyMail = "UserQuestionNotifyMail";

	// 寻求帮助电话类别图标
	public static final String CommunityPhoneTypeIcon = "CommunityPhoneTypeIcon";

	// 寻求帮助默认文案
	public static final String AskHelpPlaceholdDefault = "AskHelpPlaceholdDefault";

	// 静态资源根目录
	public static final String ResourcePath = "resourcePath";

	// 短链接服务器域名
	public static final String ShortUrlServer = "shortUrlServer";

	// 开始计算欠费的日期 如2017-01-01
	public static final String SYSTEMSTARTBILLTIME = "systemStartBillTime";
	/** 借贷图标根路径 */
	/**支付成功是否短信通知管理员*/
	public static final String LOAN_ICO_BASEPATH = "loanBasePath";
	/** 华庭车禁websocket端口 */
	public static final String HT_CAR_WEBSOCKET_PORT = "ht_car_websocket_port";
	/**复制订单，redis缓存时间*/
	public static final String COPY_ORDER_CACHE_TIME = "copy_order_cache_time";
	/**蜜蜂提供的第三方编号*/
	public static final String MF_CAR_GCODE="mf_car_gcode";
	/**蜜蜂车禁服务端地址*/
	public static final String MF_CAR_SERVER_URL="mf_car_server_url";
	/**华安车禁服务端地址*/
	public static final String HA_CAR_SERVER_URL="ha_car_server_url";
	/**蜜蜂车禁加密秘钥*/
	public static final String MF_CAR_REQUEST_KEY="mf_car_request_key";
	/**临停车车场查询结果redis缓存时间（单位：秒，默认300秒）*/
	public static final String PLOT_CACHE_EXPIRE_SECONDS = "plot_cache_expire_seconds";
	/**团贷网签名校验公钥*/
	public static final String TDW_SIGN_PUBLIC_KEY="tdw_sign_public_key";
	/**团贷网缓存时间*/
	public static final String TDW_CACHE_TIME = "tdw_cache_time";
	/**
	 * app首页顶部背景图.
	 */
	public static final String HOME_BACK_GROUND_PIC = "homeBackGroundPic";
	/**
	 * 解放区体验店URL地址
	 */
	public static final String JFQ_Store_Visit_URL = "jfq_store_visit_url";
	/**
	 * 解放区体验店分享标题
	 */
	public static final String JFQ_Store_Share_Title = "jfq_store_share_title";
	/**解放区体验店分享描述*/
	public static final String JFQ_Store_Share_Content = "jfq_store_share_content";
	/**车牌前缀缓存时间*/
	public static final String CAR_PREFIX_CACHE_TIME = "car_prefix_cache_time";
	/**临停车轻应用push标题*/
	public static final String LA_TMP_CAR_PUSH_Title = "la_tmp_car_push_title";
	/**临停车轻应用push描述*/
	public static final String LA_TMP_CAR_PUSH_DESC = "la_tmp_car_push_desc";

	/**临停车轻应用push标题--商户端扫码*/
	public static final String LA_TMP_CAR_PUSH_Title_Scan = "la_tmp_car_push_title_scan";
	/**临停车轻应用push描述--商户端扫码*/
	public static final String LA_TMP_CAR_PUSH_DESC_Scan = "la_tmp_car_push_desc_scan";

	
	/**
	 * 体验店的id
	 */
	public static final String Experience_Store_Id = "Experience_Store_Id";

	public static final String JOB_ADMIN_BASE_URL = "jobAdminBaseUrl";

	public static final String DREDGE_REFUND_NOTIFY_EMAIL = "dredgeRefundNotityEmail";
	public static final String SUBMIT_DREDGE_BILL_PUSH_ID = "submitDredgeBillPushId";
	public static final String DREDGE_BILL_REFUND_PUSH_ID = "dredgeBillRefundPushId";
	public static final String DREDGE_BILL_FINISH_OOS_PUSH_ID = "dredgeBillFinishOosPushId";
	public static final String ADDRESS_JSON_FILE_MD5 = "addressJsonFileMD5";
	public static final String JFQ_SOTRE_INFO = "jfq_store_info";
	public static final String CAR_XMF_EXPIRE_THRESHOLD = "car_xmf_expire_threshold";
	public static final String CAR_XMF_PAY_MAX_MONTH = "car_xmf_pay_max_month";

	public static final String CAR_YDT_USERKEY="car_ydt_userkey";
	public static final String CAR_YDT_SERVER_URL="car_ydt_server_url";
	/**
	 * 生活缴费首页图片
	 */
	public static final String AD_IMAGES_FOR_LivingPay_INDEX="ad_images_for_livingpay_index";
}
