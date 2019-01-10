package com.quanminjieshui.waterindex.contract.model.callback;

public interface CommomCallback {

    void onRequestSuccess(Object bean);
    void onRequestFailed(String msg);
}
