package com.qianyi.shine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.fragment.CareerFragment;
import com.qianyi.shine.fragment.HomeFragment;
import com.qianyi.shine.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private CareerFragment careerFragment;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;
    private Fragment currentFra=new Fragment();
    @BindView(R.id.bar)
    public BottomNavigationBar bar;
    @Override
    protected void initViews() {
        fragmentManager=getSupportFragmentManager();
        homeFragment=new HomeFragment();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        AddOrShowFra(ft,homeFragment);
        ButterKnife.bind(this);


        bar.setMode(BottomNavigationBar.MODE_FIXED);
        bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bar.addItem(new BottomNavigationItem(getResources().getDrawable(R.mipmap.main_home),"首页").setActiveColorResource(R.color.main_blue))
                .addItem(new BottomNavigationItem(getResources().getDrawable(R.mipmap.main_careet),"职业规划").setActiveColorResource(R.color.main_blue))
                .addItem(new BottomNavigationItem(getResources().getDrawable(R.mipmap.main_mine),"我的").setActiveColorResource(R.color.main_blue))

                .setFirstSelectedPosition(0).initialise();
        bar.setTabSelectedListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.lay_main);
    }

    @Override
    protected void initListener() {

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
            ft.hide(currentFra).add(R.id.main_switch, Fra).commit();

        } else {
            ft.hide(currentFra).show(Fra).commit();

        }
        currentFra = Fra;


    }

    @Override
    public void onTabSelected(int position) {
        switch(position ){
            case 0:
                if(homeFragment==null){
                    homeFragment=new HomeFragment();
                }
                FragmentTransaction ft_home=fragmentManager.beginTransaction();
                AddOrShowFra(ft_home,homeFragment);
            break;
            case 1:
                if(careerFragment==null){
                    careerFragment=new CareerFragment();
                }
                FragmentTransaction ft_careet=fragmentManager.beginTransaction();
                AddOrShowFra(ft_careet,careerFragment);
                break;
            case 2:
                if(mineFragment==null){
                    mineFragment=new MineFragment();
                }
                FragmentTransaction ft_mine=fragmentManager.beginTransaction();
                AddOrShowFra(ft_mine,mineFragment);
                break;

            default:
            break;


        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
