package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 16:13.
 */

public interface OrderDetailViewImpl extends IBaseViewImpl {
    void onOrderDetailSuccess(OrderDetailResponseBean orderDetailResponseBeans);
    void onOrderDetailFailed(String msg);
}
