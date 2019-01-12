package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.BaseBean;
import com.quanminjieshui.waterindex.beans.UserAuthInfo;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.UserAuthInfoModel;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:
 */

public class UserAuthInfoPresenter extends BasePresenter<CommonViewImpl> {

    private UserAuthInfoModel userAuthInfoModel;

    public UserAuthInfoPresenter(){}

    public UserAuthInfoPresenter(UserAuthInfoModel userAuthInfoModel){
        this.userAuthInfoModel = userAuthInfoModel;
    }

    public void userAuthInfo(BaseActivity activity, BaseBean params){
        if(userAuthInfoModel == null){
            userAuthInfoModel = new UserAuthInfoModel();
        }
        userAuthInfoModel.userAuthInfo(activity, params, new UserAuthInfoModel.UserAuthInfoCallBack() {
            @Override
            public void success(UserAuthInfo userAuthInfo) {
                if (mView!=null){
                    mView.onRequestSuccess(userAuthInfo);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onRequestFailed(msg);
                }

            }
        });
    }
}
