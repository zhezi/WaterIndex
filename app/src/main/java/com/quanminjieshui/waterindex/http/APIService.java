package com.quanminjieshui.waterindex.http;

import com.quanminjieshui.waterindex.beans.AccountDetailResponseBean;
import com.quanminjieshui.waterindex.beans.AuthDetailResponseBean;
import com.quanminjieshui.waterindex.beans.BannerListResponseBean;
import com.quanminjieshui.waterindex.beans.BuyResponseBean;
import com.quanminjieshui.waterindex.beans.CreateOrderResponseBean;
import com.quanminjieshui.waterindex.beans.FactoryDetailResponseBean;
import com.quanminjieshui.waterindex.beans.FactoryListResponseBean;
import com.quanminjieshui.waterindex.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterindex.beans.GoodsResposeBean;
import com.quanminjieshui.waterindex.beans.InfoListsResponseBean;
import com.quanminjieshui.waterindex.beans.LoginResponseBean;
import com.quanminjieshui.waterindex.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterindex.beans.OrderListsResponseBean;
import com.quanminjieshui.waterindex.beans.RegisterResponseBean;
import com.quanminjieshui.waterindex.beans.SellResponseBean;
import com.quanminjieshui.waterindex.beans.ServiceListResponseBean;
import com.quanminjieshui.waterindex.beans.TotalPriceResponseBean;
import com.quanminjieshui.waterindex.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterindex.beans.TradeLineResponseBean;
import com.quanminjieshui.waterindex.beans.TradeListsResponseBean;
import com.quanminjieshui.waterindex.beans.UserDetailResponseBean;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.UrlConfig;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
     * "type", "0";
     * "page", "1";
     * "page_size", "10";
     */
    @POST(UrlConfig.LISTORDER)
    Observable<BaseEntity<List<ListOrder>>> listOrder(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     */
    @POST(UrlConfig.MONEY)
    Observable<BaseEntity<UserMoney>> money(@Body RequestBody requestBody);

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
     */
    @POST(UrlConfig.USER_AUTH_INFO)
    Observable<BaseEntity<UserAuthInfo>> userAuthInfo(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     * "file",File
     */
    @POST(UrlConfig.UPLOAD)
    Observable<BaseEntity<UploadOne>> upload(@Body RequestBody requestBody);

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
    Observable<BaseEntity<UserInfo>> userInfo(@Body RequestBody requestBody);

    /**
     * "token", token;
     * "device_type", "android";
     * "type", "0";
     * "page", "1";
     * "page_size", "10";
     */
    @POST(UrlConfig.LIST_TRADE)
    Observable<BaseEntity<TradeListBase>> listTrade(@Body RequestBody requestBody);

}

