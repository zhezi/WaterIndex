package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.TradeDetailResponseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 18:26.
 */

public interface TradeDetailViewImpl extends IBaseViewImpl {
    void onTradeDetailSuccess(TradeDetailResponseBean tradeDetailResponseBean);
    void onTradeDetailFailed(String msg);
}
