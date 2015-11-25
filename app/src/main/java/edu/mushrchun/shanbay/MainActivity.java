package edu.mushrchun.shanbay;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import edu.mushrchun.shanbay.dao.ArticleReader;
import edu.mushrchun.shanbay.entity.Article;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private LinearLayout ll_tintcircle;

    private Context context = this;

    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listView);
        ll_tintcircle = (LinearLayout) findViewById(R.id.tint_circle);

        new Thread(new AnalysisRunnable()).start();



    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case SUCCESS:
                    List<String> lessonList = new ArrayList<String>();
                    for(int i=1;i<=ArticleReader.getInstance().articleList.size();i++){
                        lessonList.add("Lesson "+i);
                    }
                    ArticleAdapter aa = new ArticleAdapter(lessonList);
                    listView.setAdapter(aa);
                    ll_tintcircle.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    break;
                case FAILURE:break;
            }
        }
    };

    class ArticleAdapter extends BaseAdapter{


        private List<String> articleList;

        public ArticleAdapter(List<String> list){
            articleList = list;
        }


        @Override
        public int getCount() {
            return articleList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if(convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_articlelist,null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textview);

                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText("Lesson"+(position+1));
            viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this,ArticleActivity.class);
                    i.putExtra("article-num", position);
                    startActivity(i);
                }
            });
            return convertView;
        }
    }

    static class ViewHolder{
        public TextView textView;
    }

    class AnalysisRunnable implements Runnable{

        @Override
        public void run() {

        ArticleReader ar = ArticleReader.getInstance();

        AssetManager am = getAssets();
        try {
            ar.Load(am.open("nce4.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(ar.Analyse()){
            handler.sendEmptyMessage(SUCCESS);
        }
        else{
            handler.sendEmptyMessage(FAILURE);
        }



        }
    }
}
