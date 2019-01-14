package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.FactoryListResponseBean;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.FactoryListModel;
import com.jieshuizhibiao.waterindex.contract.view.FactoryListViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/20.
 * Class Note:
 */

public class FactoryListPresenter extends BasePresenter<FactoryListViewImpl> {

    private FactoryListModel factoryListModel;

    public FactoryListPresenter(){}

    public FactoryListPresenter(FactoryListModel factoryListModel){
        this.factoryListModel = factoryListModel;
    }

    /**
     * placeholder=0 默认值 必填
     * @param activity
     * @param count
     */
    public void getFactoryList(BaseActivity activity,int count){
        if(factoryListModel == null){
            factoryListModel = new FactoryListModel();
        }
        factoryListModel.getFactoryList(activity, count, new FactoryListModel.FactoryListCallBack() {
            @Override
            public void success(List<FactoryListResponseBean> factoryListEntities) {
                if(mView!=null){
                    mView.onFactoryListSuccess(factoryListEntities);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onFactoryListFailed(msg);
                }

            }
        });
    }
}