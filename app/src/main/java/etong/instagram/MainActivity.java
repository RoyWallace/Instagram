package etong.instagram;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ImageView;


import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements MomentListFragment.OnFragmentInteractionListener {



    private FragmentManager fm;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllView();
//        doAnim();
    }

    public void findAllView() {
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.content,new MomentListFragment()).commit();
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setAllListener() {

    }

    public void addFragment(Fragment fragment){
        if(fragment instanceof CommentListFragment){
            imageView.setVisibility(View.GONE);
        }
        fm.beginTransaction().add(R.id.content,fragment).addToBackStack("").commit();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
