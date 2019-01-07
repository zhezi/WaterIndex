package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.CreateOrderResponseBean;
import com.quanminjieshui.waterindex.beans.request.CreateOrderReqParams;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:创建订单
 */

public class CreateOrderModel {
    public void createOrder(BaseActivity activity, CreateOrderReqParams params,final CreateOrderCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .createOrder(RequestUtil.getRequestBeanBody(params,false))
                .compose(activity.<BaseEntity<CreateOrderResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<CreateOrderResponseBean>>io())
                .subscribe(new BaseObserver<CreateOrderResponseBean>(activity) {
                    @Override
                    protected void onSuccess(CreateOrderResponseBean createOrderResponseBean) throws Exception {
                        callBack.success(createOrderResponseBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.failed(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.failed(e.getMessage());
                            }
                        } else {
                            callBack.failed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.failed(msg);
                    }
                });
    }

    public interface CreateOrderCallBack{
        void success(CreateOrderResponseBean createOrderResponseBean);
        void failed(String msg);
    }
}
