/**
 * @version: 1.0
 * @author: yefufeng@foxmail.com
 */
package com.leaf.black.reader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.leaf.black.reader.R;
import com.leaf.black.reader.fragment.MenuFragment;
import com.leaf.black.reader.fragment.NotesFragment;
import com.leaf.black.reader.fragment.PlanFragment;
import com.leaf.black.reader.fragment.SettingsFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private MaterialMenuIconToolbar materialMenuIconToolbar;
    private boolean direction = false;
    private boolean navigationItemSelected = false;
    private int mFragmentIndex = 0;

    /*将对象与控件进行绑定*/
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        /*定义导航栏图标*/
        setNavigationIcon();
        /*设置导航栏的文字、动画及响应事件*/
        setToolBar();
        /*设置导航栏子项点击响应事件*/
        setNavigationItem();
    }

    /**
     * 定义导航栏图标
     */
    private void setNavigationIcon() {
        materialMenuIconToolbar = new MaterialMenuIconToolbar(this,
                getResources().getColor(R.color.color_white, null),
                MaterialMenuDrawable.Stroke.REGULAR) {
            @Override
            public int getToolbarViewId() {
                return R.id.toolbar;
            }
        };
    }

    /**
     * 设置导航栏的文字、动画及响应事件
     */
    private void setToolBar() {
        materialMenuIconToolbar.setNeverDrawTouch(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white,null));
        toolbar.setTitle(R.string.text_main);
        toolbar.setBackgroundColor(getResources().getColor(R.color.background_daytime_material_blue, null));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
                if (fragmentCount <= 1) {
                    if (!direction) {
                        drawerLayout.openDrawer(Gravity.LEFT);
                    } else {
                        drawerLayout.closeDrawer(Gravity.LEFT);
                    }
                } else {
                    mFragmentIndex = 0;
                    if (mFragmentIndex == MenuFragment.SETTINGS_FRAGMENT) {
                        navigationItemSelected = false;
                    }
                    toolbar.setTitleTextColor(getResources()
                            .getColor(R.color.color_white, null));
                    toolbar.setTitle(R.string.text_main);
                    toolbar.setBackgroundColor(getResources()
                            .getColor(R.color.background_daytime_material_blue, null));
                    materialMenuIconToolbar.setColor(getResources().getColor(R.color.color_white, null));
                    materialMenuIconToolbar.animateState(MaterialMenuDrawable.IconState.BURGER);
                }
            }
        });
        drawerLayout.setScrimColor(getResources().getColor(R.color.drawer_color, null));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (!navigationItemSelected && getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                    materialMenuIconToolbar.setTransformationOffset(
                            MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                            direction ? 2 - slideOffset : slideOffset);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                direction = true;
                if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                    toolbar.setTitleTextColor(getResources().getColor(R.color.color_white, null));
                } else {
                    toolbar.setTitleTextColor(getResources()
                            .getColor(R.color.background_daytime_material_blue, null));
                }
                toolbar.setTitle(getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                direction = false;
                navigationItemSelected = false;
                if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                    toolbar.setTitleTextColor(getResources().getColor(R.color.color_white, null));
                    toolbar.setTitle(R.string.text_main);
                } else {
                    toolbar.setTitleTextColor(getResources().getColor(R.color.background_daytime_material_blue, null));
                    toolbar.setTitle(R.string.text_null);
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    /**
     * 设置导航栏子项点击响应事件
     */
    private void setNavigationItem(){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(false);
                        int menuItemId = item.getItemId();
                        switch (menuItemId) {
                            /*点击首页选项*/
                            case R.id.drawer_home:
                                drawerLayout.closeDrawers();
                                if (mFragmentIndex > 0) {
                                    mFragmentIndex = 0;
                                    materialMenuIconToolbar.setColor(getResources()
                                            .getColor(R.color.color_white, null));
                                    toolbar.setTitleTextColor(getResources()
                                            .getColor(R.color.color_white, null));
                                    toolbar.setTitle(R.string.text_main);
                                    toolbar.setBackgroundColor(getResources()
                                            .getColor(R.color.background_daytime_material_blue, null));
                                }
                                break;
                            /*点击读书选项*/
                            case R.id.drawer_Read:
                                Intent intent = new Intent(MainActivity.this,ReadingActivity.class);
                                startActivity(intent);
                                break;
                            /*点击笔记选项*/
                            case R.id.drawer_Note:
                                navigationItemSelected = true;
                                if (mFragmentIndex != MenuFragment.NOTES_FRAGMENT) {
                                    mFragmentIndex = MenuFragment.NOTES_FRAGMENT;
                                    materialMenuIconToolbar.setColor(getResources()
                                            .getColor(R.color.background_daytime_material_blue, null));
                                    materialMenuIconToolbar.setState(MaterialMenuDrawable.IconState.ARROW);
                                    toolbar.setTitle(R.string.text_null);
                                    toolbar.setBackgroundColor(getResources()
                                            .getColor(R.color.color_white, null));
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    NotesFragment notesFragment = new NotesFragment();
                                    fragmentTransaction.add(R.id.container,notesFragment);
                                    fragmentTransaction.commit();
                                }
                                drawerLayout.closeDrawers();
                                break;
                            /*点击计划选项*/
                            case R.id.drawer_Plan:
                                navigationItemSelected = true;
                                if (mFragmentIndex != MenuFragment.PLANS_FRAGMENT) {
                                    mFragmentIndex = MenuFragment.PLANS_FRAGMENT;
                                    materialMenuIconToolbar.setColor(getResources()
                                            .getColor(R.color.background_daytime_material_blue, null));
                                    materialMenuIconToolbar.setState(MaterialMenuDrawable.IconState.ARROW);
                                    toolbar.setTitle(R.string.text_null);
                                    toolbar.setBackgroundColor(getResources()
                                            .getColor(R.color.color_white, null));
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    PlanFragment planFragment = new PlanFragment();
                                    fragmentTransaction.add(R.id.container,planFragment);
                                    fragmentTransaction.commit();
                                }
                                drawerLayout.closeDrawers();
                                break;
                            /*点击设置选项*/
                            case R.id.drawer_settings:
                                navigationItemSelected = true;
                                if (mFragmentIndex != MenuFragment.SETTINGS_FRAGMENT) {
                                    mFragmentIndex = MenuFragment.SETTINGS_FRAGMENT;
                                    materialMenuIconToolbar.setColor(getResources()
                                            .getColor(R.color.background_daytime_material_blue, null));
                                    materialMenuIconToolbar.setState(MaterialMenuDrawable.IconState.ARROW);
                                    toolbar.setTitle(R.string.text_null);
                                    toolbar.setBackgroundColor(getResources()
                                            .getColor(R.color.color_white, null));
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    SettingsFragment settingsFragment = new SettingsFragment();
                                    fragmentTransaction.add(R.id.container,settingsFragment);
                                    fragmentTransaction.commit();
                                }
                                drawerLayout.closeDrawers();
                                break;
                            /*点击退出选项*/
                            case R.id.drawer_logout:
                                finish();
                                break;
                            default:
                                Log.i(TAG, "item = " + item.getTitle());
                                break;
                        }
                        return false;
                    }
                });
    }
}
