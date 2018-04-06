package com.qianyi.shine.ui.college.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.fragment.HomeFragment;
import com.qianyi.shine.ui.college.fragments.collegeEmploymentProspectsFragment;
import com.qianyi.shine.ui.college.fragments.collegeIntroductionFragment;
import com.qianyi.shine.ui.college.fragments.collegeProfessionalSettingsFragment;
import com.qianyi.shine.ui.college.fragments.collegeScoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/30.
 */

public class CollegeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.college_Introduction)
    public RelativeLayout college_Introduction;
    @BindView(R.id.college_ProfessionalSettings)
    public RelativeLayout college_ProfessionalSettings;
    @BindView(R.id.college_Score)
    public RelativeLayout college_Score;
    @BindView(R.id.college_EmploymentProspects)
    public RelativeLayout college_EmploymentProspects;
    @BindView(R.id.indicator01)
    public View Indicator01;
    @BindView(R.id.indicator02)
    public View Indicator02;
    @BindView(R.id.indicator03)
    public View Indicator03;
    @BindView(R.id.indicator04)
    public View Indicator04;
    private Fragment currentFra=new Fragment();
    private collegeIntroductionFragment introductionFragment;
    private collegeProfessionalSettingsFragment professionalSettingsFragment;
    private collegeScoreFragment scoreFragment;
    private collegeEmploymentProspectsFragment employmentProspectsFragment;
    private FragmentManager fragmentManager;
    @BindView(R.id.collete_tv01)
    public TextView collete_tv01;
    @BindView(R.id.collete_tv02)
    public TextView collete_tv02;
    @BindView(R.id.collete_tv03)
    public TextView collete_tv03;
    @BindView(R.id.collete_tv04)
    public TextView collete_tv04;
    @BindView(R.id.back)
    public ImageView back;



    @Override
    protected void initViews() {
        setStatusColor();
        ButterKnife.bind(this);
        fragmentManager=getSupportFragmentManager();
        introductionFragment=new collegeIntroductionFragment();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        AddOrShowFra(ft,introductionFragment);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_college);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }

    @OnClick({R.id.college_Introduction, R.id.college_ProfessionalSettings, R.id.college_Score,
            R.id.college_EmploymentProspects,R.id.back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();

                break;
            case R.id.college_Introduction:
                showIndicator(0);
                if(introductionFragment==null){
                    introductionFragment=new collegeIntroductionFragment();
                }
                FragmentTransaction ft_introduce=fragmentManager.beginTransaction();
                AddOrShowFra(ft_introduce,introductionFragment);
                break;
            case R.id.college_ProfessionalSettings:
                showIndicator(1);
                if(professionalSettingsFragment==null){
                    professionalSettingsFragment=new collegeProfessionalSettingsFragment();
                }
                FragmentTransaction ft_professionalSettings=fragmentManager.beginTransaction();
                AddOrShowFra(ft_professionalSettings,professionalSettingsFragment);
                break;
            case R.id.college_Score:
                showIndicator(2);
                if(scoreFragment==null){
                    scoreFragment=new collegeScoreFragment();
                }
                FragmentTransaction ft_score=fragmentManager.beginTransaction();
                AddOrShowFra(ft_score,scoreFragment);
                break;
            case R.id.college_EmploymentProspects:
                showIndicator(3);
                if(employmentProspectsFragment==null){
                    employmentProspectsFragment=new collegeEmploymentProspectsFragment();
                }
                FragmentTransaction ft_employmentProspects=fragmentManager.beginTransaction();
                AddOrShowFra(ft_employmentProspects,employmentProspectsFragment);
                break;

            default:
                break;


        }
    }

    /***
     * 显示隐藏Fragment
     *
     * @param ft
     * @param Fra
     */
    private void AddOrShowFra(FragmentTransaction ft, Fragment Fra) {
        if (currentFra == Fra) {
            return;
        }
        if (!Fra.isAdded()) {
            ft.hide(currentFra).add(R.id.college_switLayout, Fra).commit();

        } else {
            ft.hide(currentFra).show(Fra).commit();

        }
        currentFra = Fra;


    }


    /***
     * 显示指示器
     */
    private void showIndicator(int index) {
        switch (index) {
            case 0:
                Indicator01.setVisibility(View.VISIBLE);
                Indicator02.setVisibility(View.INVISIBLE);
                Indicator03.setVisibility(View.INVISIBLE);
                Indicator04.setVisibility(View.INVISIBLE);
                collete_tv01.setTextColor(getResources().getColor(R.color.main_blue));
                collete_tv02.setTextColor(getResources().getColor(R.color.black));
                collete_tv03.setTextColor(getResources().getColor(R.color.black));
                collete_tv04.setTextColor(getResources().getColor(R.color.black));

                break;
            case 1:
                Indicator01.setVisibility(View.INVISIBLE);
                Indicator02.setVisibility(View.VISIBLE);
                Indicator03.setVisibility(View.INVISIBLE);
                Indicator04.setVisibility(View.INVISIBLE);

                collete_tv01.setTextColor(getResources().getColor(R.color.black));
                collete_tv02.setTextColor(getResources().getColor(R.color.main_blue));
                collete_tv03.setTextColor(getResources().getColor(R.color.black));
                collete_tv04.setTextColor(getResources().getColor(R.color.black));

                break;
            case 2:
                Indicator01.setVisibility(View.INVISIBLE);
                Indicator02.setVisibility(View.INVISIBLE);
                Indicator03.setVisibility(View.VISIBLE);
                Indicator04.setVisibility(View.INVISIBLE);

                collete_tv01.setTextColor(getResources().getColor(R.color.black));
                collete_tv02.setTextColor(getResources().getColor(R.color.black));
                collete_tv03.setTextColor(getResources().getColor(R.color.main_blue));
                collete_tv04.setTextColor(getResources().getColor(R.color.black));

                break;
            case 3:
                Indicator01.setVisibility(View.INVISIBLE);
                Indicator02.setVisibility(View.INVISIBLE);
                Indicator03.setVisibility(View.INVISIBLE);
                Indicator04.setVisibility(View.VISIBLE);

                collete_tv01.setTextColor(getResources().getColor(R.color.black));
                collete_tv02.setTextColor(getResources().getColor(R.color.black));
                collete_tv03.setTextColor(getResources().getColor(R.color.black));
                collete_tv04.setTextColor(getResources().getColor(R.color.main_blue));

                break;

            default:
                break;


        }

    }
}
