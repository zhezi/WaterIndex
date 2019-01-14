package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.ServiceListResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public interface ServiceListViewImpl extends IBaseViewImpl {
    void onServiceListSuccess(List<ServiceListResponseBean.serviceListEntity> serviceListEntities);
    void onServiceListFailed(String msg);
}
