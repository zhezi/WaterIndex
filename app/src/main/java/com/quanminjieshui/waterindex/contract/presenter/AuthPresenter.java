package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.BaseBean;
import com.quanminjieshui.waterindex.beans.request.CompanyAuthReqParams;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.AuthModel;
import com.quanminjieshui.waterindex.contract.view.AuthViewImpl;

import java.util.Map;

public class AuthPresenter extends BasePresenter<AuthViewImpl> {

    private AuthModel model;

    public AuthPresenter(AuthModel model) {
        this.model = model;
    }

    public void auth(BaseActivity activity, boolean user_type, BaseBean params){
        if(model==null){
            model=new AuthModel();
        }
        model.auth(activity, user_type, params, new AuthModel.AuthCallback() {
            @Override
            public void onEdtContentsLegal() {
                if(mView!=null){
                    mView.onEdtContentsLegal();
                }
            }

            @Override
            public void onEdtContentsIllegal(Map<String, Boolean> verify) {
                if(mView!=null){
                    mView.onEdtContentsIllegal(verify);
                }
            }

            @Override
            public void onCompanyAuthSuccess() {
                if(mView!=null){
                    mView.onCompanyAuthSuccess();
                }
            }

            @Override
            public void onCompanyAuthFailed(String msg) {
                if(mView!=null){
                    mView.onCompanyAuthFailed(msg);
                }
            }

            @Override
            public void onPersonalAuthSuccess() {
                if(mView!=null){
                    mView.onPersonalAuthSuccess();
                }
            }

            @Override
            public void onPersonalAuthFailed(String msg) {
                if(mView!=null){
                    mView.onPersonalAuthFailed(msg);
                }
            }
        });
    }

}
