/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TradeLinePresenter
 * Author: sxt
 * Date: 2019/1/4 11:30 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.TradeLineResponseBean;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.TradeLineModel;
import com.jieshuizhibiao.waterindex.contract.view.TradeLineViewImpl;

/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.presenter
 * @ClassName: TradeLinePresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/4 11:30 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/4 11:30 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TradeLinePresenter extends BasePresenter<TradeLineViewImpl> {
    TradeLineModel tradeLineModel;

    public TradeLinePresenter(TradeLineModel tradeLineModel) {
        this.tradeLineModel=tradeLineModel;
    }


    public void getTradeLine(BaseActivity activity, String type) {
        if (tradeLineModel == null) {
            tradeLineModel = new TradeLineModel();
        }

        tradeLineModel.getTradeLine(activity, type, new TradeLineModel.TradeLineCallback() {
            @Override
            public void onTradeLineSuccess(TradeLineResponseBean tradeLineResponseBean) {
                if (mView != null) {
                    mView.onTradeLineSuccess(tradeLineResponseBean);
                }
            }

            @Override
            public void onTradeLineFailed(String msg) {
                if (mView != null) {
                    mView.onTradeLineFailed(msg);
                }
            }
        });

    }


}