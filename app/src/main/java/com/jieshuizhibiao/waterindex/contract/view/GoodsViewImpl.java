/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.view
 * @ClassName: GoodsViewImpl
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:31 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.GoodsResposeBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

import java.util.List;

/**
 *
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.view
 * @ClassName: GoodsViewImpl
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:31 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface GoodsViewImpl extends IBaseViewImpl {
    void onGetGoodsSuccess(List<GoodsResposeBean> list);
    void onGetGoodsFailed(String msg);
}
