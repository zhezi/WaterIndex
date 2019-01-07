package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.InfoDetailRespoonseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public interface InfoDetailViewImpl extends IBaseViewImpl {
    void infoDetailSuccess(InfoDetailRespoonseBean infoDetailRespoonseBean);
    void infoDetailFailed(String msg);
}
