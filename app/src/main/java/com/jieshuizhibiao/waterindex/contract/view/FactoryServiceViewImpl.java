package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.FactoryServiceResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/22.
 * Class Note:
 */

public interface FactoryServiceViewImpl extends IBaseViewImpl {
    void onFactoryServiceSuceess(FactoryServiceResponseBean factoryServiceResponseBean);
    void onFactoryServiceFailed(String msg);
}
