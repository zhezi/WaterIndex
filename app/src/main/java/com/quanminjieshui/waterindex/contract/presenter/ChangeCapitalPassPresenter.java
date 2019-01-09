package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.request.ChangeCapitalPassReqParams;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.ChangeCapitalPassModel;
import com.quanminjieshui.waterindex.contract.view.ChangeCapitalPassViewImpl;

/**
 * Created by songxiaotao on 2019/1/10.
 * Class Note:
 */

public class ChangeCapitalPassPresenter extends BasePresenter<ChangeCapitalPassViewImpl> {

    private ChangeCapitalPassModel changeCapitalPassModel;

    public void ChangeCapitalPassPresenter(){}

    public void ChangeCapitalPassPresenter(ChangeCapitalPassModel changeCapitalPassModel){
        this.changeCapitalPassModel = changeCapitalPassModel;
    }
    public void changeCapitalPass(BaseActivity activity, ChangeCapitalPassReqParams params){
        if(changeCapitalPassModel==null){
            changeCapitalPassModel = new ChangeCapitalPassModel();
        }
        changeCapitalPassModel.changeCapitalPass(activity, params, new ChangeCapitalPassModel.ChangeCapitalPassCallBack() {
            @Override
            public void success() {
                if(mView!=null){
                    mView.onChangeCapitalPassSuccess();
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onChangeCapitalPassFailed(msg);
                }

            }
        });
    }
}
