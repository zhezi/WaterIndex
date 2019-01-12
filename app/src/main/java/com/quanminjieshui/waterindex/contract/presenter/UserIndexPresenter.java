package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.BaseBean;
import com.quanminjieshui.waterindex.beans.UserIndexResponseBean;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.UserIndexModel;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:
 */

public class UserIndexPresenter extends BasePresenter<CommonViewImpl> {

    private UserIndexModel userIndexModel;

    public UserIndexPresenter(){}

    public UserIndexPresenter(UserIndexModel userIndexModel){
        this.userIndexModel = userIndexModel;
    }

    public void userIndex(BaseActivity activity, BaseBean params){
        if(userIndexModel == null){
            userIndexModel.usetIndex(activity, params, new UserIndexModel.UserIndexCallBack() {
                @Override
                public void success(UserIndexResponseBean bean) {
                    if (mView!=null){
                        mView.onRequestSuccess(bean);
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
}
