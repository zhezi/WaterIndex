package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.FactoryListResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/20.
 * Class Note:
 */

public interface FactoryListViewImpl extends IBaseViewImpl {
    void onFactoryListSuccess(List<FactoryListResponseBean> factoryListEntities);
    void onFactoryListFailed(String msg);
}
