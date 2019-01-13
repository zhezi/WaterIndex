package com.quanminjieshui.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AddZFBPaymetActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.btn_payment_qrcode_img)
    Button btnQrcode;
    @BindView(R.id.edt_payment_comment_account)
    EditText edtAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);

        initView();
    }

    private void initView() {
        tv_title_center.setText("支付宝");
        btnQrcode.setText(R.string.hint_upload_payment_zfb_img);
        edtAccount.setHint(R.string.hint_input_payment_zfb_account);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_add_payment_zfb_wx);

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
