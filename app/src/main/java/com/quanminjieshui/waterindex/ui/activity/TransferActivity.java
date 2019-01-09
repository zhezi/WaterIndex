package com.quanminjieshui.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/9.
 * Class Note:
 */

public class TransferActivity extends BaseActivity {

    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("资金划转");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_transfer);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.left_ll:
                goBack(view);
                break;
        }
    }
}
