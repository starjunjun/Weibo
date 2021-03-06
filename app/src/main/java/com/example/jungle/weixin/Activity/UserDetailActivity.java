package com.example.jungle.weixin.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jungle.weixin.Adapter.ViewPagerAdapter;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.Bean.XHRBase.XHRBaseBean;
import com.example.jungle.weixin.Bean.XHRBase.XHRUserDetail;
import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.Fragment.UserPhotosFragment;
import com.example.jungle.weixin.Fragment.UserWeiboFragement;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.H5Service;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.vov.vitamio.utils.Log;
import me.nereo.multi_image_selector.bean.Image;
import retrofit2.Response;

public class UserDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private User user;
    private String uid = "3117780635";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        user = (User) getIntent().getSerializableExtra("user");

        getData();

    }

    public void setUI() {

        // StatusBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        // ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Collapsing
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
//        collapsingToolbarLayout.setStatusBarScrimColor(Color.WHITE);
        collapsingToolbarLayout.setTitle(user.getScreen_name());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBlue));

        // TabBar
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Fragment> viewList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        titleList.add("微博");
        titleList.add("相册");
        viewList.add(new UserWeiboFragement());
        viewList.add(new UserPhotosFragment());

        // ViewPager
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), viewList, titleList);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(mViewPagerAdapter);

        // 个人信息
        CircleImageView avatarImgV = (CircleImageView) findViewById(R.id.avatar);
        ImageView identityImgV = (ImageView) findViewById(R.id.identity_icon);
        TextView followTv = (TextView) findViewById(R.id.follow_num);
        TextView followerTv = (TextView) findViewById(R.id.follower_num);
        TextView descTv = (TextView) findViewById(R.id.introduction);
        ImageView backgroundImgV = (ImageView) findViewById(R.id.background);
        Glide.with(UserDetailActivity.this).load(user.getAvatar_hd()).into(avatarImgV);
        if (user.isVerified()) {
            identityImgV.setImageResource(R.drawable.avatar_vip);
        } else {
            identityImgV.setVisibility(View.GONE);
        }
        followTv.setText(user.getFollow_count() + "");
        followerTv.setText(user.getFollowers_count() + "");
        descTv.setText(user.getDescription());
        Glide.with(UserDetailActivity.this).load(user.getCover_image_phone()).into(backgroundImgV);

    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();//左滑退出activity
    }


    public void getData() {
        NetRequestFactory.getInstance().createService(H5Service.class).getUserDetail(CodeUtils.getmToken(), 1, (user.getId() + ""))
                .compose(Transform.<Response<XHRBaseBean<XHRUserDetail>>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<XHRBaseBean<XHRUserDetail>>>() {
            @Override
            public void onSuccess(Response<XHRBaseBean<XHRUserDetail>> stringResponse) {
                if (stringResponse.body().getStatus() == 1) {
                    user = stringResponse.body().getData().getInfo().getUserInfo();
                    setUI();
                } else {

                }
            }

            @Override
            public void _onError(Response<XHRBaseBean<XHRUserDetail>> e) {
                super._onError(e);
            }
        });
    }

}
