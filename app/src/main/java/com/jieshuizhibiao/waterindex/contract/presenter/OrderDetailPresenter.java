package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.OrderDetailResponseBean;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.OrderDetailModel;
import com.jieshuizhibiao.waterindex.contract.view.OrderDetailViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 16:15.
 */

public class OrderDetailPresenter extends BasePresenter<OrderDetailViewImpl> {

    private OrderDetailModel orderDetailModel;

    public OrderDetailPresenter(){}

    public OrderDetailPresenter(OrderDetailModel orderDetailModel){
        this.orderDetailModel = orderDetailModel;
    }

    public void orderDetail(BaseActivity activity,int id){
        if(orderDetailModel == null){
            orderDetailModel = new OrderDetailModel();
        }
        orderDetailModel.getOrderDetail(activity,id,new OrderDetailModel.OrderDetailCallBack() {
            @Override
            public void onOrderDetailSuccess(OrderDetailResponseBean orderDetailBeans) {
                if(mView!=null){
                    mView.onOrderDetailSuccess(orderDetailBeans);
                }
            }

            @Override
            public void onOrderDetailFailed(String msg) {
                if(mView!=null){
                    mView.onOrderDetailFailed(msg);
                }

            }
        });
    }
}
