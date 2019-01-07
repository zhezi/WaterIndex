package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.AuthDetailResponseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public interface AuthDetailViewImpl extends IBaseViewImpl {
    void authDetailSuccess(AuthDetailResponseBean authDetailResponseBean);
    void authDetailFailed(String msg);
}
