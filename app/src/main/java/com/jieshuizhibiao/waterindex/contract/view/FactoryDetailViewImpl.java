package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.FactoryDetailResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/21.
 * Class Note:
 */

public interface FactoryDetailViewImpl extends IBaseViewImpl {
    void onFactoryDetailSuccess(FactoryDetailResponseBean factoryDetailResponseBean);
    void onFactoryDetailFailed(String msg);
}
