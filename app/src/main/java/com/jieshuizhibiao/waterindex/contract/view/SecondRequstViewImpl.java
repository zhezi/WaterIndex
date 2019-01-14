package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.SysConfigResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

public interface SecondRequstViewImpl extends IBaseViewImpl {
    void onSecondRequstSuccess(SysConfigResponseBean bean);
    void onSecondRequstFailed(String msg);
}
