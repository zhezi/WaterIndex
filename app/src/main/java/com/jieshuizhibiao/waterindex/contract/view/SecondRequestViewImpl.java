package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.SysConfigResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

public interface SecondRequestViewImpl extends IBaseViewImpl {
    void onSecondRequstSuccess(Object bean);
    void onSecondRequstFailed(String msg);
}
