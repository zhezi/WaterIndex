package com.jieshuizhibiao.waterindex.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.jieshuizhibiao.waterindex.R;
import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.http.config.HttpConfig;
import com.jieshuizhibiao.waterindex.utils.LogUtils;
import com.jieshuizhibiao.waterindex.utils.PictureFileUtil;
import com.jieshuizhibiao.waterindex.utils.RxHelper;
import com.jieshuizhibiao.waterindex.utils.StatusBarUtil;
import com.jieshuizhibiao.waterindex.utils.image.GlidImageManager;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class ImageBrowseActivity extends BaseActivity {
    @BindView(R.id.rl_pic_browse)
    RelativeLayout rlPicBrowse;
    @BindView(R.id.pv_pic)
    PhotoView pvPic;
    @BindView(R.id.imgbtn_save_pic)
    ImageButton imgbtnSavePic;

    private String mImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, rlPicBrowse);
        getIntentExtra();
        initView();
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        mImageUrl = intent.getStringExtra(PayActivity.QRCODE_URL);
        mImageUrl = HttpConfig.BASE_URL + "/" + mImageUrl;
//        mImageUrl="https://www.jieshuizhibiao.com/upload/admin/20190109/277a2b3341ebbf2a70bf755c0774361e.jpg";
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_image_browse);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }


    private void initView() {
        pvPic.enable();
        if (mImageUrl.contains("gif")) {
            loadGif();
        } else {
            loadImage();
        }
    }


    @OnClick({R.id.rl_pic_browse, R.id.imgbtn_save_pic})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_pic_browse:
                goBack(view);
                break;
            case R.id.imgbtn_save_pic:
//                saveImageToLocal(mImageUrl);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * 保存图片到本地
     *
     * @param fileName 文件名
     */
    private void saveImageToLocal(final String fileName) {
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> e) throws Exception {
                e.onNext(Glide.with(ImageBrowseActivity.this)
                        .load(mImageUrl)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get());
                e.onComplete();
            }
        }).compose(RxHelper.<File>rxSchedulerHelper()).subscribe(new Consumer<File>() {
            @Override
            public void accept(File file) throws Exception {
                saveImage(fileName, file);
            }
        });
    }

    /**
     * 加载gif
     */
    private void loadGif() {
        Glide.with(ImageBrowseActivity.this)
                .load(mImageUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new GlideDrawableImageViewTarget(pvPic) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<?
                            super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        //在这里添加一些图片加载完成的操作
                        imgbtnSavePic.setVisibility(View.VISIBLE);
                    }
                });
    }

    /**
     * 加载静态图片
     */
    private void loadImage() {
        Log.e("tag", "*****-----" + mImageUrl);
//        Glide.with(ImageBrowseActivity.this)
//                .load(mImageUrl)
//                .fitCenter()
//                .crossFade()
//                .into(new GlideDrawableImageViewTarget(pvPic) {
//                    @Override
//                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                        super.onResourceReady(drawable, anim);
//                        //在这里添加一些图片加载完成的操作
//                        imgbtnSavePic.setVisibility(View.VISIBLE);
//                    }
//                });
//        Glide.with(ImageBrowseActivity.this)
//                .load(mImageUrl)
//                .fitCenter()
//                .crossFade()
//                .into(new GlideDrawableImageViewTarget(imgPic) {
//                    @Override
//                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                        super.onResourceReady(drawable, anim);
//                        //在这里添加一些图片加载完成的操作
//                        imgbtnSavePic.setVisibility(View.VISIBLE);
//                    }
//                });
        GlidImageManager.getInstance().loadImageView(this, mImageUrl, pvPic, R.mipmap.default_img);
    }

    /**
     * 保存图片，并且回调提示
     *
     * @param fileName 文件名
     * @param file     文件file
     */
    private void saveImage(String fileName, File file) {
        PictureFileUtil.saveImage(ImageBrowseActivity.this, fileName, file, new PictureFileUtil
                .SaveResultCallback() {
            @Override
            public void onSavedSuccess() {
//                ToastUtils.showCustomToast("保存成功！");
                LogUtils.e("TAG","保存成功！");
            }

            @Override
            public void onSavedFailed() {
//                ToastUtils.showCustomToast("保存失败！");
                LogUtils.e("TAG","保存失败！");

            }
        });
    }
}

