package com.jieshuizhibiao.waterindex.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.MoveMoneryBean;
import com.jieshuizhibiao.waterindex.beans.request.MoveMoneryReqParams;
import com.jieshuizhibiao.waterindex.contract.presenter.BeforeMvMoneryPresenter;
import com.jieshuizhibiao.waterindex.contract.presenter.MoveMoneryPresenter;
import com.jieshuizhibiao.waterindex.contract.view.BeforeMvMoneryViewImpl;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;
import com.jieshuizhibiao.waterindex.ui.view.AlertChainDialog;
import com.jieshuizhibiao.waterindex.utils.AccountValidatorUtil;
import com.jieshuizhibiao.waterindex.utils.LogUtils;
import com.jieshuizhibiao.waterindex.utils.SPUtil;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;

import java.text.DecimalFormat;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/9.
 * Class Note:
 */

public class TransferActivity extends BaseActivity implements BeforeMvMoneryViewImpl,CommonViewImpl {

    @BindView(R.id.btn_jsl_transfer)
    Button transferBtn;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.tv_ds1)
    TextView tvDs1;
    @BindView(R.id.tv_ds2)
    TextView tvDs2;
    @BindView(R.id.tv_able_ds)
    TextView tvAbleDs;
    @BindView(R.id.edt_transfer_amonut)
    EditText edTransferDs;
    @BindView(R.id.edt_transfer_amonut_pwd)
    EditText edTransferPass;
    @BindDrawable(R.drawable.gray_border_bg_shape)
    Drawable edt_border;
    @BindDrawable(R.drawable.red_border_illegal_bg_shape)
    Drawable edt_border_illegal;

    private String ableTransferAmount;
    private AlertChainDialog alertChainDialog;
    private BeforeMvMoneryPresenter beforeMvMoneryPresenter;
    private MoveMoneryPresenter moveMoneryPresenter;
    private MoveMoneryReqParams params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);
        beforeMvMoneryPresenter = new BeforeMvMoneryPresenter();
        beforeMvMoneryPresenter.attachView(this);
        moveMoneryPresenter = new MoveMoneryPresenter();
        moveMoneryPresenter.attachView(this);
        initData();
        initView();
    }

    private void initData() {
        params = new MoveMoneryReqParams();
        if (getIntent()!=null){
            DecimalFormat df = new DecimalFormat("###.00");
            String ds = getIntent().getStringExtra("action");
            String ds_c2c = getIntent().getStringExtra("ds_c2c");
            Double dsc2c = Double.valueOf(ds_c2c.substring(0,ds_c2c.length()-1));
            String ds_Bb = getIntent().getStringExtra("ds_Bb");
            Double dsBb = Double.valueOf(ds_Bb.substring(0,ds_Bb.length()-1));
            if (ds.equals("c2c")){
                tvTitle1.setText("从C2C账号");
                tvDs1.setText("余额："+(dsc2c <= 0.00 ? "0.00" : df.format(dsc2c)) +" T");
                tvTitle2.setText("到主站账号");
                tvDs2.setText("余额："+(dsBb <= 0.00 ? "0.00" : df.format(dsBb)) + " T");
                tvAbleDs.setText("可划转数量 "+(dsc2c <= 0.00 ? "0.00" : df.format(dsc2c))+" T");
                if (dsc2c <= 0.00){
                    ableTransferAmount = "0.00";//可划转总额
                }else {
                    ableTransferAmount = df.format(dsc2c);//可划转总额
                }
                params.setType("2");//划入
            }else{
                tvTitle1.setText("从主站账号");
                tvDs1.setText("余额："+(dsBb <= 0.00 ? "0.00" : df.format(dsBb)) + " T");
                tvTitle2.setText("到C2C账号");
                tvDs2.setText("余额："+(dsc2c <= 0.00 ? "0.00" :df.format(dsc2c)) +" T");
                tvAbleDs.setText("可划转数量 "+(dsBb <= 0.00 ? "0.00" :df.format(dsBb))+" T");
                if (dsBb <= 0.00){
                    ableTransferAmount = "0.00";//可划转总额
                }else {
                    ableTransferAmount = df.format(dsBb);//可划转总额
                }
                params.setType("1");//划出
            }
        }
    }

    private void initView() {
        tv_title_center.setText("资金划转");
        alertChainDialog = new AlertChainDialog(this);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_transfer);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll,R.id.btn_jsl_transfer,R.id.tv_transfer_all_ds})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.left_ll:
                goBack(view);
                break;
            case R.id.tv_transfer_all_ds:
                edTransferDs.setText(ableTransferAmount);
                edTransferDs.setSelection(ableTransferAmount.length());
                break;
            case R.id.btn_jsl_transfer:

                DecimalFormat df = new DecimalFormat("###.00");
                String msgContent2 = "",msgTitle2 = "";
                if (!TextUtils.isEmpty(edTransferDs.getText().toString()) &&
                        Double.valueOf(edTransferDs.getText().toString()) > 0.00 &&
                        Double.valueOf(edTransferDs.getText().toString()) <= Double.valueOf(ableTransferAmount)){
                    edTransferDs.setBackground(edt_border);
                }else {
                    edTransferDs.setBackground(edt_border_illegal);
                    edTransferDs.setText("");
                    return;
                }
                if (!TextUtils.isEmpty(edTransferPass.getText().toString()) && AccountValidatorUtil.isPassword(edTransferPass.getText().toString())){
                    edTransferPass.setBackground(edt_border);
                }else{
                    edTransferPass.setBackground(edt_border_illegal);
                    edTransferPass.setText("");
                    return;
                }
                if(edTransferDs.getText().toString().equals(ableTransferAmount)){
                    //全额划转，则不显示扣减信息 和 结转 JSL 信息
                    String transferAmount = edTransferDs.getText()+" T";
                    String actualAmount = edTransferDs.getText()+" T";
                    msgContent2 = "划转总金额:"+transferAmount+"\n实际到账:"+actualAmount;
                    msgTitle2 = "确认划转";

                } else {
                    //部分划转
                    String transferAmount = edTransferDs.getText()+" T";
                    String actualAmount = df.format(Double.valueOf(edTransferDs.getText().toString())*0.7)+" T";
                    String balanceAmount = df.format((Double.valueOf(edTransferDs.getText().toString()))-(Double.valueOf(edTransferDs.getText().toString())*0.7))+" T";
                    String persent = "30%";
                    msgContent2 = "划转总金额:"+transferAmount+"\n实际到账:"+actualAmount+"\n结转JSL:"+balanceAmount;
                    msgTitle2 = "系统将您划转的节水指标的"+persent+"结转到您的平台账号，并兑换为积分作为公益用途，您可以在兑换商城进行消费";

                }

                if(alertChainDialog!=null){
                    alertChainDialog.builder().setCancelable(false);
                    alertChainDialog.setMsgType(getResources().getColor(R.color.text_red_dialog),getResources().getColor(R.color.text_gray_dialog));
                    alertChainDialog.setTitle(msgTitle2).setMsg(msgContent2)
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).setPositiveButton("确认划转", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            params.setTotal(edTransferDs.getText().toString());
                            params.setSafe_pw(edTransferPass.getText().toString());
                            moveMoneryPresenter.moveMonery(TransferActivity.this,params);
                            showLoadingDialog();
                        }
                    }).show();
                }
                break;
        }
    }

    @Override
    public void onRequestSuccess(Object bean) {
        dismissLoadingDialog();
        DecimalFormat df = new DecimalFormat("###.00");
        Double transferGyj = 0.00,transferArrive = 0.00,transferTotal = 0.00;
        if (bean instanceof MoveMoneryBean){
            transferGyj = Double.valueOf(((MoveMoneryBean) bean).getGyj());
            transferArrive = Double.valueOf(((MoveMoneryBean) bean).getArrive());
            transferTotal = Double.valueOf(((MoveMoneryBean) bean).getTotal());
        }
        if (params.getType().equals("1")){//c2c公益金
            SPUtil.insert(this,SPUtil.C2C_TRAMSFER,(transferGyj <= 0.00 ? "0.00":df.format(transferGyj)));
        }else {
            SPUtil.insert(this,SPUtil.BB_TRAMSFER,(transferGyj <= 0.00 ? "0.00":df.format(transferGyj)));
        }
        LogUtils.d("transferGyj:"+transferGyj+"\n"+"transferArrive:"+transferArrive+"\n"+"transferTotal:"+transferTotal);
        finish();
    }

    @Override
    public void onRequestFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg,0);

    }

    @Override
    public void onBeforeMvMonerySuccess(MoveMoneryBean moveMoneryBean) {
        dismissLoadingDialog();
    }

    @Override
    public void onBeforeMvMoneryFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg,0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (beforeMvMoneryPresenter!=null){
            beforeMvMoneryPresenter.detachView();
        }
        if (moveMoneryPresenter!=null){
            moveMoneryPresenter.detachView();
        }
    }
}
