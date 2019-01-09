package com.quanminjieshui.waterindex.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.beans.TradeIndex;
import com.quanminjieshui.waterindex.beans.TradeIndexBase;
import com.quanminjieshui.waterindex.contract.model.TradeIndexModel;
import com.quanminjieshui.waterindex.contract.presenter.TradeIndexPresenter;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;
import com.quanminjieshui.waterindex.event.LoginStatusChangedEvent;
import com.quanminjieshui.waterindex.ui.activity.AuthActivity;
import com.quanminjieshui.waterindex.ui.activity.LoginActivity;
import com.quanminjieshui.waterindex.ui.adapter.TradeIndexAdapter;
import com.quanminjieshui.waterindex.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TradeIndexFragment extends BaseFragment implements CommonViewImpl {

    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.ll_warning)
    LinearLayout llWarning;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.btn_sell)
    Button btnSell;
    @BindView(R.id.xrv_buy)
    XRecyclerView xrvBuy;
    @BindView(R.id.xrv_sell)
    XRecyclerView xrvSell;
    private Unbinder unbinder;
    private String type = "2";//购买2 出售1
    private TradeIndexPresenter tradeIndexPresenter;
    private int buyCounter = 0;//buy返回数据页数统计
    private int sellCounter = 0;//sell返回数据页数统计
    private List<TradeIndex> buyList = new ArrayList<>();
    private List<TradeIndex> sellList = new ArrayList<>();
    private TradeIndexAdapter buyAdapter;
    private TradeIndexAdapter sellAdapter;
    private String is_login;//是否登录   "1"登录   "0"未登录
    private int is_auth;//是否注册  1是认证中 3是已认证 0是未认证

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trade_index, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        tradeIndexPresenter = new TradeIndexPresenter(new TradeIndexModel());
        tradeIndexPresenter.attachView(this);
        doRequest();

        initView();
        return rootView;
    }

    private void initView() {
        buyAdapter = new TradeIndexAdapter(getActivity(), buyList, "2");
        sellAdapter = new TradeIndexAdapter(getActivity(), sellList, "1");
        xrvBuy.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrvSell.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrvBuy.setPullRefreshEnabled(true);
        xrvSell.setPullRefreshEnabled(true);
        xrvBuy.setLoadingMoreEnabled(true);
        xrvSell.setLoadingMoreEnabled(true);
        xrvBuy.setArrowImageView(R.drawable.iconfont_downgrey);
        xrvSell.setArrowImageView(R.drawable.iconfont_downgrey);
        xrvBuy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                buyCounter = 0;
                buyList.clear();
                doRequest();
                xrvBuy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                doRequest();
                xrvBuy.loadMoreComplete();
            }
        });
        xrvSell.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                sellCounter = 0;
                sellList.clear();
                doRequest();
                xrvSell.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                doRequest();
                xrvSell.loadMoreComplete();
            }
        });
        xrvBuy.setAdapter(buyAdapter);
        xrvSell.setAdapter(sellAdapter);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {//todo 是否刷新

        }
    }

    @OnClick({R.id.btn_buy, R.id.btn_sell, R.id.ll_warning})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_buy:
                type = "2";
                xrvBuy.setVisibility(View.VISIBLE);
                xrvSell.setVisibility(View.GONE);
                setBtnYellowBgShape(btnBuy);
                setBtnYellowBorderBgShape(btnSell);
                if (buyList.size() == 0) doRequest();
                break;

            case R.id.btn_sell:
                type = "1";
                xrvBuy.setVisibility(View.GONE);
                xrvSell.setVisibility(View.VISIBLE);
                setBtnYellowBgShape(btnSell);
                setBtnYellowBorderBgShape(btnBuy);
                if (sellList.size() == 0) doRequest();
                break;

            case R.id.ll_warning:
                if (is_login.equals("0")) {
                    Intent intent = new Intent();
                    intent.putExtra("target", "main_trade_index");
                    jump(LoginActivity.class, intent);
                    break;
                }
                if (is_auth == 0) {
                    jump(AuthActivity.class, null);
                }

                break;

            default:
                break;
        }
    }

    private void jump(Class<?> clz, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(getActivity(), clz);
        startActivity(intent);
    }


    private void setBtnYellowBgShape(Button v) {
        v.setBackground(getResources().getDrawable(R.drawable.blue_bg_shape));
        v.setTextColor(getResources().getColor(R.color.white));
    }

    private void setBtnYellowBorderBgShape(Button v) {
        v.setBackground(getResources().getDrawable(R.drawable.blue_border_bg_shape));
        v.setTextColor(getResources().getColor(R.color.primary_yellow));
    }

    private void doRequest() {
        if (tradeIndexPresenter == null) {
            tradeIndexPresenter = new TradeIndexPresenter(new TradeIndexModel());
        }
        showLoadingDialog();
        if (type.equals("2")) {
            tradeIndexPresenter.getTradeIndex(getBaseActivity(), type, String.valueOf(buyCounter), null);
        } else if (type.equals("1")) {
            tradeIndexPresenter.getTradeIndex(getBaseActivity(), type, String.valueOf(sellCounter), null);
        } else {
            tradeIndexPresenter.getTradeIndex(getBaseActivity(), type, null, null);
        }
    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        if (tradeIndexPresenter != null) tradeIndexPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onRequestSuccess(Object bean) {
        TradeIndexBase tradeIndexBase = (TradeIndexBase) bean;
        if (bean != null) {
            is_login = tradeIndexBase.getIs_login();
            is_auth = tradeIndexBase.getIs_auth();
            if (!TextUtils.isEmpty(tradeIndexBase.getTip())) {
                llWarning.setVisibility(View.VISIBLE);
                tvTip.setText(tradeIndexBase.getTip());
            }
            if (type.equals("2")) {
                buyCounter += 1;
                List<TradeIndex> list2 = tradeIndexBase.getList();
                buyList.addAll(list2);
                buyAdapter.notifyDataSetChanged();


            } else if (type.equals("1")) {
                sellCounter += 1;
                List<TradeIndex> list1 = tradeIndexBase.getList();
                sellList.addAll(list1);
                sellAdapter.notifyDataSetChanged();
            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void onRequestFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);
    }

    public void onEventMainThread(LoginStatusChangedEvent event) {
        if (event != null && event.getMsg().equals("login_status_changed_main_trade_index_reconnect")) {
            buyCounter = 0;
            buyList.clear();
            sellCounter = 0;
            sellList.clear();
            doRequest();
        }
    }
}
