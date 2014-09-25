package etong.instagram;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;


import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements MomentListFragment.OnFragmentInteractionListener {

    private FragmentManager fm;

    private LinearLayout leftMenu;

    private DrawerLayout drawerLayout;

    private ListView menuListView;

    private ImageView addImageView;

    private ImageView leftImageView;
    private ImageView rightImageView;
    private TextView centerText;

    private ImageView menuAvatar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllView();
        setupData();
        doAnim();
        setAllListener();
    }

    private void doAnim() {
        Animation animation = new TranslateAnimation(0,0,-100,0);
        animation.setDuration(500);
        leftImageView.startAnimation(animation);
        rightImageView.startAnimation(animation);
        centerText.startAnimation(animation);
    }

    public void findAllView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftMenu = (LinearLayout) findViewById(R.id.left_drawer);
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content,new MomentListFragment()).commit();
        addImageView = (ImageView) findViewById(R.id.add_imageView);
        leftImageView = (ImageView) findViewById(R.id.left_imageView);
        rightImageView = (ImageView) findViewById(R.id.right_imageView);
        centerText = (TextView) findViewById(R.id.center_textView);
        centerText.setSelected(true);
        leftImageView = (ImageView) findViewById(R.id.left_imageView);
        menuListView = (ListView) findViewById(R.id.menuListView);
        menuAvatar = (ImageView) findViewById(R.id.menu_avatar_imageView);

    }

    public void setAllListener() {

        leftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(leftMenu);
            }
        });

        menuAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(leftMenu);
                addFragment(new ProfileFragment(0,0));
            }
        });
    }

    public void setupData(){
        menuListView.setAdapter(new MenuAdapter(this));
    }

    public void addFragment(Fragment fragment){
        if(fragment instanceof CommentListFragment){
            addImageView.setVisibility(View.GONE);
        }
        fm.beginTransaction().setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_slide_out_bottom,R.anim.abc_fade_in,R.anim.fragment_out).add(R.id.content,fragment).addToBackStack("").commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(leftMenu)){
            drawerLayout.closeDrawers();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
