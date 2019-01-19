package com.jieshuizhibiao.waterindex.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.beans.ListTradeResponseBean;
import com.jieshuizhibiao.waterindex.beans.request.ListTradeReqParams;
import com.jieshuizhibiao.waterindex.contract.presenter.ListTradePresenter;
import com.jieshuizhibiao.waterindex.contract.view.ListTradeViewImpl;
import com.jieshuizhibiao.waterindex.http.config.HttpConfig;
import com.jieshuizhibiao.waterindex.ui.adapter.TransactionAdapter;
import com.jieshuizhibiao.waterindex.ui.view.AlertChainDialog;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;
import com.jieshuizhibiao.waterindex.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/25.
 * Class Note:购买
 */

public class TranscationBuyFragment extends BaseFragment implements ListTradeViewImpl{

    @BindView(R.id.transaction_demand_buy)
    XRecyclerView transcationBuyList;
    @BindView(R.id.relative_hint)
    RelativeLayout relativeHint;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;
    private ListTradePresenter listTradePresenter;
    private int currentPage = 1, pageSize = 10;
    private TransactionAdapter transactionAdapter;
    private List<ListTradeResponseBean.TradeList> tradeListArrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listTradePresenter = new ListTradePresenter();
        listTradePresenter.attachView(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_transcation_buy,container,false);
        ButterKnife.bind(this, rootView);
        alertChainDialog = new AlertChainDialog(getBaseActivity());
        initView();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initView() {

        transactionAdapter = new TransactionAdapter(getBaseActivity(), tradeListArrayList, new TransactionAdapter.TransactionListener() {
            @Override
            public void onLowerShelfClick(String sn) {

            }
        });
        transcationBuyList.setArrowImageView(R.drawable.iconfont_downgrey);
        transcationBuyList.setLayoutManager(new LinearLayoutManager(getActivity()));
        transcationBuyList.addItemDecoration(new TranscationBuyFragment.SpaceItemDecoration(Util.px2px(getBaseActivity(), 10, 1334)));
        transcationBuyList.setAdapter(transactionAdapter);
        transcationBuyList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;//后期数据需要分页时再用
                doReuqest();
            }

            @Override
            public void onLoadMore() {
                currentPage++;
                doReuqest();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doReuqest();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            doReuqest();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            doReuqest();
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        doReuqest();
    }

    public void doReuqest(){
        ListTradeReqParams params = new ListTradeReqParams();
        params.setType(HttpConfig.TRANSCATION_RELEASE_BUY);
        params.setPage(String.valueOf(currentPage));
        params.setPage_size(String.valueOf(pageSize));
        listTradePresenter.getListTrade(getBaseActivity(),params);
        showLoadingDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listTradePresenter!=null){
            listTradePresenter.detachView();
        }
    }

    @Override
    public void onListTradeSuccess(ListTradeResponseBean bean) {

    }

    @Override
    public void onloadMoreListTrade(List<ListTradeResponseBean.TradeList> tradeLists) {
        dismissLoadingDialog();
        tradeListArrayList.addAll(tradeLists);
        transactionAdapter.notifyDataSetChanged();
        transcationBuyList.loadMoreComplete();

        if (tradeListArrayList.size() <= 0) {
            transcationBuyList.setVisibility(View.GONE);
            relativeHint.setVisibility(View.VISIBLE);
            tvDetail.setText("暂无内容！");
            tvDetail.setTextColor(getResources().getColor(R.color.text_black));
            tvDetail.setEnabled(false);
        } else {
            transcationBuyList.setVisibility(View.VISIBLE);
            relativeHint.setVisibility(View.GONE);
            tvDetail.setEnabled(false);
        }
    }

    @Override
    public void onRefreshListTrade(List<ListTradeResponseBean.TradeList> tradeLists) {
        dismissLoadingDialog();
        tradeListArrayList.clear();
        tradeListArrayList.addAll(tradeLists);
        transactionAdapter.notifyDataSetChanged();
        transcationBuyList.refreshComplete();

        if (tradeListArrayList.size() <= 0) {
            transcationBuyList.setVisibility(View.GONE);
            relativeHint.setVisibility(View.VISIBLE);
            tvDetail.setText("暂无内容！");
            tvDetail.setTextColor(getResources().getColor(R.color.text_black));
            tvDetail.setEnabled(false);
        } else {
            transcationBuyList.setVisibility(View.VISIBLE);
            relativeHint.setVisibility(View.GONE);
            tvDetail.setEnabled(false);
        }

    }

    @Override
    public void onLoadListTradeError(boolean isRefresh, String msg) {
        if (isRefresh) {
            transcationBuyList.refreshComplete();
        } else {
            transcationBuyList.loadMoreComplete();
        }
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg,0);
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.bottom = space;
            if (parent.getChildPosition(view) != 0) {
                outRect.top = space;
            }
        }
    }
}
