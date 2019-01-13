package com.quanminjieshui.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class AddBankPaymetActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);

        initView();
    }

    private void initView() {
        tv_title_center.setText("添加银行卡");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_add_payment_bank);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_ll:
                goBack(v);
                finish();
                break;
            default:break;
        }
    }
}
