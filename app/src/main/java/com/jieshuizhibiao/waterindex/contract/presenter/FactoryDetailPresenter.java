package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.FactoryDetailResponseBean;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.FactoryDetailModel;
import com.jieshuizhibiao.waterindex.contract.view.FactoryDetailViewImpl;

/**
 * Created by songxiaotao on 2018/12/21.
 * Class Note:
 */

public class FactoryDetailPresenter extends BasePresenter<FactoryDetailViewImpl> {

    private FactoryDetailModel factoryDetailModel;

    public FactoryDetailPresenter(){}

    public FactoryDetailPresenter(FactoryDetailModel factoryDetailModel){
        this.factoryDetailModel = factoryDetailModel;
    }

    public void getFactoryDetail(BaseActivity activity,int id){
        if(factoryDetailModel == null){
            factoryDetailModel = new FactoryDetailModel();
        }
        factoryDetailModel.getFactoryDetail(activity, id, new FactoryDetailModel.FactoryDetailCallBack() {
            @Override
            public void success(FactoryDetailResponseBean factoryDetailResponseBean) {
                if (mView!=null){
                    mView.onFactoryDetailSuccess(factoryDetailResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onFactoryDetailFailed(msg);
                }

            }
        });
    }
}
