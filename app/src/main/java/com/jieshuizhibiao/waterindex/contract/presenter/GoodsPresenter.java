/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.presenter
 * @ClassName: GoodsPresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:33 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:33 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.GoodsResposeBean;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.GoodsModel;
import com.jieshuizhibiao.waterindex.contract.view.GoodsViewImpl;

import java.util.List;

/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.presenter
 * @ClassName: GoodsPresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:33 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:33 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GoodsPresenter extends BasePresenter<GoodsViewImpl> {
    private GoodsModel goodsModel;


    public GoodsPresenter(GoodsModel goodsModel) {
        this.goodsModel = goodsModel;
    }

    public void getGoods(BaseActivity activity) {
        if (goodsModel == null) {
            goodsModel = new GoodsModel();
        }
        goodsModel.getGoods(activity, new GoodsModel.GoodsCallback() {
            @Override
            public void onGetGoodsSuccess(List<GoodsResposeBean> list) {
                if (mView != null) {
                    mView.onGetGoodsSuccess(list);
                }
            }

            @Override
            public void onGetGoodsFailed(String msg) {
                if (mView != null) {
                    mView.onGetGoodsFailed(msg);
                }
            }
        });

    }

}
