package com.qianyi.shine.ui.mine.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qianyi.shine.BuildConfig;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.dialog.PhotoChioceDialog;
import com.qianyi.shine.dialog.SelfDialog;
import com.qianyi.shine.ui.account.activity.LoginActivity;
import com.qianyi.shine.ui.mine.addressUtils.LoginDialogFragment;
import com.qianyi.shine.ui.mine.view.CircleImageView;
import com.qianyi.shine.utils.BitmapToBase64;
import com.qianyi.shine.utils.ListActivity;
import com.qianyi.shine.utils.SDPathUtils;
import com.qianyi.shine.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by NEUNB on 2018/3/31.
 */

public class PersonalInfoActivity extends BaseActivity implements LoginDialogFragment.LoginInputListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_mine)
    CircleImageView iv_mine;
    private PhotoChioceDialog photoChioceDialog;
    private String localImg,strBase64;
    private DisplayImageOptions options;
    @BindView(R.id.tv_address)
    public TextView tv_address;
    @Override
    protected void initViews() {
        tv_title.setText("我的资料");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_personal_info);
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.tv_quit,R.id.rl_photoChoice,R.id.rl_address})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_quit:
                quitAccount();
                break;
            case R.id.rl_photoChoice:
                photoChioceDialog = new PhotoChioceDialog(this);
                photoChioceDialog.show();
                photoChioceDialog.setClickCallback(new PhotoChioceDialog.ClickCallback() {
                    @Override
                    public void doAlbum() {
                        readPhotoAlbum();
                    }
                    @Override
                    public void doCancel() {
                    }
                    @Override
                    public void doCamera() {
                        takePhoto();
                    }
                });
                break;
            case R.id.rl_address:
                //地区
                showLoginDialog();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showLoginDialog() {
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getFragmentManager(), "loginDialog");
    }

    @Override
    public void onLoginInputComplete(String username) {
      tv_address.setText(username);
    }
    //跳转到拍照
    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(PersonalInfoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PersonalInfoActivity.this, new String[]{Manifest.permission.CAMERA}, 222);
            return;
        } else if (ContextCompat.checkSelfPermission(PersonalInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PersonalInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 222);
            return;
        } else if (ContextCompat.checkSelfPermission(PersonalInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PersonalInfoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 222);
            return;
        } else {
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(SDPathUtils.getCachePath(), "temp.jpg")));
                startActivityForResult(openCameraIntent, 2);
            } else {
                Uri imageUri = FileProvider.getUriForFile(PersonalInfoActivity.this, "com.camera_photos.fileprovider", new File(SDPathUtils.getCachePath(), "temp.jpg"));
                openCameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(openCameraIntent, 2);
            }
        }

    }
    //读取相册
    private void readPhotoAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            startPhotoZoom(data.getData());
        } else if (requestCode == 2) {
            File temp = new File(SDPathUtils.getCachePath(), "temp.jpg");
            startPhotoZoom(Uri.fromFile(temp));
        } else if (requestCode == 3) {
            if (data != null) {
                setPicToView(data);
            }
        }
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent(PersonalInfoActivity.this, PreviewActivity.class);
        intent.setDataAndType(uri, "image/*");
        startActivityForResult(intent, 3);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bitmap bitmap = null;
        byte[] bis = picdata.getByteArrayExtra("bitmap");
        bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
        localImg = System.currentTimeMillis() + ".JPEG";
        if (bitmap != null) {
            strBase64= BitmapToBase64.Bitmap2StrByBase64(bitmap);
            SDPathUtils.saveBitmap(bitmap, localImg);
            Log.e("本地图片绑定", SDPathUtils.getCachePath() + localImg);
            setImageUrl(iv_mine, "file:/" + SDPathUtils.getCachePath() + localImg, R.mipmap.head);
        }
    }
    public void setImageUrl(ImageView ivId, String imageUrl, int emptyImgId) {
        if (options == null) {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(emptyImgId)
                    .showImageForEmptyUri(emptyImgId)
                    .showImageOnFail(emptyImgId).cacheInMemory(true)
                    .cacheOnDisk(true).considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
        }
        ImageLoader.getInstance().displayImage(imageUrl, ivId, options);
    }
    /***
     * 退出当前账号
     */
    private void quitAccount() {
        final SelfDialog quitDialog = new SelfDialog(this);
        quitDialog.setTitle("提示");
        quitDialog.setMessage("是否退出登录");
        quitDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
               Utils.clearSharedUser(PersonalInfoActivity.this);
               quitDialog.dismiss();
                ListActivity.close();
               jumpActivity(PersonalInfoActivity.this,LoginActivity.class);
            }
        });
        quitDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                quitDialog.dismiss();
            }
        });
        quitDialog.show();
    }


}
