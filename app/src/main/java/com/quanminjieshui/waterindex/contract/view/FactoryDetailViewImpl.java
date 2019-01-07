package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.FactoryDetailResponseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/21.
 * Class Note:
 */

public interface FactoryDetailViewImpl extends IBaseViewImpl {
    void onFactoryDetailSuccess(FactoryDetailResponseBean factoryDetailResponseBean);
    void onFactoryDetailFailed(String msg);
}
