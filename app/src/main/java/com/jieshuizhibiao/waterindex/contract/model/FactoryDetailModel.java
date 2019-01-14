package com.jieshuizhibiao.waterindex.contract.model;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.FactoryDetailResponseBean;
import com.jieshuizhibiao.waterindex.http.BaseObserver;
import com.jieshuizhibiao.waterindex.http.RetrofitFactory;
import com.jieshuizhibiao.waterindex.http.bean.BaseEntity;
import com.jieshuizhibiao.waterindex.http.config.HttpConfig;
import com.jieshuizhibiao.waterindex.http.utils.ObservableTransformerUtils;
import com.jieshuizhibiao.waterindex.http.utils.RequestUtil;
import com.jieshuizhibiao.waterindex.utils.LogUtils;

import java.util.HashMap;

/**
 * Created by songxiaotao on 2018/12/21.
 * Class Note:洗涤企业详情
 */

public class FactoryDetailModel {
    /**
     * @param activity
     * @param id 洗涤企业id placeholder="非必填"
     */
    public void getFactoryDetail(BaseActivity activity,int id,final FactoryDetailCallBack callBack){
        HashMap<String ,Object> params = new HashMap<>();
        params.put("id",id);
        RetrofitFactory.getInstance().createService()
                .factoryDetail(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<FactoryDetailResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<FactoryDetailResponseBean>>io())
                .subscribe(new BaseObserver<FactoryDetailResponseBean>() {
                    @Override
                    protected void onSuccess(FactoryDetailResponseBean factoryDetailResponseBean) throws Exception {
                        callBack.success(factoryDetailResponseBean);
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

    public interface FactoryDetailCallBack{
        void success(FactoryDetailResponseBean factoryDetailResponseBean);
        void failed(String msg);
    }

}
