package com.jieshuizhibiao.waterindex.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.request.AddPayMentTypeReqParams;
import com.jieshuizhibiao.waterindex.contract.presenter.AddPayMentTypePresenter;
import com.jieshuizhibiao.waterindex.contract.view.AddPayMentTypeViewImpl;
import com.jieshuizhibiao.waterindex.http.config.HttpConfig;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;
import com.jieshuizhibiao.waterindex.utils.Util;

import java.util.Map;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class AddBankPaymetActivity extends BaseActivity implements AddPayMentTypeViewImpl{

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.edt_name)
    EditText etName;
    @BindView(R.id.edt_bank_name)
    EditText etBankName;
    @BindView(R.id.edt_bank_branch_name)
    EditText etBankBranchName;
    @BindView(R.id.edt_bank_number)
    EditText etBankNumber;
    @BindView(R.id.edt_capital_pass)
    EditText etCapitalPass;
    @BindDrawable(R.drawable.gray_border_bg_shape)
    Drawable edt_border;
    @BindDrawable(R.drawable.red_border_illegal_bg_shape)
    Drawable edt_border_illegal;
    @BindString(R.string.key_edt_pay_type_bank_deposit)
    String keyDeposit;
    @BindString(R.string.key_edt_pay_type_bank_deposit_branch)
    String keyDepositBranch;
    @BindString(R.string.key_edt_pay_type_bank_number)
    String keyPayNumber;
    @BindString(R.string.key_edt_pay_type_name)
    String keyPayName;
    @BindString(R.string.key_edt_pay_type_capital_pwd)
    String keyPayPass;

    private AddPayMentTypePresenter addPayMentTypePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);
        addPayMentTypePresenter = new AddPayMentTypePresenter();
        addPayMentTypePresenter.attachView(this);

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

    @OnClick({R.id.left_ll,R.id.btn_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_ll:
                goBack(v);
                finish();
                break;
            case R.id.btn_confirm:
                AddPayMentTypeReqParams params = new AddPayMentTypeReqParams();
                params.setUser_name(etName.getText().toString());
                params.setAccount_name(etBankNumber.getText().toString());
                params.setBank_name(etBankName.getText().toString());
                params.setBank_detail_name(etBankBranchName.getText().toString());
                params.setSafe_pw(etCapitalPass.getText().toString());
                params.setType(HttpConfig.BANK_TYPE);
                addPayMentTypePresenter.vertifyBank(params);
                addPayMentTypePresenter.addPayMentType(AddBankPaymetActivity.this,params);
                break;
            default:break;
        }
    }

    @Override
    public void onAddEdtContentsLegal() {
        etName.setBackground(edt_border);
        etBankName.setBackground(edt_border);
        etBankBranchName.setBackground(edt_border);
        etBankNumber.setBackground(edt_border);
        etCapitalPass.setBackground(edt_border);
        showLoadingDialog();
    }

    @Override
    public void onAddEdtContentsIllegal(Map<String, Boolean> verify) {

    }

    /**
     * 开户行 选填
     * @param verify
     */
    @Override
    public void onAddEdtContentsIllegalBank(Map<String, Boolean> verify) {
        if (!Util.isEmpty(verify.get(keyPayName)) && !verify.get(keyPayName)){
            etName.setBackground(edt_border_illegal);
        } else {
            etName.setBackground(edt_border);
        }
        if (!Util.isEmpty(verify.get(keyDeposit)) && !verify.get(keyDeposit)) {
            etBankName.setBackground(edt_border_illegal);
            etBankName.setText("");
        } else {
            etBankName.setBackground(edt_border);
        }
        if (!Util.isEmpty(verify.get(keyDepositBranch)) && !verify.get(keyDepositBranch)) {
            etBankBranchName.setBackground(edt_border_illegal);
            etBankBranchName.setText("");
        } else {
            etBankBranchName.setBackground(edt_border);
        }
        if (!Util.isEmpty(verify.get(keyPayNumber)) && !verify.get(keyPayNumber)){
            etBankNumber.setBackground(edt_border_illegal);
            etBankNumber.setText("");
        } else {
            etBankNumber.setBackground(edt_border);
        }
        if (!Util.isEmpty(verify.get(keyPayPass)) && !verify.get(keyPayPass)){
            etCapitalPass.setBackground(edt_border_illegal);
            etCapitalPass.setText("");
        } else {
            etCapitalPass.setBackground(edt_border);
        }
    }

    @Override
    public void onAddPaymentTypeSuccess() {
        dismissLoadingDialog();
        ToastUtils.showCustomToast("添加成功！");
        finish();
    }

    @Override
    public void onAddPaymentTypeFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (addPayMentTypePresenter!=null){
            addPayMentTypePresenter.detachView();
        }
    }
}
