package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.SysConfigResponseBean;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.UserOrderModel;
import com.quanminjieshui.waterindex.contract.model.callback.SencondRequestCallback;
import com.quanminjieshui.waterindex.contract.view.SecondRequstViewImpl;

public class SysConfigPresenter extends BasePresenter<SecondRequstViewImpl> {
    private UserOrderModel userOrderModel;

    public SysConfigPresenter(UserOrderModel userOrderModel) {
        this.userOrderModel = userOrderModel;
    }

    public void getSysConfig(BaseActivity activity) {
        if (userOrderModel == null) {
            userOrderModel = new UserOrderModel();
        }

        userOrderModel.getSysConfig(activity, new SencondRequestCallback() {
            @Override
            public void onSecondRequstSuccess(SysConfigResponseBean bean) {
                if (mView != null) {
                    mView.onSecondRequstSuccess(bean);
                }
            }

            @Override
            public void onSecondRequstFailed(String msg) {
                if (mView != null) {
                    mView.onSecondRequstFailed(msg);
                }
            }

        });
    }
}
