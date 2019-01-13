package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.MoveMoneryBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public interface BeforeMvMoneryViewImpl extends IBaseViewImpl {
    void onBeforeMvMonerySuccess(MoveMoneryBean moveMoneryBean);
    void onBeforeMvMoneryFailed(String msg);
}
