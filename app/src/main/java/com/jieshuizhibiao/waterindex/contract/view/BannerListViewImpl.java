package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.BannerListResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by WanghongHe on 2018/12/13 14:42.
 */

public interface BannerListViewImpl extends IBaseViewImpl {
    void onBannerListSuccess(List<BannerListResponseBean.BannerListEntity> list);
    void onBannerListFailed(String msg);
}
