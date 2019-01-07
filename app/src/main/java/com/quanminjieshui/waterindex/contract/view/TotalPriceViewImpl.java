package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.TotalPriceResponseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:
 */

public interface TotalPriceViewImpl extends IBaseViewImpl {
    void onTotalPriceSuccess(TotalPriceResponseBean totalPriceResponseBean);
    void onTotalPriceFailed(String msg);
}
