package com.quanminjieshui.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterindex.R;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.request.SetCaptialPassReqParams;
import com.quanminjieshui.waterindex.contract.presenter.SetCapitalPassPresenter;
import com.quanminjieshui.waterindex.contract.view.SetCapitalPassViewImpl;
import com.quanminjieshui.waterindex.utils.SPUtil;
import com.quanminjieshui.waterindex.utils.StatusBarUtil;
import com.quanminjieshui.waterindex.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置资金密码(修改)
 */
public class SetCapitalPassActivity extends BaseActivity implements SetCapitalPassViewImpl {

    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;

    @BindView(R.id.edt_old_pass)
    EditText edtOldPass;
    @BindView(R.id.edt_new_pass)
    EditText edtNewPass;
    @BindView(R.id.edt_confirm_pass)
    EditText edtConfirmPass;
    @BindView(R.id.edt_pay_nike)
    EditText edtPayNike;
    @BindView(R.id.btn_find)
    Button btnAuth;

    private SetCapitalPassPresenter setCapitalPassPresenter;

    @OnClick({R.id.left_ll,R.id.btn_find})
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.left_ll:
                goBack(v);
                finish();
                break;

            case R.id.btn_find:
                SetCaptialPassReqParams params = new SetCaptialPassReqParams();
                params.setDevice_type("android");
                params.setSafe_pw(edtNewPass.getText().toString());
                params.setSafe_pw_re(edtConfirmPass.getText().toString());
                params.setToken((String)SPUtil.get(this,SPUtil.TOKEN,""));
                // TODO: 2019/1/10 添加请求参数
                setCapitalPassPresenter.SetCapitalPass(this,params);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCapitalPassPresenter=new SetCapitalPassPresenter();
        setCapitalPassPresenter.attachView(this);

        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("设置资金密码");
        edtOldPass.setHint(R.string.hint_input_capital_old_pwd);
        edtPayNike.setVisibility(View.VISIBLE);
        btnAuth.setText("提交认证");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_change_pass);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }



    @Override
    public void onSetCapitalPassSuccess() {
        showToast("修改成功！");
        finish();
    }

    @Override
    public void onSetCapitalPassFailed(String msg) {
        ToastUtils.showCustomToast(msg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setCapitalPassPresenter.detachView();
    }


}
