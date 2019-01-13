package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.OrderListsResponseBean;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.OrderListsModel;
import com.quanminjieshui.waterindex.contract.model.callback.CommonCallback;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;
import com.quanminjieshui.waterindex.contract.view.OrderListsViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 17:39.
 */

public class OrderListsPresenter extends BasePresenter<CommonViewImpl> {

    private OrderListsModel orderListsModel;

    public OrderListsPresenter(OrderListsModel orderListsModel) {
        this.orderListsModel = orderListsModel;
    }

    public void getOrderList(BaseActivity activity, String type, String page, String page_size) {
        if (orderListsModel == null) {
            orderListsModel = new OrderListsModel();
        }
        orderListsModel.orderList(activity, type, page, page_size, new CommonCallback() {

            @Override
            public void onRequestSuccess(Object bean) {
                if (mView != null) {
                    mView.onRequestSuccess(bean);
                }
            }

            @Override
            public void onRequestFailed(String msg) {
                if (mView != null) {
                    mView.onRequestFailed(msg);
                }
            }
        });
    }
}
