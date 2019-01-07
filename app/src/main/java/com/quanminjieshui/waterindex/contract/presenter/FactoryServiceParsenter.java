package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.FactoryServiceModel;
import com.quanminjieshui.waterindex.contract.view.FactoryServiceViewImpl;

/**
 * Created by songxiaotao on 2018/12/22.
 * Class Note:
 */

public class FactoryServiceParsenter extends BasePresenter<FactoryServiceViewImpl> {

    private FactoryServiceModel factoryServiceModel;

    public FactoryServiceParsenter(){}

    public FactoryServiceParsenter(FactoryServiceModel factoryServiceModel){
        this.factoryServiceModel = factoryServiceModel;
    }

    public void getFactoryService(BaseActivity activity,int fsid){
        if(factoryServiceModel == null){
            factoryServiceModel = new FactoryServiceModel();
        }
        factoryServiceModel.getFactoryService(activity, fsid, new FactoryServiceModel.FactoryServiceCallBack() {
            @Override
            public void success(FactoryServiceResponseBean factoryServiceResponseBean) {
                if(mView!=null){
                    mView.onFactoryServiceSuceess(factoryServiceResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onFactoryServiceFailed(msg);
                }

            }
        });

    }
}
