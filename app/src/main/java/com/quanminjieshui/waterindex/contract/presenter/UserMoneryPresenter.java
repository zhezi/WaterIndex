package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.BaseBean;
import com.quanminjieshui.waterindex.beans.UserMoney;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.UserMoneryModel;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:
 */

public class UserMoneryPresenter extends BasePresenter<CommonViewImpl> {

    private UserMoneryModel userMoneryModel;

    public UserMoneryPresenter(){}

    public UserMoneryPresenter(UserMoneryModel userMoneryModel){
        this.userMoneryModel = userMoneryModel;
    }

    public void userMonery(BaseActivity activity, BaseBean params){
        if (userMoneryModel == null){
            userMoneryModel = new UserMoneryModel();
        }
        userMoneryModel.userMonery(activity, params, new UserMoneryModel.UserMoneryCallBack() {
            @Override
            public void success(UserMoney userMoney) {
                if (mView!=null){
                    mView.onRequestSuccess(userMoney);
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
