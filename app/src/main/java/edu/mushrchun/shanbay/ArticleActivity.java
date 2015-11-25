package edu.mushrchun.shanbay;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import edu.mushrchun.shanbay.dao.ArticleReader;
import edu.mushrchun.shanbay.dao.WordListReader;
import edu.mushrchun.shanbay.entity.WordList;

public class ArticleActivity extends AppCompatActivity {

    private TextView tv_article;
    private RatingBar rb_level;
    private Switch s_highlight;

    private int articleNum;
    private Context context = this;
    private ArticleReader ar = ArticleReader.getInstance();
    private WordListReader wl = WordListReader.getInstance();
    SpannableStringBuilder styled;

    private static final int FINISH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);


        articleNum = getIntent().getIntExtra("article-num", -1);
        tv_article = (TextView) findViewById(R.id.articleContent);
        tv_article.setText(ar.articleList.get(articleNum).getContent());

        rb_level = (RatingBar) findViewById(R.id.ratingBar);
        rb_level.setStepSize(1.0f);
        rb_level.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                Toast.makeText(context, "You choose word level:" + rating, Toast.LENGTH_SHORT).show();
                if(rating>0){
                    new Thread(new HighlightRunnable((int) rating)).start();
                }


            }
        });
        s_highlight = (Switch) findViewById(R.id.switcher);
        s_highlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    tv_article.setText(ar.articleList.get(articleNum).getContent());

                    rb_level.setRating(0);
                }
            }
        });



    }

    final Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==FINISH) {
                tv_article.setText(styled);
                s_highlight.setChecked(true);
            }
        }
    };

    class HighlightRunnable implements Runnable{

        int rating;

        public HighlightRunnable(int rating){
            this.rating = rating;
        }
        @Override
        public void run() {
            styled = new SpannableStringBuilder(ar.articleList.get(articleNum).getContent());

            switch ((int)rating){
                case 5:
                    for (String word:wl.l_5List) {
                        int start = ar.articleList.get(articleNum).getContent().indexOf(word);
                        int end = start+ word.length();
                        if (start<0){
                            continue;
                        }
                        styled.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    };
                case 4:
                    for (String word:wl.l_4List) {
                        int start = ar.articleList.get(articleNum).getContent().indexOf(word);
                        int end = start+ word.length();
                        if (start<0){
                            continue;
                        }
                        styled.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    };
                case 3:
                    for (String word:wl.l_3List) {
                        int start = ar.articleList.get(articleNum).getContent().indexOf(word);
                        int end = start+ word.length();
                        if (start<0){
                            continue;
                        }
                        styled.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    };
                case 2:
                    for (String word:wl.l_2List) {
                        int start = ar.articleList.get(articleNum).getContent().indexOf(word);
                        int end = start+ word.length();
                        if (start<0){
                            continue;
                        }
                        styled.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    };
                case 1:
                    for (String word:wl.l_1List) {
                        int start = ar.articleList.get(articleNum).getContent().indexOf(word);
                        int end = start+ word.length();
                        if (start<0){
                            continue;
                        }
                        styled.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    };
            }
            handler.sendEmptyMessage(FINISH);
        }
    }
}
