package com.quanminjieshui.waterindex.contract.model;

import android.text.TextUtils;

import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.WaterIndexApplication;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.request.SetCaptialPassReqParams;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.AccountValidatorUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songxiaotao on 2019/1/10.
 * Class Note:设置资金密码
 */

public class SetCapitalPassModel {

    private Map<String, Boolean> verifyResult = new HashMap<String, Boolean>();
    private WaterIndexApplication context = WaterIndexApplication.getInstance();

    public Map<String, Boolean> verify(SetCaptialPassReqParams params) {
        verifyResult.clear();
        if(!TextUtils.isEmpty(params.getSafe_pw())&&params.getSafe_pw().equals(params.getSafe_pw_re())&&AccountValidatorUtil.isPassword(params.getSafe_pw())&&AccountValidatorUtil.isPassword(params.getSafe_pw_re())){
            verifyResult.put(context.getString(R.string.key_edt_name_pwd), true);
        } else {
            verifyResult.put(context.getString(R.string.key_edt_name_pwd), false);
        }

        if(!TextUtils.isEmpty(params.getNick_name())){
            verifyResult.put(context.getString(R.string.key_edt_name_nike_name), true);
        }else{
            verifyResult.put(context.getString(R.string.key_edt_name_nike_name), false);
        }

        return verifyResult;
    }

    public void setCapitalPass(BaseActivity activity, SetCaptialPassReqParams params,final SetCaptialPassCallBack callBack){

        RetrofitFactory.getInstance().createService()
                .setCapitalPass(RequestUtil.getRequestBeanBody(params,false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {
                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callBack.success();
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

    public interface SetCaptialPassCallBack{
        void success();
        void failed(String msg);
    }
}
