package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.util.HashMap;

/**
 * Created by songxiaotao on 2018/12/22.
 * Class Note:洗涤企业项目详情
 */

public class FactoryServiceModel {
    public void getFactoryService(BaseActivity activity,int fsid,final FactoryServiceCallBack callBack){

        HashMap<String,Object> params = new HashMap<>();
        params.put("fsid",fsid);
        RetrofitFactory.getInstance().createService()
                .factoryService(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<FactoryServiceResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<FactoryServiceResponseBean>>io())
                .subscribe(new BaseObserver<FactoryServiceResponseBean>() {
                    @Override
                    protected void onSuccess(FactoryServiceResponseBean factoryServiceResponseBean) throws Exception {
                        callBack.success(factoryServiceResponseBean);
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

    public interface FactoryServiceCallBack{
        void success(FactoryServiceResponseBean factoryServiceResponseBean);
        void failed(String msg);
    }
}
