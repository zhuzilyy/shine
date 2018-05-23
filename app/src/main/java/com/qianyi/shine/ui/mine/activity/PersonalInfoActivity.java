package com.qianyi.shine.ui.mine.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qianyi.shine.BuildConfig;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiMine;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.dialog.PhotoChioceDialog;
import com.qianyi.shine.dialog.SelfDialog;
import com.qianyi.shine.ui.account.activity.GuessScoreActivity;
import com.qianyi.shine.ui.account.activity.LoginActivity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.mine.addressUtils.LoginDialogFragment;
import com.qianyi.shine.ui.mine.view.CircleImageView;
import com.qianyi.shine.utils.BitmapToBase64;
import com.qianyi.shine.utils.ListActivity;
import com.qianyi.shine.utils.SDPathUtils;
import com.qianyi.shine.utils.SPUtils;
import com.qianyi.shine.utils.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by NEUNB on 2018/3/31.
 */

public class PersonalInfoActivity extends BaseActivity implements LoginDialogFragment.LoginInputListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.iv_mine)
    CircleImageView iv_mine;
    @BindView(R.id.ll_personInfo)
    LinearLayout ll_personInfo;
    @BindView(R.id.et_school)
    EditText et_school;
    @BindView(R.id.et_nikeName)
    EditText et_nikeName;
    private PhotoChioceDialog photoChioceDialog;
    private String localImg,strBase64;
    private DisplayImageOptions options;
    @BindView(R.id.tv_address)
    public TextView tv_address;
    private PopupWindow pw_sex;
    private View view_sex;
    private RelativeLayout rl_male,rl_female;
    private ImageView iv_female,iv_male;
    private String province,selectCity,selectCounty,sexType="1";
    private CustomLoadingDialog dialog;
    @Override
    protected void initViews() {
        tv_title.setText("我的资料");
        tv_right.setVisibility(View.VISIBLE);
        view_sex= LayoutInflater.from(this).inflate(R.layout.pw_sex,null);
        rl_female=view_sex.findViewById(R.id.rl_female);
        rl_male=view_sex.findViewById(R.id.rl_male);
        iv_female=view_sex.findViewById(R.id.iv_female);
        iv_male=view_sex.findViewById(R.id.iv_male);
        dialog=new CustomLoadingDialog(this);
        setValues();
    }
    //设置个人信息
    private void setValues() {
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        String avatar = loginInfo.getAvatar();
        String nikeName=loginInfo.getNickname();
        String sex = loginInfo.getSex();
        String prov = loginInfo.getProv();
        String city = loginInfo.getCity();
        String contry = loginInfo.getContry();
        String school = loginInfo.getSchool();
        Glide.with(this).load(avatar).into(iv_mine);
        et_nikeName.setText(nikeName);
        tv_sex.setText(sex);
        tv_address.setText(prov+city+contry);
        province=prov;
        selectCity=city;
        selectCounty=contry;
        et_school.setText(school);
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
        rl_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_female.setImageResource(R.mipmap.pay_checked);
                iv_male.setImageResource(R.mipmap.pay_nochecked);
                tv_sex.setText("女");
                pw_sex.dismiss();
                sexType="0";
            }
        });
        rl_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_male.setImageResource(R.mipmap.pay_checked);
                iv_female.setImageResource(R.mipmap.pay_nochecked);
                tv_sex.setText("男");
                pw_sex.dismiss();
                sexType="1";
            }
        });
    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.tv_quit,R.id.rl_photoChoice,R.id.rl_address,R.id.rl_sex,R.id.tv_right})
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
            case R.id.rl_sex:
                selectSexPw();
                break;
            case R.id.tv_right:
                String school=et_school.getText().toString().trim();
                String nikeName=et_nikeName.getText().toString().trim();
                if (TextUtils.isEmpty(province)){
                    Toast.makeText(this, "请选择省市县", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(school)){
                    Toast.makeText(this, "请输入学校", Toast.LENGTH_SHORT).show();
                    return;
                }
                updateInfo(school,nikeName);
                break;
        }
    }
    //更改个人信息
    private void updateInfo(String school,String nikeName) {
        dialog.show();
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        String id=loginInfo.getId();
        apiMine.UpdateInfo(apiConstant.UPDATE_INFO, id, strBase64, sexType, nikeName, province, selectCity, selectCounty, school, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        Gson gson = new Gson();
                        LoginBean loginBean=gson.fromJson(s, LoginBean.class);
                        Log.i("tag",s);
                        if(loginBean != null){
                            String code = loginBean.getCode();
                            if("0" .equals(code)){
                                LoginBean.LoginData.LoginInfo user = loginBean.getData().getInfo();
                                try {
                                    //存储当前用户
                                    Utils.saveUser(user,PersonalInfoActivity.this);
                                    Intent intent=new Intent();
                                    intent.setAction("com.action.updateInfo");
                                    sendBroadcast(intent);
                                    finish();
                                }catch (Exception e){
                                    Log.i("excaption_shine",e.getMessage());
                                }
                            }else {
                                Toast.makeText(PersonalInfoActivity.this, ""+loginBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void selectSexPw() {
        pw_sex = new PopupWindow(this);
        pw_sex.setContentView(view_sex);
        pw_sex.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pw_sex.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pw_sex.setTouchable(true);
        pw_sex.setFocusable(true);
        pw_sex.setBackgroundDrawable(new BitmapDrawable());
       // pw_sex.setAnimationStyle(R.style.AnimBottom);
        pw_sex .showAtLocation(ll_personInfo, Gravity.CENTER, 0, 0);
        // 设置pw弹出时候的背景颜色的变化
        backgroundAlpha(0.5f);
        pw_sex.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showLoginDialog() {
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getFragmentManager(), "loginDialog");
    }
    @Override
    public void onLoginInputComplete(String username) {
      tv_address.setText(username);
      String[] address = username.split(",");
      province=address[0];
      selectCity=address[1];
      selectCounty=address[2];
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
                //获取系統版本
                int currentapiVersion = Build.VERSION.SDK_INT;
                // 激活相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 判断存储卡是否可以用，可用进行存储
                    SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                            "yyyy_MM_dd_HH_mm_ss");
                    String filename = timeStampFormat.format(new Date());
                    File outputImagepath = new File(Environment.getExternalStorageDirectory(),
                            filename + ".jpg");
                    if (currentapiVersion < 24) {
                        // 从文件中创建uri
                        Uri uri = Uri.fromFile(outputImagepath);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    } else {
                        //兼容android7.0 使用共享文件的形式
                        ContentValues contentValues = new ContentValues(1);
                        contentValues.put(MediaStore.Images.Media.DATA, outputImagepath.getAbsolutePath());
                        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    }
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                startActivityForResult(intent, 2);
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
                SPUtils.put(PersonalInfoActivity.this,"chongCi","");
                SPUtils.put(PersonalInfoActivity.this,"baoShou","");
                SPUtils.put(PersonalInfoActivity.this,"wenTuo","");
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
