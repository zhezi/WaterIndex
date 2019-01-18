package com.jieshuizhibiao.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/18.
 * Class Note:发布购买需求
 */

public class TranscationReleaseBuyOrSellActivity extends BaseActivity {

    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.time_limit_fl)
    FrameLayout timeLimitFl;
    @BindView(R.id.iv_bank_state)
    ImageView bankSelect;
    @BindView(R.id.iv_alipay_state)
    ImageView alipaySelect;
    @BindView(R.id.iv_wxpay_state)
    ImageView wxpaySelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);

        initView();
    }

    private void initView() {
        if (getIntent().getStringExtra("action").equals("Buy")){
            tvTitleCenter.setText("发布购买需求");
            timeLimitFl.setVisibility(View.GONE);
        }else {
            tvTitleCenter.setText("发布出售需求");
            timeLimitFl.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.left_ll,R.id.bank_ll,R.id.wx_ll,R.id.zfb_ll})
    public void OnClick(View view){
        switch (view.getId()) {
            case R.id.left_ll:
                goBack(view);
                break;
            case R.id.bank_ll:
                bankSelect.setVisibility(View.VISIBLE);
                alipaySelect.setVisibility(View.GONE);
                wxpaySelect.setVisibility(View.GONE);
                break;
            case R.id.wx_ll:
                bankSelect.setVisibility(View.GONE);
                alipaySelect.setVisibility(View.GONE);
                wxpaySelect.setVisibility(View.VISIBLE);
                break;
            case R.id.zfb_ll:
                bankSelect.setVisibility(View.GONE);
                alipaySelect.setVisibility(View.VISIBLE);
                wxpaySelect.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_transaction_release_buy);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
