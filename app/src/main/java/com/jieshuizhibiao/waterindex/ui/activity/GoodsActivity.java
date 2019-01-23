/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.ui.activity
 * @ClassName: GoodsActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:40 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:40 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.jieshuizhibiao.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.GoodsResposeBean;
import com.jieshuizhibiao.waterindex.contract.model.GoodsModel;
import com.jieshuizhibiao.waterindex.contract.presenter.GoodsPresenter;
import com.jieshuizhibiao.waterindex.contract.view.GoodsViewImpl;
import com.jieshuizhibiao.waterindex.ui.adapter.GoodsAdapter;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.ui.activity
 * @ClassName: GoodsActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:40 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:40 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GoodsActivity extends BaseActivity implements GoodsViewImpl {


    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.xrv)
    XRecyclerView xrv;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.relative_hint)
    RelativeLayout relativeHint;
    private GoodsPresenter goodsPresenter;
    private GoodsAdapter goodsAdapter;
    private List<GoodsResposeBean> list;

    @OnClick({R.id.img_title_left})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_title_left:
                goBack(v);
                finish();
                break;
            default:
                break;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);

        goodsPresenter = new GoodsPresenter(new GoodsModel());
        goodsPresenter.attachView(this);
//        showLoadingDialog();
        goodsPresenter.getGoods(this);

        initView();
    }

    private void initView() {
        tvTitleCenter.setText("我的兑换");
        list = new ArrayList<>();
        goodsAdapter = new GoodsAdapter(this, list);
        xrv.setAdapter(goodsAdapter);
        xrv.setLoadingMoreEnabled(false);
//        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
//        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadMore() {
//
//            }
//        });
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_goods);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        if (goodsPresenter != null) {
            showLoadingDialog();
            goodsPresenter.getGoods(this);
        }
    }

    @Override
    public void onGetGoodsSuccess(List<GoodsResposeBean> list) {
        dismissLoadingDialog();
        if (list != null) {
            if (list.size() == 0) {
                relativeHint.setVisibility(View.VISIBLE);
                xrv.setVisibility(View.GONE);
                tvDetail.setText("您还没有兑换过任何物品！");
            } else {
                relativeHint.setVisibility(View.GONE);
                xrv.setVisibility(View.VISIBLE);
                this.list = list;
                goodsAdapter.notifyDataSetChanged();
                xrv.refreshComplete();
            }
        }
    }

    @Override
    public void onGetGoodsFailed(String msg) {
        dismissLoadingDialog();
        relativeHint.setVisibility(View.VISIBLE);
        xrv.setVisibility(View.GONE);
    }
}
