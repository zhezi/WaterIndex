package com.jieshuizhibiao.waterindex.http.config;

/**
 * Created by WanghongHe on 2018/12/3 12:23.
 */

public class UrlConfig {
    //登录
    /**
     *
     * {
     *      "code":1,
     *      "msg":"登录成功",
     *      "data":{
     *      "id":14,
     *      "is_blocked":0,
     *      "user_login":"18329257177",
     *      "avatar":"http:\/\/jsl.sshsky.com\/upload\/",
     *      "user_nickname":"",
     *      "token":"880486ea9371415e52b8ef0878b743fa7b4ea77fa1a8dd89ec9067c6e8a63129"
     * }
     */
    public final static String LOGIN = "/api/home/public/login";
    //登录
    /**
     * 获取短信验证码
     * {
     *      "code":1,
     *     "msg":"操作成功",
     *     "data":"4444"
     *  }
     */
    public final static String SEND_VER_CODE = "api/home/common/sendVercode";
    //登录
    /***
     * 注册
     *
     * {
     *     "code":1,
     *    "msg":"注册成功",
     *     "data":{
     *        "uid":"14",
     *        "user_login":"18329257177",
     *        "token":"d6bdbc551eb58413b80cd619cc9c4e65d19cedfb02ed04699cba16906f1c21a6"
     *    }
     * }
     */
    public final static String REGISTER = "api/home/public/register";
    //找回密码
    public static final String FIND_PASS = "api/home/public/findPass";
    //身份认证-企业
    public static final String COMPANY_AUTH = "api/home/public/companyAuth";
    //轮播图列表地址
    public static final String BANNER_LIST = "api/home/banner/bannerLists";
    //洗涤企业列表
    /**
     * 洗涤企业列表
     *
     *      {
     *          "code":1,
     *          "msg":"操作成功！",
     *          "data":[
     *              {
     *                  "id":1,
     *                  "f_name":"洗涤工厂",
     *                  "logo":"http:\/\/www.quanminjieshui.com\/upload\/fac\/20181202\/e3e8abc9439e5d42286449533770f8ed.jpg",
     *                  "service_area":"山东临沂市区内",
     *                  "service_lists":"酒店餐厅布草、餐具消毒类等"
     *              }
     *          ]
     *      }
     */
    public static final String FACTORY_LIST = "api/home/factory/factoryLists";
    //洗涤项目列表
    public static final String SERVICE_LIST = "api/home/common/serviceLists";
    //洗涤企业列表
    /**
     * 洗涤企业详情
     *      {
     *          "code":1,
     *          "msg":"操作成功",
     *          "data":{
     *              "detail":{
     *                  "id":1,
     *                  "description":"<p>临沂东海胜洁清洁服务有限公司现有员六十余人，管理人员均从业多年并持有国家认可的中级以上洗涤技师资格证书，所有员工均经过正规的岗前培训方可上岗。 东海胜洁清洁服务有限公司是经国家相关部门批准注册的企业。专业从事床单、被罩、浴巾、毛巾、桌布、客车座套及商务办公、家庭住宅的窗帘、床罩、毛毯、工作服等布草的洗涤(干洗、水洗）、烘干、消毒、熨烫的清洁 洗涤一条龙服务公司 。公司成立几年来，为多家中小型宾馆以及各大酒店、公司等提服务。<\/p>",
     *                  "img":"https:\/\/www.quanminjieshui.com\/upload\/fac\/20181202\/e3e8abc9439e5d42286449533770f8ed.jpg"
     *              },
     *              "service_lists":[
     *                  {
     *                      "s_name":"酒店餐厅布草",
     *                      "img":"https:\/\/www.quanminjieshui.com\/upload\/admin\/20181211\/4aadf0b4a721ffa8bc2dbc298bfa3c27.jpg",
     *                      "service_id":1,
     *                      "description":"节水 95%，专业洗涤",
     *                      "id":7
     *                  }
     *              ]
     *          }
     *      }
     */
    public static final String FACTORY_DETAIL = "api/home/factory/factoryDetail";
    //洗涤企业项目详情
    public static final String FACTORY_SERVICE = "api/home/factory/factoryService";
    //支付总金额
    public static final String TOTAL_PRICE = "api/home/factory/totalPrice";
    //创建订单
    public static final String CREATE_ORDER = "api/home/factory/addOrder";
    //洗涤企业列表
    /**
     *
     *     无token
     *      {
     *          "code":1,
     *          "msg":"",
     *          "data":{
     *              "cur_price":"0.00342",
     *              "price_limit":"--",
     *              "price_limit_color":"",
     *              "day_vol":"6829.73375",
     *              "trade_status":"休息",
     *              "trade_list":{
     *                  "buy":[
     *                      {
     *                          "total":"641.44527",
     *                          "price":"0.00342",
     *                          "name":"贡献1"
     *                      },
     *                      {
     *                          "total":"26.70361",
     *                          "price":"0.00340",
     *                          "name":"贡献2"
     *                      },
     *                      {
     *                          "total":"136.37764",
     *                          "price":"0.00339",
     *                          "name":"贡献3"
     *                      },
     *                      {
     *                          "total":"1169.39638",
     *                          "price":"0.00338",
     *                          "name":"贡献4"
     *                      },
     *                      {
     *                          "total":"2.72868",
     *                          "price":"0.00337",
     *                          "name":"贡献5"
     *                      }
     *                  ],
     *                  "sell":[
     *                      {
     *                          "total":"141.20675",
     *                          "price":"0.00316",
     *                          "name":"获取1"
     *                      },
     *                      {
     *                          "total":"618.71021",
     *                          "price":"0.00321",
     *                          "name":"获取2"
     *                      },
     *                      {
     *                          "total":"944.27701",
     *                          "price":"0.00322",
     *                          "name":"获取3"
     *                      },
     *                      {
     *                          "total":"311.60029",
     *                          "price":"0.00324",
     *                          "name":"获取4"
     *                      },
     *                      {
     *                          "total":"357.13491",
     *                          "price":"0.00335",
     *                          "name":"获取5"
     *                      }
     *                  ]
     *              },
     *              "trade_detail_list":[
     *                  {
     *                      "id":3447,
     *                      "add_time":"21:48:01",
     *                      "action_type":1,
     *                      "price":"0.00292",
     *                      "total":"16.32278",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3449,
     *                      "add_time":"21:49:01",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"3.30007",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3453,
     *                      "add_time":"21:52:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"0.14069",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3455,
     *                      "add_time":"21:52:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"9.30398",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3457,
     *                      "add_time":"21:53:01",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"0.83569",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3459,
     *                      "add_time":"21:54:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"102.59792",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3461,
     *                      "add_time":"21:54:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"1.30924",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3463,
     *                      "add_time":"21:54:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"30.08287",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3472,
     *                      "add_time":"21:58:01",
     *                      "action_type":1,
     *                      "price":"0.00329",
     *                      "total":"43.31933",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3474,
     *                      "add_time":"21:58:01",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"4.88370",
     *                      "type_name":"获取"
     *                  }
     *              ],
     *              "user_cur_trade":[
     *
     *              ],
     *              "user_history_trade":[
     *
     *              ],
     *              "user_account":{
     *                  "jsl":"0.00000",
     *                  "ds":"0.00000"
     *              },
     *              "line_chart":"0.00342",
     *              "is_login":0
     *          }
     *      }
     ***********************************************************************************************
     *     带token
     *      {
     *          "code":1,
     *          "msg":"",
     *          "data":{
     *              "cur_price":"0.00342",
     *              "price_limit":"--",
     *              "price_limit_color":"",
     *              "day_vol":"6599.96565",
     *              "trade_status":"休息",
     *              "trade_list":{
     *                  "buy":[
     *                      {
     *                          "total":"641.44527",
     *                          "price":"0.00342",
     *                          "name":"贡献1"
     *                      },
     *                      {
     *                          "total":"26.70361",
     *                          "price":"0.00340",
     *                          "name":"贡献2"
     *                      },
     *                      {
     *                          "total":"136.37764",
     *                          "price":"0.00339",
     *                          "name":"贡献3"
     *                      },
     *                      {
     *                          "total":"1169.39638",
     *                          "price":"0.00338",
     *                          "name":"贡献4"
     *                      },
     *                      {
     *                          "total":"2.72868",
     *                          "price":"0.00337",
     *                          "name":"贡献5"
     *                      }
     *                  ],
     *                  "sell":[
     *                      {
     *                          "total":"141.20675",
     *                          "price":"0.00316",
     *                          "name":"获取1"
     *                      },
     *                      {
     *                          "total":"618.71021",
     *                          "price":"0.00321",
     *                          "name":"获取2"
     *                      },
     *                      {
     *                          "total":"944.27701",
     *                          "price":"0.00322",
     *                          "name":"获取3"
     *                      },
     *                      {
     *                          "total":"311.60029",
     *                          "price":"0.00324",
     *                          "name":"获取4"
     *                      },
     *                      {
     *                          "total":"357.13491",
     *                          "price":"0.00335",
     *                          "name":"获取5"
     *                      }
     *                  ]
     *              },
     *              "trade_detail_list":[
     *                  {
     *                      "id":3447,
     *                      "add_time":"21:48:01",
     *                      "action_type":1,
     *                      "price":"0.00292",
     *                      "total":"16.32278",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3449,
     *                      "add_time":"21:49:01",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"3.30007",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3453,
     *                      "add_time":"21:52:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"0.14069",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3455,
     *                      "add_time":"21:52:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"9.30398",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3457,
     *                      "add_time":"21:53:01",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"0.83569",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3459,
     *                      "add_time":"21:54:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"102.59792",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3461,
     *                      "add_time":"21:54:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"1.30924",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3463,
     *                      "add_time":"21:54:02",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"30.08287",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3472,
     *                      "add_time":"21:58:01",
     *                      "action_type":1,
     *                      "price":"0.00329",
     *                      "total":"43.31933",
     *                      "type_name":"获取"
     *                  },
     *                  {
     *                      "id":3474,
     *                      "add_time":"21:58:01",
     *                      "action_type":1,
     *                      "price":"0.00342",
     *                      "total":"4.88370",
     *                      "type_name":"获取"
     *                  }
     *              ],
     *              "user_cur_trade":[
     *
     *              ],
     *              "user_history_trade":[
     *
     *              ],
     *              "user_account":{
     *                  "jsl":"1222.00000",
     *                  "ds":"1532.00000"
     *              },
     *              "line_chart":"0.00342",
     *              "is_login":1
     *          }
     *      }
     *
     */
    public static final String TRADE_CENTER = "api/home/trade/tradeCenter";
    //贡献节水指标
    public static final String TRADE_BUY = "api/home/trade/buy";
    //获取节水指标
    public static final String TRADE_SELL = "api/home/trade/sell";
    //用户撤单
    public static final String TRADE_CANCEL = "api/home/trade/cancel";
    //折线图
    /**
     *  {
     *          "code":1,
     *          "msg":"",
     *          "data":{
     *              "data":[
     *                  {
     *                      "price":"0.00411",
     *                      "tdate":"2019-01-04 10:01:00"
     *                  },
     *                  {
     *                      "price":"0.00411",
     *                      "tdate":"2019-01-04 10:02:00"
     *                  },
     *                  {
     *                      "price":"0.00407",
     *                      "tdate":"2019-01-04 10:03:00"
     *                  },
     *                  {
     *                      "price":"0.00407",
     *                      "tdate":"2019-01-04 10:04:00"
     *                  },
     *                  {
     *                      "price":"0.00430",
     *                      "tdate":"2019-01-04 10:05:00"
     *                  },
     *                  {
     *                      "price":"0.00430",
     *                      "tdate":"2019-01-04 10:05:00"
     *                  },
     *                  {
     *                      "price":"0.00429",
     *                      "tdate":"2019-01-04 10:06:00"
     *                  },
     *                  {
     *                      "price":"0.00429",
     *                      "tdate":"2019-01-04 10:06:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:07:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:08:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:09:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:09:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:10:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:11:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:12:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:13:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:14:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:15:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:15:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:16:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:16:00"
     *                  },
     *                  {
     *                      "price":"0.
     *     └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
     * 01-04 10:25:30.852 21339-21546/com.quanminjieshui.waterindex D/WaterIndex:
     *     ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
     *      00441",
     *                      "tdate":"2019-01-04 10:17:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:17:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:18:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:19:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:19:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:20:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:21:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:22:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:23:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:23:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:24:00"
     *                  },
     *                  {
     *                      "price":"0.00441",
     *                      "tdate":"2019-01-04 10:24:00"
     *                  },
     *                  {
     *                      "price":"0.00396",
     *                      "tdate":"2019-01-04 10:25:00"
     *                  }
     *              ],
     *              "xasix":[
     *                  "2019-01-04 10:00:00",
     *                  "2019-01-04 11:00:00",
     *                  "2019-01-04 12:00:00",
     *                  "2019-01-04 13:00:00",
     *                  "2019-01-04 14:00:00",
     *                  "2019-01-04 15:00:00",
     *                  "2019-01-04 16:00:00",
     *                  "2019-01-04 17:00:00",
     *                  "2019-01-04 18:00:00",
     *                  "2019-01-04 19:00:00",
     *                  "2019-01-04 20:00:00",
     *                  "2019-01-04 21:00:00",
     *                  "2019-01-04 22:00:00"
     *              ]
     *          }
     */
    public static final String TRADE_LINE="api/home/trade/tradeLine";
    //平台资讯列表
    /**
     *      {
     *          "code":1,
     *          "msg":"操作成功",
     *          "data":{
     *              "lists":[
     *                  {
     *                      "id":4,
     *                      "title":"12月24日节水资讯",
     *                      "img":"https:\/\/www.quanminjieshui.com\/upload\/admin\/20181214\/61865c5afa8cb1be3faf5d81647e2ca5.jpg",
     *                      "publishtime":"2018-12-27",
     *                      "content":"12月14日节水资讯"
     *                  },
     *                  {
     *                      "id":5,
     *                      "title":"12月25日节水资讯",
     *                      "img":"https:\/\/www.quanminjieshui.com\/upload\/admin\/20181214\/4db2ce83bfac13ae1fdfc1476a23a305.jpg",
     *                      "publishtime":"2018-12-27",
     *                      "content":"12月24日节水资讯"
     *                  },
     *                  {
     *                      "id":6,
     *                      "title":"12月26日节水资讯",
     *                      "img":"https:\/\/www.quanminjieshui.com\/upload\/admin\/20181227\/13771579cbe4a5b1fe32284e2c5769c1.jpg",
     *                      "publishtime":"2018-12-27",
     *                      "content":"12月24日节水资讯"
     *                  }
     *              ]
     *          }
     *      }
     */
    public static final String INFO_LIST = "api/home/info/infoLists";
    //平台咨询详情
    public static final String INFO_DETAIL = "api/home/info/infoDetail";
    //用户信息
    /**
     * 用户信息
     *      {
     *          "code":1,
     *          "msg":"操作成功",
     *          "data":{
     *              "user_login":"18329257177",
     *              "create_time":"2018-12-13",
     *              "avatar":"http:\/\/www.quanminjieshui.com\/upload\/",
     *              "user_type":"企业",
     *              "is_blocked":0,
     *              "user_status":1
     *          }
     *      }
     */
    public static final String USER_DETAIL = "api/home/user/userDetail";
    //用户信息
    /**
     * 我的资产
     *
     *      {
     *          "code":1,
     *          "msg":"操作成功",
     *          "data":{
     *              "jsl":"0.16908",
     *              "jsl_freeze":"0.00000",
     *              "ds":"0.42439",
     *              "ds_freeze":"0.00000",
     *              "jsl_lock_view":"0.00000",
     *              "jsl_gyj":"0.00000"
     *          }
     *      }
     */
    public static final String USER_ACCOUNT = "api/home/user/accountDetail";
    //用户身份证认证信息
    /**
     * 用户身份证认证信息
     *      {
     *          "code":1,
     *          "msg":"操作成功",
     *          "data":{
     *              "user_status":"待审核",
     *              "user_name":"",
     *              "id_no":"",
     *              "company_name":"公司名称",
     *              "company_license_no":"123456789",
     *              "user_type":2
     *          }
     *      }
     */
    public static final String AUTH_DETAIL = "api/home/user/authDetail";
    //个人中心-全部委托
    /**
     *  {
     *          "code":1,
     *          "msg":"操作成功",
     *          "data":{
     *              "lists":[
     *                  {
     *                      "id":7034,
     *                      "add_time":"2019-01-03 19:08",
     *                      "price":"0.00000",
     *                      "old_total":"6.43998",
     *                      "total":"6.43998",
     *                      "status":"等待成交",
     *                      "action_type":"贡献",
     *                      "deal_total":"0.00000",
     *                      "more":0,
     *                      "show_cancel":1,
     *                      "fee":"0.00000JSL",
     *                      "price_avg":"0.00000"
     *                  },
     *                  {
     *                      "id":7027,
     *                      "add_time":"2019-01-03 19:04",
     *                      "price":"0.00000",
     *                      "old_total":"122.35950",
     *                      "total":"122.35950",
     *                      "status":"等待成交",
     *                      "action_type":"贡献",
     *                      "deal_total":"0.00000",
     *                      "more":0,
     *                      "show_cancel":1,
     *                      "fee":"0.00000JSL",
     *                      "price_avg":"0.00000"
     *                  },
     *                  {
     *                      "id":7024,
     *                      "add_time":"2019-01-03 19:03",
     *                      "price":"0.00349",
     *                      "old_total":"51.51979",
     *                      "total":"51.51979",
     *                      "status":"等待成交",
     *                      "action_type":"贡献",
     *                      "deal_total":"0.00000",
     *                      "more":0,
     *                      "show_cancel":1,
     *                      "fee":"0.00000JSL",
     *                      "price_avg":"0.00000"
     *                  },
     *                  {
     *                      "id":7018,
     *                      "add_time":"2019-01-03 19:00",
     *                      "price":"0.00000",
     *                      "old_total":"131.90307",
     *                      "total":"0.00000",
     *                      "status":"全部成交",
     *                      "action_type":"获取",
     *                      "deal_total":"131.90307",
     *                      "more":1,
     *                      "show_cancel":0,
     *                      "fee":"0.00255T",
     *                      "price_avg":"0.00394"
     *                  },
     *                  {
     *                      "id":7017,
     *                      "add_time":"2019-01-03 18:59",
     *                      "price":"0.00000",
     *                      "old_total":"15.83471",
     *                      "total":"0.00000",
     *                      "status":"全部成交",
     *                      "action_type":"获取",
     *                      "deal_total":"15.83471",
     *                      "more":1,
     *                      "show_cancel":0,
     *                      "fee":"0.00029T",
     *                      "price_avg":"0.00406"
     *                  }
     *              ]
     *          }
     *      }
     */
    public static final String TRADE_LIST = "api/home/user/tradeLists";
    //交易明细
    public static final String TRADE_DETAIL = "api/home/user/tradeDetail";
    //洗涤订单
    /**
     * 洗涤订单
     *
     *      {
     *          "code":1,
     *          "msg":"操作成功",
     *          "data":{
     *              "lists":[
     *                  {
     *                      "fid":1,
     *                      "f_name":"洗涤工厂",
     *                      "s_name":"餐具消毒类",
     *                      "img":"http:\/\/www.quanminjieshui.com\/upload\/admin\/20181211\/4aadf0b4a721ffa8bc2dbc298bfa3c27.jpg",
     *                      "total_price":"2.00000",
     *                      "status":"已取消",
     *                      "id":48
     *                  }
     *              ]
     *          }
     *      }
     */
    public static final String ORDER_LIST = "api/home/user/orderLists";
    //洗涤订单
    /**
     * 订单详情   需要传订单id  文档中未提示
     *      {
     *          "code":1,
     *          "msg":"操作成功",
     *          "data":{
     *              "f_name":"洗涤工厂",
     *              "fid":1,
     *              "s_name":"餐具消毒类",
     *              "img":"http:\/\/www.quanminjieshui.com\/upload\/admin\/20181211\/4aadf0b4a721ffa8bc2dbc298bfa3c27.jpg",
     *              "total_price":"2.00000",
     *              "service_cate":[
     *                  {
     *                      "c_name":"方巾",
     *                      "total":"1",
     *                      "price":"2.00"
     *                  }
     *              ],
     *              "order_sn":"2018122757979754",
     *              "status":"已取消",
     *              "createtime":"2018-12-27 21:47:37",
     *              "pay_cate":"全额支付",
     *              "pay_type":"支付宝支付",
     *              "pay_price":"2.00000",
     *              "pay_jsl":"0.00000",
     *              "contact_name":"傅基本",
     *              "contact_tel":"15110067060",
     *              "express":"洗涤企业配送",
     *              "province":"湖北",
     *              "city":"武汉",
     *              "address":"湖北武汉雨花石",
     *              "pickup_time":"2018-12-12",
     *              "pay_sn":"",
     *              "id":48
     *          }
     *      }
     */
    public static final String ORDER_DETAIL = "api/home/user/orderDetail";
    /**
     * 修改密码--安全中心
     */
    public static final String CHANGE_PASS = "api/home/user/editPw";
    //洗涤订单
    /**
     * 我的-兑换记录
     *      实际请求：
     *      {
     *          "code":1,
     *          "msg":"操作成功",
     *          "data":[
     *
     *          ]
     *      }
     *
     *
     *      文档示例:
     *     {
     *     "code": "",
     *     "data": [
     *         {
     *             "order_sn": "8056040072755409",
     *             "g_name": "",
     *             "c_name": "",
     *             "total_price": "",
     *             "createtime": "",
     *             "status_view": ""
     *         }
     *     ],
     *     "msg": ""
     * }
     *
     *
     *
     */
    public static final String GOODS="api/home/user/goods";


    /**
     * 系统消息
     */
    public static final String SYSMSG="api/home/user/sysMsg";

    /**
     * 我的资产
     */
    public static final String USER_MONEY="api/home/user/money";

    /**
     * 交易市场
     *      {
     *          "code":1,
     *          "msg":"操作成功！",
     *          "data":{
     *              "total":17,
     *              "page":1,
     *              "list":[
     *                  {
     *                      "trade_id":7,
     *                      "trade_uid":37,
     *                      "avatar":"",
     *                      "user_nickname":"看你不爽",
     *                      "sold":"0",
     *                      "sold_rate":"0%",
     *                      "total":"300.00000T",
     *                      "pay_type_bank_card":"1",
     *                      "pay_type_alipay":"0",
     *                      "pay_type_wechat":"1",
     *                      "pay_min":"10.00000T",
     *                      "pay_timeout":0
     *                  },
     *                  {
     *                      "trade_id":12,
     *                      "trade_uid":52,
     *                      "avatar":"admin\/20190108\/907251bff177cd8052086d3e497e013a.jpg",
     *                      "user_nickname":"小白",
     *                      "sold":"0",
     *                      "sold_rate":"0%",
     *                      "total":"1.00000T",
     *                      "pay_type_bank_card":"1",
     *                      "pay_type_alipay":"1",
     *                      "pay_type_wechat":"1",
     *                      "pay_min":"2.00000T",
     *                      "pay_timeout":0
     *                  }
     *              ],
     *              "is_login":"0",
     *              "is_auth":0,
     *              "tip":"在开始交易之前，您需要完善必要的交易信息"
     *          }
     *      }
     */
    public static final String TRADE_INDEX="api/home/trade/index";

    /**
     * 用户下单
     *      {
     *          "code":1,
     *          "msg":"下单成功！",
     *          "data":"57"
     *      }
     */
    public static final String USER_ORDER="api/home/trade/userOrder";

    /**
     * 订单列表
     *      {
     *          "code":1,
     *          "msg":"订单列表",
     *          "data":[
     *              {
     *                  "my_action":"出售",
     *                  "other_uid":8,
     *                  "other_nickname":"bb",
     *                  "other_avatar":"222",
     *                  "createtime":"2018-12-13 18:55:00",
     *                  "status_text":"已完成",
     *                  "total":"3.10000T",
     *                  "rmb":"9.30000元",
     *                  "order_id":1,
     *                  "next_step":"sellerSucc"
     *              },
     *              {
     *                  "my_action":"出售",
     *                  "other_uid":8,
     *                  "other_nickname":"bb",
     *                  "other_avatar":"222",
     *                  "createtime":"2018-12-14 17:29:54",
     *                  "status_text":"已取消",
     *                  "total":"3.00000T",
     *                  "rmb":"9.00000元",
     *                  "order_id":3,
     *                  "next_step":"sellerCancel"
     *              },
     *              {
     *                  "my_action":"出售",
     *                  "other_uid":8,
     *                  "other_nickname":"bb",
     *                  "other_avatar":"222",
     *                  "createtime":"2018-12-17 19:44:55",
     *                  "status_text":"进行中",
     *                  "total":"3.00000T",
     *                  "rmb":"9.00000元",
     *                  "order_id":5,
     *                  "next_step":"sellerPaid"
     *              },
     *              {
     *                  "my_action":"出售",
     *                  "other_uid":52,
     *                  "other_nickname":"小白",
     *                  "other_avatar":"admin\/20190109\/25f10b6a8d35bf46968f42cb4acaa290.jpg",
     *                  "createtime":"2019-01-05 13:25:27",
     *                  "status_text":"申诉处理",
     *                  "total":"3.00000T",
     *                  "rmb":"9.00000元",
     *                  "order_id":21,
     *                  "next_step":"sellerAppeal"
     *              },
     *              {
     *                  "my_action":"购买",
     *                  "other_uid":8,
     *                  "other_nickname":"bb",
     *                  "other_avatar":"222",
     *                  "createtime":"2018-12-21 15:46:25",
     *                  "status_text":"已取消",
     *                  "total":"3.00000T",
     *                  "rmb":"9.00000元",
     *                  "order_id":6,
     *                  "next_step":"buyerCancel"
     *              }
     *          ]
     *      }
     */
    public static final String LISTORDER="api/home/userOrder/listOrder";

    /**
     * 买家-未付款
      {
          "code":1,
          "msg":"订单详情",
          "data":{
              "order_info":{
                  "title":"购买节水指标",
                  "expire_time":"不限制",
                  "rmb":"9.00000元",
                  "total":"3.00000T",
                  "price":"3.00000元\/T",
                  "order_sn":"20190110182211322408",
                  "pay_code":"322408",
                  "createtime":"2019-01-10 18:22:11",
                  "seller_avatar":"https:\/\/www.jieshuizhibiao.com\/upload\/admin\/20190109\/277a2b3341ebbf2a70bf755c0774361e.jpg",
                  "seller_nickname":"丁香满文轩",
                  "order_id":"59"
              },
              "pay_info_list":[
                  {
                      "id":10,
                      "uid":39,
                      "type":2,
                      "user_name":"萨文轩",
                      "bank_name":"",
                      "bank_detail_name":"",
                      "account_name":"18658900249",
                      "qrcode":"admin\/20190108\/ddbded5a8c787ea8108d77aadc25de2f.jpeg",
                      "add_time":1546142739,
                      "status":1
                  },
                  {
                      "id":14,
                      "uid":39,
                      "type":1,
                      "user_name":"萨文轩",
                      "bank_name":"北京银行",
                      "bank_detail_name":"朝阳支行",
                      "account_name":"6227001142040082275",
                      "qrcode":"",
                      "add_time":1546917730,
                      "status":1
                  }
              ]
          }
      }
     *
     */
    public static final String BUYER_UNPAY ="api/home/userOrder/buyerUnpay";

    /**
     * 买家-取消订单
     *      {
     *          "code":1,
     *          "msg":"取消成功",
     *          "data":""
     *      }
     */
    public static final String BUYER_CANCEL="api/home/userOrder/buyerCancel";

    /**
     * 卖家未付款
     *  {
     *          "code":1,
     *          "msg":"订单详情",
     *          "data":{
     *              "order_info":{
     *                  "title":"出售节水指标",
     *                  "expire_time":"不限制",
     *                  "rmb":"3.00000元",
     *                  "total":"1.00000T",
     *                  "price":"3.00000元\/T",
     *                  "order_sn":"20190110153159246886",
     *                  "pay_code":"246886",
     *                  "createtime":"2019-01-10 15:31:59",
     *                  "buyer_avatar":"https:\/\/www.jieshuizhibiao.com\/upload\/admin\/20190111\/2cb733cf51ef8fe5f9c955bc78919c6c.jpg",
     *                  "buyer_nickname":"石头",
     *                  "order_id":"57"
     *              },
     *              "pay_info_list":[
     *                  {
     *                      "id":2,
     *                      "uid":6,
     *                      "type":3,
     *                      "user_name":"",
     *                      "bank_name":"",
     *                      "bank_detail_name":"",
     *                      "account_name":"",
     *                      "qrcode":"",
     *                      "add_time":0,
     *                      "status":1
     *                  },
     *                  {
     *                      "id":3,
     *                      "uid":6,
     *                      "type":1,
     *                      "user_name":"",
     *                      "bank_name":"",
     *                      "bank_detail_name":"",
     *                      "account_name":"",
     *                      "qrcode":"",
     *                      "add_time":0,
     *                      "status":1
     *                  }
     *              ]
     *          }
     *      }
     */
    public static final String SELLER_UNPAY="api/home/userOrder/sellerUnpay";

    /**
     *基础信息    节水指标单价
     */
    public static final String SYS_CONFIG="api/home/common/sysConfig";

    /**
     * 身份认证
     */
    public static final String USER_AUTH_INFO="api/home/user/userAuthInfo";

    /**
     * 文件上传
     */
    public static final String UPLOAD="api/user/Upload/one";

    /**
     * 更换头像
     */
    public static final String AVATAR="api/home/user/setAvatar";

    /**
     * 基本资料
     */
    public static final String USER_INFO="api/home/user/info";

    /**
     *身份认证-个人
     */
    public static final String PERSONAL_AUTH = "api/home/user/userAuth";


    /**
     * 买卖需求 - 列表
     */
    public static final String LIST_TRADE = "api/home/userTrade/listTrade";
    /**
     * 获取H5链接
     */
    public static final String GET_URL="api/home/common/getUrl";

    /**
     * 修改资金密码
     */
    public static final String CHANGE_CAPITAL_PASS = "api/home/user/editSafePw";

    /**
     * 设置资金密码
     */
    public static final String SET_CAPITAL_PASS = "api/home/user/safePw";

    /**
     * 用户首页
     */
    public static final String USER_INDEX = "api/home/user/index";

    /**
     * 移交资产
     */
    public static final String MOVE_MONERY = "api/home/user/mvMoney";

    /**
     * 收款方式
     */
    public static final String PAYMENT_TYPE = "api/home/userPayInfo/index";

    /**
     * 添加收款方式
     */
    public static final String ADD_PAYMENT_TYPE = "api/home/userPayinfo/addPayInfo";

    /**
     * 收款方式 - 开启|禁用
     */
    public static final String PAYMENT_TYPE_SWITCH = "api/home/userPayinfo/switchPayInfo";

    /**
     * 收款方式 - 修改
     */
    public static final String CHANGE_PAYMENT_TYPE = "api/home/userPayinfo/editPayInfo";

    /**
     * 买卖需求-发布
     */
    public static final String ADD_TRADE = "api/home/userTrade/addTrade";

    /**
     * 买卖需求-下架
     */
    public static final String DELETE_TRADE = "api/home/userTrade/delTrade";

    /**
     * 系统消息
     */
    public static final String SYSTEM_MSSAGE = "api/home/user/sysMsg";

    /**
     * 资产划转-划转前确认
     */
    public static final String BEFORE_MOVE_MONERY = "api/home/user/beforeMvMoney";
}
