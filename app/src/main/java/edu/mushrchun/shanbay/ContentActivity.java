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
//        tabLayout.addTab(tabLayout.newTab().setText("原文"));
//        tabLayout.addTab(tabLayout.newTab().setText("新词"));
//        tabLayout.addTab(tabLayout.newTab().setText("翻译"));
        tabLayout.setupWithViewPager(viewPager);


    }


    class ContentAdapter extends FragmentStatePagerAdapter {

        public ContentAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            Fragment f = null;
            switch(position){
                case 0:f = ArticlePartFragment.newInstance(getIntent().getIntExtra("article-num", -1));
                    break;
                case 1:f = WordsPartFragment.newInstance(getIntent().getIntExtra("article-num", -1));
                    break;
                case 2:f = TranslationPartFragment.newInstance(getIntent().getIntExtra("article-num", -1));
                    break;
            }

            return f ;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:return "原文";
                case 1:return "新词";
                case 2:return "翻译";
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
