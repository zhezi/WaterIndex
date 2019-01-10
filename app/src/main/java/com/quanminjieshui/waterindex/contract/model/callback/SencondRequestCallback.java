package com.quanminjieshui.waterindex.contract.model.callback;

import com.quanminjieshui.waterindex.beans.SysConfigResponseBean;

public interface SencondRequestCallback {
    void onSecondRequstSuccess(SysConfigResponseBean bean);
    void onSecondRequstFailed(String msg);
}
