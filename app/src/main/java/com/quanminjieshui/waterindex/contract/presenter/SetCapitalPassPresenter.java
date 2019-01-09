package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.request.SetCaptialPassReqParams;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.SetCapitalPassModel;
import com.quanminjieshui.waterindex.contract.view.SetCapitalPassViewImpl;

/**
 * Created by songxiaotao on 2019/1/10.
 * Class Note:
 */

public class SetCapitalPassPresenter extends BasePresenter<SetCapitalPassViewImpl> {

    private SetCapitalPassModel setCapitalPassModel;

    public void SetCapitalPassPresenter(){}

    public void SetCapitalPassPresenter(SetCapitalPassModel setCapitalPassModel){
        this.setCapitalPassModel = setCapitalPassModel;
    }
    public void SetCapitalPass(BaseActivity activity, SetCaptialPassReqParams params){
        if(setCapitalPassModel==null){
            setCapitalPassModel = new SetCapitalPassModel();
        }
        setCapitalPassModel.setCapitalPass(activity, params, new SetCapitalPassModel.SetCaptialPassCallBack() {
            @Override
            public void success() {
                if(mView!=null){
                    mView.onSetCapitalPassSuccess();
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onSetCapitalPassFailed(msg);
                }

            }
        });
    }
}
