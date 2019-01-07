package com.quanminjieshui.waterindex.contract.model;

import com.google.gson.Gson;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.ServiceListResponseBean;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public class ServiceListModel {
    public void getSrviceList(BaseActivity activity,final ServiceListCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .serviceList(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity<ServiceListResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<ServiceListResponseBean>>io())
                .subscribe(new BaseObserver<ServiceListResponseBean>(activity) {

                    @Override
                    protected void onSuccess(ServiceListResponseBean serviceListResponseBean) throws Exception {
                        callBack.success(serviceListResponseBean.getLists());
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

    public interface ServiceListCallBack{
        void success(List<ServiceListResponseBean.serviceListEntity> serviceListEntity);
        void failed(String msg);
    }
}
