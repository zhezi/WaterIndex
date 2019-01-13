package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public interface DelTradeViewImpl extends IBaseViewImpl {
    void onDelTradeSuccess();
    void onDelTradeFailed(String msg);
}
