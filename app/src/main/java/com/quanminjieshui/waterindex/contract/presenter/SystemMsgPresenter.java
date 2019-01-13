package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.BaseBean;
import com.quanminjieshui.waterindex.beans.SystemMsgResponseBean;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.SystemtMsgModel;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class SystemMsgPresenter extends BasePresenter<CommonViewImpl> {

    private SystemtMsgModel systemtMsgModel;

    public SystemMsgPresenter(){}

    public SystemMsgPresenter(SystemtMsgModel systemtMsgModel){
        this.systemtMsgModel = systemtMsgModel;
    }

    public void getSystemMsg(BaseActivity activity, BaseBean params){
        if (systemtMsgModel == null){
            systemtMsgModel = new SystemtMsgModel();
        }
        systemtMsgModel.getSystemMsg(activity, params, new SystemtMsgModel.SystemMsgCallBack() {
            @Override
            public void success(SystemMsgResponseBean systemMsgResponseBean) {
                if (mView!=null){
                    mView.onRequestSuccess(systemMsgResponseBean);
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
