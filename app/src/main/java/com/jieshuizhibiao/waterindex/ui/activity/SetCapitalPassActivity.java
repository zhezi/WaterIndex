package com.jieshuizhibiao.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.request.ChangeCapitalPassReqParams;
import com.jieshuizhibiao.waterindex.beans.request.PersonalAuthReqParams;
import com.jieshuizhibiao.waterindex.beans.request.SetCaptialPassReqParams;
import com.jieshuizhibiao.waterindex.contract.presenter.ChangeCapitalPassPresenter;
import com.jieshuizhibiao.waterindex.contract.presenter.SetCapitalPassPresenter;
import com.jieshuizhibiao.waterindex.contract.view.SetCapitalPassViewImpl;
import com.jieshuizhibiao.waterindex.utils.SPUtil;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置资金密码(修改/设置)
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
    private SetCaptialPassReqParams setCaptialPassReqParams;
    private ChangeCapitalPassReqParams changeCapitalPassReqParams;
    private SetCapitalPassPresenter setCapitalPassPresenter;
    private ChangeCapitalPassPresenter changeCapitalPassPresenter;
    @OnClick({R.id.left_ll,R.id.btn_find})
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.left_ll:
                goBack(v);
                finish();
                break;

            case R.id.btn_find:
                if(getIntent().getStringExtra("action").equals("SafeCenterActivity")){
                    String oldCapitalPass = edtOldPass.getText().toString();
                    String oldCapitalNewPass = edtNewPass.getText().toString();
                    String oldCapitalConfirmPass = edtConfirmPass.getText().toString();
                    changeCapitalPassReqParams.setSafe_pw(oldCapitalNewPass);
                    changeCapitalPassReqParams.setSafe_pw_re(oldCapitalConfirmPass);

                    changeCapitalPassPresenter.changeCapitalPass(this,changeCapitalPassReqParams);
                }else if(getIntent().getStringExtra("action").equals("AuthActivity")){


                    setCapitalPassPresenter.SetCapitalPass(this,setCaptialPassReqParams);
                }


                break;
            default:break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCapitalPassPresenter=new SetCapitalPassPresenter();
        setCapitalPassPresenter.attachView(this);
        changeCapitalPassPresenter = new ChangeCapitalPassPresenter();

        StatusBarUtil.setImmersionStatus(this, titleBar);
        initData();
        initView();
    }

    private void initData() {
        setCaptialPassReqParams = new SetCaptialPassReqParams();
        if(getIntent()!=null){
            PersonalAuthReqParams params = new PersonalAuthReqParams();
            setCaptialPassReqParams.setCity(params.getCity());
            setCaptialPassReqParams.setCity(params.getProvince());
            setCaptialPassReqParams.setCity(params.getUser_name());
            setCaptialPassReqParams.setCity(params.getId_no());
            setCaptialPassReqParams.setCity(params.getId_img_a());
            setCaptialPassReqParams.setCity(params.getId_img_b());
        }
    }

    /**
     * 修改密码-设置资金密码-修改资金密码 使用的同一xml
     * <string name="hint_input_capital_old_pwd">原资金密码</string>
     <string name="hint_input_capital_pwd">设置资金密码</string>
     <string name="hint_input_capital_new_pwd">设置新资金密码</string>
     <string name="hint_input_capital_confirm_pwd">确认资金密码</string>
     */
    private void initView() {
        if (getIntent()!=null){
            if(getIntent().getStringExtra("action").equals("SafeCenterActivity")){
                tvTitleCenter.setText("修改资金密码");
                edtOldPass.setHint(R.string.hint_input_capital_old_pwd);
                edtNewPass.setHint(R.string.hint_input_capital_new_pwd);
                edtConfirmPass.setHint(R.string.hint_input_capital_confirm_pwd);
                btnAuth.setText("完成");
            }else if(getIntent().getStringExtra("action").equals("AuthActivity")){
                tvTitleCenter.setText("设置资金密码");
                edtNewPass.setHint(R.string.hint_input_capital_pwd);
                edtConfirmPass.setHint(R.string.hint_input_capital_confirm_pwd);

                edtOldPass.setVisibility(View.GONE);
                edtPayNike.setVisibility(View.VISIBLE);
                btnAuth.setText("提交认证");
            }
        }


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
