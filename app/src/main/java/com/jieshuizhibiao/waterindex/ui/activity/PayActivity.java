package com.jieshuizhibiao.waterindex.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.unpay.BaseUnpayOrderInfo;
import com.jieshuizhibiao.waterindex.beans.appeal.PayInfo;
import com.jieshuizhibiao.waterindex.contract.model.BuyerDoPayModel;
import com.jieshuizhibiao.waterindex.contract.model.CancelOrderModel;
import com.jieshuizhibiao.waterindex.contract.model.UploadFileModel;
import com.jieshuizhibiao.waterindex.contract.model.callback.SecondRequestCallback;
import com.jieshuizhibiao.waterindex.contract.presenter.BuyerDoPayPresenter;
import com.jieshuizhibiao.waterindex.contract.presenter.CancelOrderPresenter;
import com.jieshuizhibiao.waterindex.contract.presenter.PicturePresenter;
import com.jieshuizhibiao.waterindex.contract.presenter.UploadFilePresenter;
import com.jieshuizhibiao.waterindex.contract.view.CancelOrderViewImpl;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;
import com.jieshuizhibiao.waterindex.contract.view.PictureViewImpl;
import com.jieshuizhibiao.waterindex.contract.view.SecondRequestViewImpl;
import com.jieshuizhibiao.waterindex.contract.view.ThirdRequestViewImpl;
import com.jieshuizhibiao.waterindex.event.ChangeOrderStatusEvent;
import com.jieshuizhibiao.waterindex.ui.fragment.OrderListsTabFragment;
import com.jieshuizhibiao.waterindex.ui.view.AlertChainDialog;
import com.jieshuizhibiao.waterindex.ui.widget.PicturePopupWindow;
import com.jieshuizhibiao.waterindex.utils.PictureFileUtil;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.TimeUtils;
import com.jieshuizhibiao.waterindex.utils.ToastUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 买家身份单独使用
 * 卖家身份无对应步骤
 */
public class PayActivity extends BaseActivity implements CancelOrderViewImpl, PictureViewImpl ,CommonViewImpl, SecondRequestViewImpl {

    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.tv_big_rmb)
    TextView tvBigRmb;
    @BindView(R.id.tv_buyer_paid)
    TextView tvBuyerPaid;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_time_clock)
    TextView tvTimeClock;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_time_clock)
    LinearLayout llTimeClock;
    @BindView(R.id.img_pay_type_bank_card)
    ImageView imgPayTypeBankCard;
    @BindView(R.id.img_pay_type_alipay)
    ImageView imgPayTypeAlipay;
    @BindView(R.id.img_pay_type_wechat)
    ImageView imgPayTypeWechat;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_user_name_txt)
    TextView tvUserNameTxt;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_account_name_txt)
    TextView tvAccountNameTxt;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.ll_qrcode)
    LinearLayout llQrcode;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_detail_name)
    TextView tvBankDetailName;
    @BindView(R.id.ll_bank_info)
    LinearLayout llBankInfo;
    @BindView(R.id.tv_pay_code)
    TextView tvPayCode;
    @BindView(R.id.tv_createtime)
    TextView tvCreatetime;
    @BindView(R.id.cb_agreement)
    CheckBox cbAgreement;

    @BindView(R.id.ll_float_snapshot)
    LinearLayout llFloatSnapshot;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_msg)
    TextView txtMsg;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.btn_img)
    Button btnImg;
    @BindView(R.id.btn_neg)
    Button btnNeg;
    @BindView(R.id.btn_pos)
    Button btnPos;


    private BaseUnpayOrderInfo baseUnpayOrderInfo;
    private PayInfo payInfo;
    private String lastStep;
    private int pay_type;
    private String expire_time;
    private CountDownTimer TimeCount;
    private CancelOrderPresenter cancelOrderPresenter;
    private boolean isChecked = false;
    private long order_id;
    private String pay_code;
    private String createtime;
    private String qrcodeUrl;
    private String rmb;
    private String account_name;
    private PicturePopupWindow popupWindow;
    private PicturePresenter picturePresenter;
    private BuyerDoPayPresenter buyerDoPayPresenter;
    private UploadFilePresenter uploadFilePresenter;

    public static final String QRCODE_URL = "qrcodeUrl";
    private final String CANCEL_SUCC = "取消成功！";
    private final String I_KNOW = "知道了";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        getIntntExtra();
        initView();
        initFloatView();

        cancelOrderPresenter = new CancelOrderPresenter(new CancelOrderModel());
        cancelOrderPresenter.attachView(this);
        picturePresenter = PicturePresenter.getInstance();
        picturePresenter.attachView(this);
        buyerDoPayPresenter=new BuyerDoPayPresenter(new BuyerDoPayModel());
        buyerDoPayPresenter.attachView(this);
        uploadFilePresenter=new UploadFilePresenter(new UploadFileModel());
        uploadFilePresenter.attachView(this);
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_pay);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onCancelSucc(Object bean) {

    }

    @Override
    public void onCancelFail(String msg) {

    }
    //买家身份-支付  成功
    @Override
    public void onRequestSuccess(Object bean) {

    }
    //买家身份-支付  失败
    @Override
    public void onRequestFailed(String msg) {

    }

    //买家身份-上传支付凭证 成功
    @Override
    public void onSecondRequstSuccess(Object bean) {

    }
    //买家身份-上传支付凭证失败
    @Override
    public void onSecondRequstFailed(String msg) {

    }

    private void getIntntExtra() {
        Intent intent = getIntent();
        if (intent != null) {
            baseUnpayOrderInfo = intent.getParcelableExtra(TraderUnpayActivity.ORDERINFO);
            payInfo = intent.getParcelableExtra(TraderUnpayActivity.PAYINFO);
            lastStep = intent.getStringExtra(TraderUnpayActivity.CURRENT_STEP);
            pay_type = intent.getIntExtra(TraderUnpayActivity.PAY_TYPE, 0);
            expire_time = intent.getStringExtra(TraderUnpayActivity.MILLIS);

            if (baseUnpayOrderInfo != null) {
                order_id = Long.valueOf(baseUnpayOrderInfo.getOrder_id());
                pay_code = baseUnpayOrderInfo.getPay_code();
                createtime = baseUnpayOrderInfo.getCreatetime();
                rmb = baseUnpayOrderInfo.getRmb();
            }
            if (payInfo != null) {
                qrcodeUrl = payInfo.getQrcode();
                account_name = payInfo.getAccount_name();
            }
        }
    }

    private void initView() {
        if (lastStep.equals(OrderListsTabFragment.BUYER_UNPAY)) {
            tvTitleCenter.setText("支付");
        }/*else if(lastStep.equals(TraderUnpayActivity.SELLER_UNPAY)){
            tvTitleCenter.setText("收款");
        }else {
            tvTitleCenter.setText("支付及收款情况");
        }*/

        tvBigRmb.setText(rmb);
        if (TraderUnpayActivity.isNumeric(expire_time)) {
            long ms = Long.valueOf(expire_time);
            if (ms > 1000) {
                countDownPayTime(ms);
            }
        } else if ("不限制，请尽快付款".contains(expire_time)) {
            tvLeft.setVisibility(View.VISIBLE);
            tvLeft.setText("付款时限：");
            tvRight.setVisibility(View.GONE);
            tvTimeClock.setText(expire_time);
            tvTimeClock.setTextColor(getResources().getColor(R.color.text_black));
        }

        if (lastStep.equals(OrderListsTabFragment.BUYER_UNPAY)) {
            tvBuyerPaid.setVisibility(View.GONE);
            llTimeClock.setVisibility(View.VISIBLE);
            tvUserNameTxt.setText("收款人");
        } /*else if (lastStep.equals(TraderUnpayActivity.SELLER_UNPAY)) {
            tvBuyerPaid.setVisibility(View.VISIBLE);
            llTimeClock.setVisibility(View.GONE);
            tvUserNameTxt.setText("收款人");//???
        }*/
        if (pay_type == 1) {
            imgPayTypeBankCard.setVisibility(View.VISIBLE);
            imgPayTypeAlipay.setVisibility(View.GONE);
            imgPayTypeWechat.setVisibility(View.GONE);
            tvPayType.setText("银行卡");
            tvAccountNameTxt.setText("银行卡号");
            tvAccountName.setText(account_name);
            llQrcode.setVisibility(View.GONE);
            llBankInfo.setVisibility(View.VISIBLE);
            if (payInfo != null) {
                tvBankDetailName.setText(new StringBuilder()
                        .append(payInfo.getBank_name())
                        .append("  ")
                        .append(payInfo.getBank_detail_name())
                        .toString());
            }
        } else if (pay_type == 2 || pay_type == 3) {
            if (pay_type == 2) {
                imgPayTypeBankCard.setVisibility(View.GONE);
                imgPayTypeAlipay.setVisibility(View.VISIBLE);
                imgPayTypeWechat.setVisibility(View.GONE);
                tvPayType.setText("支付宝");
            } else if (pay_type == 3) {
                imgPayTypeBankCard.setVisibility(View.GONE);
                imgPayTypeAlipay.setVisibility(View.GONE);
                imgPayTypeWechat.setVisibility(View.VISIBLE);
                tvPayType.setText("微信");
            }
            tvAccountNameTxt.setText("收款账号");
            tvAccountName.setText(account_name);
            llQrcode.setVisibility(View.VISIBLE);
            if (payInfo != null) qrcodeUrl = payInfo.getQrcode();
            llBankInfo.setVisibility(View.GONE);
        }
        if (payInfo != null) {
            tvUserName.setText(createtime);
            tvPayCode.setText(pay_code);
            tvCreatetime.setText(createtime);
        }
        cbAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PayActivity.this.isChecked = isChecked;
            }
        });
    }

    private void initFloatView() {
        llFloatSnapshot.setVisibility(View.GONE);
        flContainer.setVisibility(View.VISIBLE);
        btnImg.setVisibility(View.VISIBLE);
        txtTitle.setText("付款确认");
        txtMsg.setText("请确认你已向卖方付款。恶意点击将直接冻结账号");
    }

    @OnClick({R.id.left_ll, R.id.ll_qrcode, R.id.btn_finish_pay,
            R.id.btn_img, R.id.btn_pos, R.id.btn_neg})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(v);
                break;

            case R.id.ll_qrcode:
                Intent intent = new Intent(PayActivity.this, ImageBrowseActivity.class);
                intent.putExtra(QRCODE_URL, qrcodeUrl);
                startActivity(intent);
                break;
            case R.id.btn_finish_pay:
                if (isChecked) {
                    llFloatSnapshot.setVisibility(View.VISIBLE);
                } else {
                    ToastUtils.showCustomToast("请同意");
                }
                break;

            case R.id.btn_pos:
                finish();
                //todo pay & send event
                uploadFile();
                break;

            case R.id.btn_neg:
                llFloatSnapshot.setVisibility(View.GONE);
                break;

            case R.id.btn_img:
                showPopupView();
                break;
        }
    }

    private void countDownPayTime(long expire_time) {
        TimeCount = new CountDownTimer(expire_time * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (!PayActivity.this.isFinishing()) {
                    String countDown = TimeUtils.getToMSTime((int) (millisUntilFinished / 1000));
                    tvTimeClock.setText(countDown);
                }
            }

            @Override
            public void onFinish() {
                if (!PayActivity.this.isFinishing()) {
                    TimeCount.onFinish();
                    cancelOrder(lastStep, order_id);
                }
            }

        };
        TimeCount.start();
    }

    private void cancelOrder(String lastStep, long order_id) {
        showLoadingDialog();
        if (cancelOrderPresenter == null) {
            cancelOrderPresenter = new CancelOrderPresenter(new CancelOrderModel());
        }
        if (OrderListsTabFragment.BUYER_UNPAY.equals(lastStep)) {
            cancelOrderPresenter.buyerCancel(this, order_id);
        } /*else if (TraderUnpayActivity.SELLER_UNPAY.equals(lastStep)) {
//            traderUnpayPresenter.sellerUnpay(this, orderId);
        }*/
    }

    private void buyerDoPay(){
        if(buyerDoPayPresenter==null){
            buyerDoPayPresenter=new BuyerDoPayPresenter(new BuyerDoPayModel());
            buyerDoPayPresenter.attachView(this);
        }
        //todo 校验图片上传是否成功
        buyerDoPayPresenter.buyerDoPay(this,order_id,pay_type,"");//todo 填写图片上传成功后后台返回url
    }
    //todo 参数待定
    private void uploadFile(){
        if(uploadFilePresenter==null){
            uploadFilePresenter=new UploadFilePresenter(new UploadFileModel());
            uploadFilePresenter.attachView(this);
        }
        //todo 参数待定
        uploadFilePresenter.uploadFile(this);
    }

    @Override
    public void onViewClicked() {

    }

    @Override
    public void initPopupView() {

    }

    @Override
    public void showView(Bitmap bitmap) {
        final BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        String imgStr = PictureFileUtil.bitmap2String(bitmap);
        btnImg.setBackground(bitmapDrawable);
    }

    @Override
    public void showPopupView() {
        View parent = LayoutInflater.from(PayActivity.this).inflate(R.layout.activity_auth, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
    }

    @Override
    public void dismissPopupView() {
        popupWindow.dismiss();
    }

    @Override
    public boolean popupIsShowing() {
        return popupWindow.isShowing();
    }

    @Override
    public void go2PicSettingActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(PayActivity.this, PictureSettingActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void go2SystemPhoto(int requestCode) {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), requestCode);
    }

    @Override
    public void go2SystemCamera(File tempFile, int requestCode) {
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml，下面2种方式都可以
            //            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            //            Uri contentUri = FileProvider.getUriForFile(mActivity, BuildConfig
            // .APPLICATION_ID + "" +
            //                    ".fileProvider", tempFile);
            //            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);

            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile
                    .getAbsolutePath());
            Uri uri = PayActivity.this.getContentResolver().insert(MediaStore.Images
                    .Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public BaseActivity getActivity() {
        return PayActivity.this;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        picturePresenter.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        picturePresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        if (cancelOrderPresenter != null) {
            cancelOrderPresenter.detachView();
        }
        if(picturePresenter!=null){
            picturePresenter.detachView();
        }
        if(buyerDoPayPresenter!=null){
            buyerDoPayPresenter.detachView();
        }
        if(uploadFilePresenter!=null){
            uploadFilePresenter.detachView();
        }
        if (TimeCount != null) TimeCount.cancel();
        super.onDestroy();
    }

}
