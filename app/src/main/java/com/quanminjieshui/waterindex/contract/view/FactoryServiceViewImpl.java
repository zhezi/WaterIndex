package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/22.
 * Class Note:
 */

public interface FactoryServiceViewImpl extends IBaseViewImpl {
    void onFactoryServiceSuceess(FactoryServiceResponseBean factoryServiceResponseBean);
    void onFactoryServiceFailed(String msg);
}
