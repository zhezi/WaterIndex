package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.UploadFileModel;
import com.jieshuizhibiao.waterindex.contract.model.callback.SecondRequestCallback;
import com.jieshuizhibiao.waterindex.contract.view.SecondRequestViewImpl;

public class UploadFilePresenter extends BasePresenter<SecondRequestViewImpl> {
    private UploadFileModel uploadFileModel;

    public UploadFilePresenter(UploadFileModel uploadFileModel) {
        this.uploadFileModel = uploadFileModel;
    }
    //todo 参数待定
    public void uploadFile(BaseActivity activity){
        if(uploadFileModel==null){
            uploadFileModel=new UploadFileModel();
        }
        uploadFileModel.uploadFile(activity, new SecondRequestCallback() {

            @Override
            public void onSecondRequstSuccess(Object o) {
                if(mView!=null){
                    mView.onSecondRequstSuccess(o);
                }
            }

            @Override
            public void onSecondRequstFailed(String msg) {
                if(mView!=null){
                    mView.onSecondRequstFailed(msg);
                }
            }
        });
    }

}
