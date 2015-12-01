package edu.mushrchun.shanbay;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContentActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ContentAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("#1"));
        tabLayout.addTab(tabLayout.newTab().setText("#2"));
        tabLayout.addTab(tabLayout.newTab().setText("#3"));
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);


    }


    class ContentAdapter extends FragmentStatePagerAdapter {

        public ContentAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            Fragment f = ContentFragment.newInstance(position);

            return f ;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "#"+position+"#";
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
