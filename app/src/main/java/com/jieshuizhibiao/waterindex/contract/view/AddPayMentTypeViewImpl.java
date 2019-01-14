package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public interface AddPayMentTypeViewImpl extends IBaseViewImpl {
    void onAddPaymentTypeSuccess();
    void onAddPaymentTypeFailed(String msg);
}
