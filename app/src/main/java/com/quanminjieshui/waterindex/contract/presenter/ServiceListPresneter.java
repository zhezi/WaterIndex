package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.ServiceListResponseBean;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.ServiceListModel;
import com.quanminjieshui.waterindex.contract.view.ServiceListViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public class ServiceListPresneter extends BasePresenter<ServiceListViewImpl> {

    private ServiceListModel serviceListModel;

    public ServiceListPresneter(){}

    public ServiceListPresneter(ServiceListModel serviceListModel){
        this.serviceListModel = serviceListModel;
    }

    public void getServiceList(BaseActivity activity){
        if(serviceListModel == null){
            serviceListModel = new ServiceListModel();
        }
        serviceListModel.getSrviceList(activity, new ServiceListModel.ServiceListCallBack() {
            @Override
            public void success(List<ServiceListResponseBean.serviceListEntity> serviceListEntity) {
                if(mView!=null){
                    mView.onServiceListSuccess(serviceListEntity);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onServiceListFailed(msg);
                }

            }
        });
    }
}
