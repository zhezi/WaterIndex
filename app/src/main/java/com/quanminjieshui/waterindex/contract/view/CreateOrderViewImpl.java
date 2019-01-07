package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.CreateOrderResponseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/27.
 * Class Note:
 */

public interface CreateOrderViewImpl extends IBaseViewImpl {
    void onCreateOrderSuccess(CreateOrderResponseBean createOrderResponseBean);
    void onCreateOrderFailed(String msg);
}
