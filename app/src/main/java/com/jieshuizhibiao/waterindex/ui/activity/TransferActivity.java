package com.jieshuizhibiao.waterindex.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.ui.view.AlertChainDialog;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/9.
 * Class Note:
 */

public class TransferActivity extends BaseActivity {

    @BindView(R.id.btn_jsl_transfer)
    Button transferBtn;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;

    private AlertChainDialog alertChainDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
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

    @OnClick({R.id.left_ll,R.id.btn_jsl_transfer})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.left_ll:
                goBack(view);
                break;
            case R.id.btn_jsl_transfer:
                String transferAmount = "10.00000T";
                String actualAmount = "7.00000T";
                String balanceAmount = "3.00000T";
                String persent = "30%";
                String msgContent2 = "划转总金额:"+transferAmount+"\n实际到账:"+actualAmount+"\n结转JSL:"+balanceAmount;
                String msgTitle2 = "系统将您划转的节水指标的"+persent+"结转到您的平台账号，并兑换为积分作为工艺用途，您可以在兑换商城进行消费";
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

                        }
                    }).show();
                }
                break;
        }
    }
}
