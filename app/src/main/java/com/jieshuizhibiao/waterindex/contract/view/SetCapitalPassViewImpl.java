package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/10.
 * Class Note:
 */

public interface SetCapitalPassViewImpl extends IBaseViewImpl {

    void onSetCapitalPassSuccess();
    void onSetCapitalPassFailed(String msg);
}
