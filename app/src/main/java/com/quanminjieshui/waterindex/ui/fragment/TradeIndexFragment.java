package com.quanminjieshui.waterindex.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterindex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TradeIndexFragment extends BaseFragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trade_index, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.btn_buy,R.id.btn_sell})
    public void onClick(View view){
        int id=view.getId();
        switch (id){
            case R.id.btn_buy:
                xrvBuy.setVisibility(View.VISIBLE);
                xrvSell.setVisibility(View.GONE);
                break;

            case R.id.btn_sell:
                xrvBuy.setVisibility(View.GONE);
                xrvSell.setVisibility(View.VISIBLE);
                break;

                default:
                    break;
        }
    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
