package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.beans.SysConfigResponseBean;
import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

public interface SecondRequstViewImpl extends IBaseViewImpl {
    void onSecondRequstSuccess(SysConfigResponseBean bean);
    void onSecondRequstFailed(String msg);
}
