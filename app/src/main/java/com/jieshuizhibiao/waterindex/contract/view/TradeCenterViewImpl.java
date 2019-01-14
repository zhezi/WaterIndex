package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.TradeCenterResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 * Created by sxt on 2018/12/31 15:17.
 */

public interface TradeCenterViewImpl extends IBaseViewImpl {
    void onTradeCenterSuccess(TradeCenterResponseBean tradeCenterResponseBean);
    void onTradeCenterFailed(String msg);

    void onBuySuccess(Object o);
    void onBuyFailed(String msg);

    void onSellSuccess(Object o);
    void onSellFailed(String msg);
}
