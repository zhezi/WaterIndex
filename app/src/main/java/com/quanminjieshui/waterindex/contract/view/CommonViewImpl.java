package com.quanminjieshui.waterindex.contract.view;

import com.quanminjieshui.waterindex.contract.IBaseViewImpl;

public interface CommonViewImpl  extends IBaseViewImpl {

    void onRequestSuccess(Object bean);
    void onRequestFailed(String msg);
}
