package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.UserDetailResponseBean;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.UserDetailModel;
import com.jieshuizhibiao.waterindex.contract.view.UserDetailViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public class UserDetailPresenter extends BasePresenter<UserDetailViewImpl> {

    private UserDetailModel userDetailModel;

    public UserDetailPresenter(){}

    public UserDetailPresenter(UserDetailModel userDetailModel){
        this.userDetailModel = userDetailModel;
    }

    public void getUserDetail(BaseActivity activity){
        if(userDetailModel == null){
            userDetailModel = new UserDetailModel();
        }

        userDetailModel.getUserDetail(activity, new UserDetailModel.UserDetailCallBack() {
            @Override
            public void success(UserDetailResponseBean userDetailResponseBean) {
                if(mView!=null){
                    mView.onUserDetailSuccess(userDetailResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onUserDetailFailed(msg);
                }

            }
        });
    }
}
