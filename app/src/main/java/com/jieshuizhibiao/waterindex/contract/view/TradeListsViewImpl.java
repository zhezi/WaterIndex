/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.view
 * @ClassName: TradeListsViewImpl
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:30 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:30 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.TradeListsResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 *
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.view
 * @ClassName: TradeListsViewImpl
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:30 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:30 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TradeListsViewImpl extends IBaseViewImpl {
    void onGetTradeListsSuccess(TradeListsResponseBean tradeListsResponseBean);
    void onGetTradeListsFailed(String msg);
}
