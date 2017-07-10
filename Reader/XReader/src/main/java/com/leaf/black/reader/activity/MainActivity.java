/**
 * @version: 1.0
 * @author: yefufeng@foxmail.com
 */
package com.leaf.black.reader.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.leaf.black.reader.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private MaterialMenuIconToolbar materialMenuIconToolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
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
        /*设置导航栏的文字及响应事件*/
        setToolBar();
        drawerLayout.setScrimColor(getResources().getColor(R.color.drawer_color));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    /**
     * 定义导航栏图标
     */
    private void setNavigationIcon() {
        materialMenuIconToolbar = new MaterialMenuIconToolbar(this,
                getResources().getColor(R.color.color_white),
                /*getResources().getColor(R.color.color_white,null),*/
                MaterialMenuDrawable.Stroke.REGULAR) {
            @Override
            public int getToolbarViewId() {
                return R.id.toolbar;
            }
        };
    }

    private void setToolBar() {
        materialMenuIconToolbar.setNeverDrawTouch(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        toolbar.setTitle(R.string.text_main);
        toolbar.setBackgroundColor(getResources().getColor(R.color.background_daytime_material_blue));
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
                    toolbar.setTitleTextColor(getResources()
                            .getColor(R.color.color_white));
                    toolbar.setTitle(R.string.text_main);
                    toolbar.setBackgroundColor(getResources()
                            .getColor(R.color.background_daytime_material_blue));
                    materialMenuIconToolbar.setColor(getResources().getColor(R.color.color_white));
                    materialMenuIconToolbar.animateState(MaterialMenuDrawable.IconState.BURGER);
                }
            }
        });
    }
}
