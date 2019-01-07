package com.quanminjieshui.waterindex.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.InfoDetailRespoonseBean;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.util.HashMap;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:平台咨询详情
 */

public class InfoDetailModel {

    public void getInfoDetail(BaseActivity activity, int id, final InfoDetailCallBack callBack){
        HashMap<String,Object> params = new HashMap<>();
        params.put("id",id);
        RetrofitFactory.getInstance().createService()
                .infoDetail(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {

                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        Gson gson = new Gson();
                        InfoDetailRespoonseBean infoDetailBean = gson.fromJson((JsonElement) o,new TypeToken<InfoDetailRespoonseBean>() {}.getType());
                        callBack.success(infoDetailBean);
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

    public interface InfoDetailCallBack{
        void success(InfoDetailRespoonseBean infoDetailRespoonseBean);
        void failed(String msg);
    }
}
