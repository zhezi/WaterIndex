package com.jieshuizhibiao.waterindex.http;

import com.jieshuizhibiao.waterindex.beans.AccountDetailResponseBean;
import com.jieshuizhibiao.waterindex.beans.AuthDetailResponseBean;
import com.jieshuizhibiao.waterindex.beans.BannerListResponseBean;
import com.jieshuizhibiao.waterindex.beans.CreateOrderResponseBean;
import com.jieshuizhibiao.waterindex.beans.FactoryDetailResponseBean;
import com.jieshuizhibiao.waterindex.beans.FactoryListResponseBean;
import com.jieshuizhibiao.waterindex.beans.FactoryServiceResponseBean;
import com.jieshuizhibiao.waterindex.beans.GoodsResposeBean;
import com.jieshuizhibiao.waterindex.beans.InfoListsResponseBean;
import com.jieshuizhibiao.waterindex.beans.ListOrder;
import com.jieshuizhibiao.waterindex.beans.LoginResponseBean;
import com.jieshuizhibiao.waterindex.beans.MoveMoneryBean;
import com.jieshuizhibiao.waterindex.beans.OrderDetailResponseBean;
import com.jieshuizhibiao.waterindex.beans.OrderListsResponseBean;
import com.jieshuizhibiao.waterindex.beans.PayMentResponseBean;
import com.jieshuizhibiao.waterindex.beans.RegisterResponseBean;
import com.jieshuizhibiao.waterindex.beans.SellResponseBean;
import com.jieshuizhibiao.waterindex.beans.ServiceListResponseBean;
import com.jieshuizhibiao.waterindex.beans.SysConfigResponseBean;
import com.jieshuizhibiao.waterindex.beans.SysMsgBase;
import com.jieshuizhibiao.waterindex.beans.SystemMsgResponseBean;
import com.jieshuizhibiao.waterindex.beans.TotalPriceResponseBean;
import com.jieshuizhibiao.waterindex.beans.TradeCenterResponseBean;
import com.jieshuizhibiao.waterindex.beans.TradeIndexBase;
import com.jieshuizhibiao.waterindex.beans.TradeLineResponseBean;
import com.jieshuizhibiao.waterindex.beans.ListTradeResponseBean;
import com.jieshuizhibiao.waterindex.beans.TradeListsResponseBean;
import com.jieshuizhibiao.waterindex.beans.UploadFileResponseBean;
import com.jieshuizhibiao.waterindex.beans.UserAuthInfo;
import com.jieshuizhibiao.waterindex.beans.UserDetailResponseBean;
import com.jieshuizhibiao.waterindex.beans.UserIndexResponseBean;
import com.jieshuizhibiao.waterindex.beans.UserInfoResponseBean;
import com.jieshuizhibiao.waterindex.beans.UserMoney;
import com.jieshuizhibiao.waterindex.beans.appeal.AppealResponse;
import com.jieshuizhibiao.waterindex.beans.cancel.CancelResponse;
import com.jieshuizhibiao.waterindex.beans.paid.BuyerPaidResponse;
import com.jieshuizhibiao.waterindex.beans.paid.SellerPaidResponse;
import com.jieshuizhibiao.waterindex.beans.succ.SuccResponse;
import com.jieshuizhibiao.waterindex.beans.unpay.BuyerUnpayResponse;
import com.jieshuizhibiao.waterindex.beans.unpay.SellerUnpayResponse;
import com.jieshuizhibiao.waterindex.http.bean.BaseEntity;
import com.jieshuizhibiao.waterindex.http.config.UrlConfig;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by WanghongHe on 2018/12/3 11:41.
 * post 对应@Body 和get 对应@Query
 */

public interface APIService {

    //登录接口
    @POST(UrlConfig.LOGIN)
    Observable<BaseEntity<LoginResponseBean>> login(@Body RequestBody requestBody);

//    @GET(UrlConfig.getMessageList)
//    Observable<BaseEntity<MessageBean>> getMessageList(@Query("currentPage") String currentPage, @Query("pageSize") String pageSize);  //消息列表

    //获取验证码
    @POST(UrlConfig.SEND_VER_CODE)
    Observable<BaseEntity> getSms(@Body RequestBody requestBody);

    //注册
    @POST(UrlConfig.REGISTER)
    Observable<BaseEntity<RegisterResponseBean>> register(@Body RequestBody requestBody);

    //忘记密码
    @POST(UrlConfig.FIND_PASS)
    Observable<BaseEntity> findPass(@Body RequestBody requestBody);

    //企业认证
    @POST(UrlConfig.COMPANY_AUTH)
    Observable<BaseEntity> companyAuth(@Body RequestBody body);

    //个人认证
    @POST(UrlConfig.PERSONAL_AUTH)
    Observable<BaseEntity> personalAuth(@Body RequestBody body);

    //修改密码
    @POST(UrlConfig.CHANGE_PASS)
    Observable<BaseEntity> changePass(@Body RequestBody requestBody);

    //订单详情
    @POST(UrlConfig.ORDER_DETAIL)
    Observable<BaseEntity<OrderDetailResponseBean>> orderDetail(@Body RequestBody requestBody);

    //洗地订单列表
    @POST(UrlConfig.ORDER_LIST)
    Observable<BaseEntity<OrderListsResponseBean>> orderList(@Body RequestBody requestBody);

    //成交明细
    @POST(UrlConfig.TRADE_DETAIL)
    Observable<BaseEntity> tradeDetail(@Body RequestBody requestBody);

    //交易中心
    @POST(UrlConfig.TRADE_CENTER)
    Observable<BaseEntity<TradeCenterResponseBean>> tradeCenter(@Body RequestBody requestBody);

    //用户撤单
    @POST(UrlConfig.TRADE_CANCEL)
    Observable<BaseEntity>cancle(@Body RequestBody body);

    //贡献节水量
    @POST(UrlConfig.TRADE_BUY)
    Observable<BaseEntity> buy(@Body RequestBody requestBody);

    //获取节水量
    @POST(UrlConfig.TRADE_SELL)
    Observable<BaseEntity<SellResponseBean>> sell(@Body RequestBody requestBody);

    //折线图
    @POST(UrlConfig.TRADE_LINE)
    Observable<BaseEntity<TradeLineResponseBean>> tradeLine(@Body RequestBody body);

    //个人中心
    @POST(UrlConfig.TRADE_LIST)
    Observable<BaseEntity<TradeListsResponseBean>> tradeList(@Body RequestBody requestBody);

    //用户身份认证信息
    @POST(UrlConfig.AUTH_DETAIL)
    Observable<BaseEntity<AuthDetailResponseBean>> authDetail(@Body RequestBody requestBody);

    //我的资产
    @POST(UrlConfig.USER_ACCOUNT)
    Observable<BaseEntity<AccountDetailResponseBean>> accountDetail(@Body RequestBody requestBody);

    //用户信息
    @POST(UrlConfig.USER_DETAIL)
    Observable<BaseEntity<UserDetailResponseBean>> userDetail(@Body RequestBody requestBody);

    //平台咨询详情
    @POST(UrlConfig.INFO_DETAIL)
    Observable<BaseEntity> infoDetail(@Body RequestBody requestBody);

    //平台咨询列表
    @POST(UrlConfig.INFO_LIST)
    Observable<BaseEntity<InfoListsResponseBean>> infoList(@Body RequestBody requestBody);

    //轮播列表
    @POST(UrlConfig.BANNER_LIST)
    Observable<BaseEntity<BannerListResponseBean>> bannerList(@Body RequestBody requestBody);

    //洗涤项目列表
    @POST(UrlConfig.SERVICE_LIST)
    Observable<BaseEntity<ServiceListResponseBean>> serviceList(@Body RequestBody requestBody);

    //洗涤商城企业列表
    @POST(UrlConfig.FACTORY_LIST)
    Observable<BaseEntity<List<FactoryListResponseBean>>> factoryList(@Body RequestBody requestBody);

    //洗涤企业详情
    @POST(UrlConfig.FACTORY_DETAIL)
    Observable<BaseEntity<FactoryDetailResponseBean>> factoryDetail(@Body RequestBody requestBody);

    //洗涤企业项目详情
    @POST(UrlConfig.FACTORY_SERVICE)
    Observable<BaseEntity<FactoryServiceResponseBean>> factoryService(@Body RequestBody requestBody);

    //下单支付总金额
    @POST(UrlConfig.TOTAL_PRICE)
    Observable<BaseEntity<TotalPriceResponseBean>> totalPrice(@Body RequestBody requestBody);

    //创建订单
    @POST(UrlConfig.CREATE_ORDER)
    Observable<BaseEntity<CreateOrderResponseBean>> createOrder(@Body RequestBody requestBody);

    //我的兑换
    @POST(UrlConfig.GOODS)
    Observable<BaseEntity<List<GoodsResposeBean>>> goods(@Body RequestBody requestBody);


    @POST(UrlConfig.SYSMSG)
    Observable<BaseEntity<SysMsgBase>> sysMsg(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     */
    @POST(UrlConfig.USER_MONEY)
    Observable<BaseEntity<UserMoney>> userMoney(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     * "type", "1";
     * "page", "1";
     * "page_size", "10";
     */
    @POST(UrlConfig.TRADE_INDEX)
    Observable<BaseEntity<TradeIndexBase>> tradeIndex(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     * "trade_id", "1";
     * "total", "1";
     */
    @POST(UrlConfig.USER_ORDER)
    Observable<BaseEntity> userOrder(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "type", "0";
     * "page", "1";
     * "page_size", "10";
     */
    @POST(UrlConfig.LISTORDER)
    Observable<BaseEntity<List<ListOrder>>> listOrder(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.BUYER_UNPAY)
    Observable<BaseEntity<BuyerUnpayResponse>>buyerUnpay(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.BUYER_PAID)
    Observable<BaseEntity<BuyerPaidResponse>>buyerPaid(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.BUYER_DO_CANCEL)
    Observable<BaseEntity> buyerCancle(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     * "detail","未收到卖家放行"
     */
    @POST(UrlConfig.BUYER_DO_APPEAL)
    Observable<BaseEntity>buyerDoAppeal(@Body RequestBody body);

    /**
     * "token":token
     * "device_type":"android"
     * "order_id":"111"
     * "pi_id":
     * "pay_snapshot":"asdfadfasdf"
     */
    @POST(UrlConfig.BUYER_DO_PAY)
    Observable<BaseEntity>buyerDoPay(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.BUYER_SUCC)
    Observable<BaseEntity<SuccResponse>>buyerSucc(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.BUYER_APPEAL)
    Observable<BaseEntity<AppealResponse>>buyerAppeal(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.BUYER_CANCEL)
    Observable<BaseEntity<CancelResponse>>buyerCancel(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.SELLER_UNPAY)
    Observable<BaseEntity<SellerUnpayResponse>>sellerUnpay(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.SELLER_PAID)
    Observable<BaseEntity<SellerPaidResponse>>sellerPaid(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     * "detail","未收到买家付款"
     */
    @POST(UrlConfig.SELLER_DO_APPEAL)
    Observable<BaseEntity>sellerDoAppeal(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     * "safe_pw","******"
     */
    @POST(UrlConfig.SELLER_CHECKOUT)
    Observable<BaseEntity>sellerCheckout(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.SELLER_SUCC)
    Observable<BaseEntity<SuccResponse>>sellerSucc(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.SELLER_APPEAL)
    Observable<BaseEntity<AppealResponse>>sellerAppeal(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "order_id", "111";
     */
    @POST(UrlConfig.SELLER_CANCEL)
    Observable<BaseEntity<CancelResponse>>sellerCancel(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "param", "ds_price";
     */
    @POST(UrlConfig.SYS_CONFIG)
    Observable<BaseEntity<SysConfigResponseBean>> sysConfig(@Body RequestBody body);


    /**
     * "token", token;
     * "device_type", "android";
     */
    @POST(UrlConfig.USER_AUTH_INFO)
    Observable<BaseEntity<UserAuthInfo>> userAuthInfo(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     * "file",File
     */
    @Multipart
    @POST(UrlConfig.UPLOAD)
    Observable<BaseEntity<UploadFileResponseBean>> uploadFile(
            @Query("token") String token,@Query("device_type") String device_type, @Part MultipartBody.Part file
    );


    /**
     * "token", token;
     * "device_type", "android";
     * "avatar","url"
     */
    @POST(UrlConfig.AVATAR)
    Observable<BaseEntity> setAvatar(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     */
    @POST(UrlConfig.USER_INFO)
    Observable<BaseEntity<UserInfoResponseBean>> userInfo(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     * "type", "0";
     * "page", "1";
     * "page_size", "10";
     * 买卖需求列表
     */
    @POST(UrlConfig.LIST_TRADE)
    Observable<BaseEntity<ListTradeResponseBean>> listTrade(@Body RequestBody requestBody);

    @POST(UrlConfig.GET_URL)
    Observable<BaseEntity>getUrl(@Body RequestBody body);

    /**
     * 修改资金密码
     * @param requestBody
     * @return
     */
    @POST(UrlConfig.CHANGE_CAPITAL_PASS)
    Observable<BaseEntity> changeCapitalPass(@Body RequestBody requestBody);

    /**
     * 设置资金密码
     * @param requestBody
     * @return
     */
    @POST(UrlConfig.SET_CAPITAL_PASS)
    Observable<BaseEntity> setCapitalPass(@Body RequestBody requestBody);

    /**
     * 用户首页
     */
    @POST(UrlConfig.USER_INDEX)
    Observable<BaseEntity<UserIndexResponseBean>> userIndex(@Body RequestBody requestBody);

    /**
     * 资产划转
     */
    @POST(UrlConfig.MOVE_MONERY)
    Observable<BaseEntity<MoveMoneryBean>> moveMonery(@Body RequestBody requestBody);

    /**
     * 收款方式--首页
     * token
     * device_type
     */
    @POST(UrlConfig.PAYMENT_TYPE)
    Observable<BaseEntity<PayMentResponseBean>> payMentType(@Body RequestBody requestBody);

    /**
     * 添加收款方式
     */
    @POST(UrlConfig.ADD_PAYMENT_TYPE)
    Observable<BaseEntity> addPayMentType(@Body RequestBody requestBody);

    /**
     * 收款方式 开启|禁用
     */
    @POST(UrlConfig.PAYMENT_TYPE_SWITCH)
    Observable<BaseEntity> payMentTypeSwitch(@Body RequestBody requestBody);

    /**
     * 收款方式 修改
     */
    @POST(UrlConfig.CHANGE_PAYMENT_TYPE)
    Observable<BaseEntity> changePayMentType(@Body RequestBody requestBody);

    /**
     * 买卖需求列表--发布
     */
    @POST(UrlConfig.ADD_TRADE)
    Observable<BaseEntity> addTrade(@Body RequestBody requestBody);

    /**
     * 买卖需求列表--下架
     */
    @POST(UrlConfig.DELETE_TRADE)
    Observable<BaseEntity> delTrade(@Body RequestBody requestBody);

    /**
     * 系统消息
     * "lists":[
     │             {
     │                 "id":6,
     │                 "add_time":"2018-12-08 17:47:49",
     │                 "content":"完成首次交易，系统赠送10.00000个JSL"
     │             },
     │             {
     │                 "id":4,
     │                 "add_time":"2018-12-08 16:49:11",
     │                 "content":"通过身份认证，系统赠送10.00000个JSL"
     │             },
     │             {
     │                 "id":2,
     │                 "add_time":"2018-12-08 16:46:24",
     │                 "content":"通过身份认证，系统赠送10.00000个JSL"
     │             },
     │             {
     │                 "id":1,
     │                 "add_time":"2018-12-08 16:44:50",
     │                 "content":"通过身份认证，系统赠送10.00000个JSL"
     │             }
     │         ]
     */
    @POST(UrlConfig.SYSTEM_MSSAGE)
    Observable<BaseEntity<SystemMsgResponseBean>> systemMsg(@Body RequestBody requestBody);

    /**
     * 资金划转钱确认:资产划转-划转前确认 参数和返回与资产划转一样!!!
     */
    @POST(UrlConfig.BEFORE_MOVE_MONERY)
    Observable<BaseEntity<MoveMoneryBean>> beforeMvMoney(@Body RequestBody requestBody);

}

