package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.CreateOrderResponseBean;
import com.jieshuizhibiao.waterindex.beans.request.CreateOrderReqParams;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.CreateOrderModel;
import com.jieshuizhibiao.waterindex.contract.view.CreateOrderViewImpl;

/**
 * Created by songxiaotao on 2018/12/27.
 * Class Note:
 */

public class CreateOrderPresenter extends BasePresenter<CreateOrderViewImpl> {

    private CreateOrderModel createOrderModel;

    public CreateOrderPresenter(){}

    public CreateOrderPresenter(CreateOrderModel createOrderModel){
        this.createOrderModel = createOrderModel;
    }

    public void createOrder(BaseActivity activity, CreateOrderReqParams params){
        if(createOrderModel == null){
            createOrderModel = new CreateOrderModel();
        }
        createOrderModel.createOrder(activity,params, new CreateOrderModel.CreateOrderCallBack() {
            @Override
            public void success(CreateOrderResponseBean createOrderResponseBean) {
                if (mView!=null){
                    mView.onCreateOrderSuccess(createOrderResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onCreateOrderFailed(msg);
                }

            }
        });

    }
}
