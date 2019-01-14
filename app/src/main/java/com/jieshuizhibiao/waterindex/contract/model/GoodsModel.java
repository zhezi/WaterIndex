/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.model
 * @ClassName: GoodsModel
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:15 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:15 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.jieshuizhibiao.waterindex.contract.model;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.GoodsResposeBean;
import com.jieshuizhibiao.waterindex.http.BaseObserver;
import com.jieshuizhibiao.waterindex.http.RetrofitFactory;
import com.jieshuizhibiao.waterindex.http.bean.BaseEntity;
import com.jieshuizhibiao.waterindex.http.config.HttpConfig;
import com.jieshuizhibiao.waterindex.http.utils.ObservableTransformerUtils;
import com.jieshuizhibiao.waterindex.http.utils.RequestUtil;
import com.jieshuizhibiao.waterindex.utils.LogUtils;

import java.util.List;

/**
 *
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.model
 * @ClassName: GoodsModel
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:15 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:15 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GoodsModel {

    public void getGoods(BaseActivity activity, final GoodsCallback callback){

        RetrofitFactory.getInstance().createService()
                .goods(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity<List<GoodsResposeBean>>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<List<GoodsResposeBean>>>io())
                .subscribe(new BaseObserver<List<GoodsResposeBean>>(activity) {
                    @Override
                    protected void onSuccess(List<GoodsResposeBean> goodsResposeBeans) throws Exception {
                        callback.onGetGoodsSuccess(goodsResposeBeans);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onGetGoodsFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onGetGoodsFailed(e.getMessage());
                            }
                        } else {
                            callback.onGetGoodsFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onGetGoodsFailed(msg);
                    }
                });
    }


    public interface GoodsCallback{
        void onGetGoodsSuccess(List<GoodsResposeBean> list);
        void onGetGoodsFailed(String msg);
    }

}
