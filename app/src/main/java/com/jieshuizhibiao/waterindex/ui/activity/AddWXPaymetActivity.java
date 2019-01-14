package com.jieshuizhibiao.waterindex.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.base.permission.config.PermissionConfig;
import com.jieshuizhibiao.waterindex.ui.widget.PicturePopupWindow;
import com.jieshuizhibiao.waterindex.utils.BitmapUtils;
import com.jieshuizhibiao.waterindex.utils.ImageFactory;
import com.jieshuizhibiao.waterindex.utils.LogUtils;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class AddWXPaymetActivity extends BaseActivity {

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

    private PicturePopupWindow popupWindow;
    private static final int REQUEST_TAKE_PHOTO = 0x110;//拍照结果回调
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);

        initView();
    }

    private void initView() {
        tv_title_center.setText("微信");
        btnQrcode.setText(R.string.hint_upload_payment_wx_img);
        edtAccount.setHint(R.string.hint_input_payment_wx_account);
        initPopupView();
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_add_payment_zfb_wx);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll,R.id.btn_payment_qrcode_img,R.id.btn_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_ll:
                goBack(v);
                finish();
                break;
            case R.id.btn_payment_qrcode_img:
                showPopupView();
                break;
            case R.id.btn_confirm:
                break;

            default:break;
        }
    }

    public void initPopupView() {
        if (popupWindow == null) {
            popupWindow = new PicturePopupWindow(AddWXPaymetActivity.this);
            popupWindow.setOnItemClickListener(new PicturePopupWindow.OnItemClickListener() {

                @Override
                public void onCaremaClicked() {
                    cameraTask();
                }

                @Override
                public void onPhotoClicked() {
                    selectImgTask();
                }

                @Override
                public void onCancelClicked() {
                    popupWindow.dismiss();
                }
            });
        }
    }

    public void showPopupView() {
        View parent = LayoutInflater.from(AddWXPaymetActivity.this).inflate(R.layout.activity_auth, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
    }

    /**
     * 判断SDCard是否可用
     */
    public static boolean existSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private void takePhoto() {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        File file = null;
        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
            String fileName = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA).format(new Date()) + ".png";
            if (existSDCard()) {
                file = new File(Environment.getExternalStorageDirectory(), fileName);
            } else {
                file = new File(Environment.getDataDirectory(), fileName);
            }
            Uri fileUrl;
            if (file != null) {
                mCurrentPhotoPath = file.getAbsolutePath();
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                    //不要完全使用Uri 避免一些不必要的麻烦
                    fileUrl = Uri.fromFile(file);

                } else {//包装7.0 Uri--清单文件中智能有一个FileProvider的声明
                    fileUrl = FileProvider.getUriForFile(AddWXPaymetActivity.this, "com.quanminjieshui.waterindex.fileProvider", file);

                    //授权4.4FileProvider content的grantUriPermission 方法
                    List<ResolveInfo> mResolveList = getPackageManager().
                            queryIntentActivities(takePhotoIntent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : mResolveList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        grantUriPermission(packageName, fileUrl, Intent.FLAG_GRANT_READ_URI_PERMISSION |
                                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    }
                }
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUrl);
            }
            startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
        }
    }

    public void dealTakePhoto() {
        if (!TextUtils.isEmpty(BitmapUtils.getBrand()) && BitmapUtils.getBrand().equals("samsung")) {
            //获取照片拍照角度
            int degree = BitmapUtils.readPictureDegree(mCurrentPhotoPath);
            LogUtils.i("拍照角度：" + degree);//正常角度应该返回0 三星的是90度
            //将原图转为Bitmap
            Bitmap mOriginalBitmap = ImageFactory.convertStremToBitmap(mCurrentPhotoPath);
            //旋转图片  负值为顺时针旋转 正值为逆时针旋转
            Bitmap mRotateBitmap = BitmapUtils.rotateBitmap(mOriginalBitmap, +(float) degree);
            //旋转图片保存路径
            String fileName = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA).format(new Date()) + ".png";
            //转为File
            File mRotateFile = ImageFactory.convertBitmapToFile(mRotateBitmap, fileName);
            ZipImg(AddWXPaymetActivity.this,mRotateFile);

            if (mOriginalBitmap != null) {
                mOriginalBitmap.recycle();
            }
            if (mRotateBitmap != null) {
                mRotateBitmap.recycle();
            }
        } else {//其他机型手机
            File mFile = new File(mCurrentPhotoPath);
            ZipImg(AddWXPaymetActivity.this,mFile);
        }
    }

    private void ZipImg(final Activity activity, File mFile) {
        Observable.just(mFile)
                .subscribeOn(Schedulers.io())
                .map(new Function<File, File>() {
                    @Override
                    public File apply(File file) throws Exception {
                        return Luban.with(AddWXPaymetActivity.this).load(file).get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) throws Exception {
                        //转换字节流 需要上传的字节
                        String stringStream = ImageFactory.file2String(file);
                        LogUtils.d("转换字节流"+stringStream);
                    }
                });
    }

    /**
     * 显示压缩后的图片 todo
     * @param file
     */
    public void showImgData(File file){
        try {
            Bitmap bitmap = BitmapUtils.getBitmap(file.getAbsolutePath(),file.getName());
            final BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
            btnQrcode.setBackground(bitmapDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cameraTask(){
        popupWindow.dismiss();
        String[] perms = {Manifest.permission.CAMERA};
        performCodeWithPermission(getString(R.string.rationale_camera), PermissionConfig.REQUEST_CAMERA_PERM, perms, new PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                takePhoto();
                LogUtils.i("已获取camera权限");
            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {
                if (hasPermanentlyDenied) {
                    alertAppSetPermission(getString(R.string.rationale_ask_again), PermissionConfig.REQUEST_CAMERA_PERM);
                    LogUtils.i("获取camera权限被拒");
                }
            }
        });
    }

    public void selectImgTask(){
        popupWindow.dismiss();
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        performCodeWithPermission(getString(R.string.rationale_storage), PermissionConfig.REQUEST_STORAGE_PERM, perms, new PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                Intent intent = new Intent(AddWXPaymetActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, ImagePicker.RESULT_CODE_ITEMS);
                LogUtils.i("已获取storage权限");
            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {
                if (hasPermanentlyDenied) {
                    alertAppSetPermission(getString(R.string.rationale_ask_again), PermissionConfig.REQUEST_STORAGE_PERM);
                    LogUtils.i("获取storage权限被拒");
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    dealTakePhoto();
                }
                break;
            case ImagePicker.RESULT_CODE_ITEMS:
                if(data!=null){
                    //打开相册没有选择图片返回
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    for (ImageItem imageItem : images) {
                        try {
                            mCurrentPhotoPath = imageItem.path;
                            //拿到用户选择的头像以后 进行字节流的转换 并上传服务器
                            dealTakePhoto();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }
}
