package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.InfoListsResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public interface InfoListViewImpl extends IBaseViewImpl {
    void infoListSuccess(InfoListsResponseBean infoListResponseBean);
    void infoListFailed(String msg);
}
