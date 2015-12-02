package edu.mushrchun.shanbay;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import edu.mushrchun.shanbay.dao.ArticleReader;
import edu.mushrchun.shanbay.dao.WordListReader;
import edu.mushrchun.shanbay.entity.Article;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlePartFragment extends Fragment {

    public static final String NUM = "NUM";

    private TextView tv_article;
    private RatingBar rb_level;
    private Switch s_highlight;
    private static final int FINISH = 1;

    private int articleNum;
    private Context context = getActivity();
    private ArticleReader ar = ArticleReader.getInstance();
    private WordListReader wl = WordListReader.getInstance();
    SpannableStringBuilder styled;

    public static ArticlePartFragment newInstance(int num){
        Bundle b = new Bundle();
        b.putInt(NUM, num);
        ArticlePartFragment apf= new ArticlePartFragment();
        apf.setArguments(b);
        return apf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_article_part, container, false);
        articleNum = getArguments().getInt(NUM, -1);
        tv_article = (TextView) view.findViewById(R.id.articleContent);
        tv_article.setText(ar.articleList.get(articleNum).getMainPart());

        rb_level = (RatingBar) view.findViewById(R.id.ratingBar);
        rb_level.setStepSize(1.0f);
        rb_level.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                Toast.makeText(context, "You choose word level:" + rating, Toast.LENGTH_SHORT).show();
                if (rating > 0) {
                    new Thread(new HighlightRunnable((int) rating)).start();
                }


            }
        });

        s_highlight = (Switch) view.findViewById(R.id.switcher);
        s_highlight.setEnabled(false);
        s_highlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    tv_article.setText(ar.articleList.get(articleNum).getMainPart());
                    s_highlight.setEnabled(false);
                    rb_level.setRating(0);
                }
            }
        });


        return view;
    }

    final Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==FINISH) {
                tv_article.setText(styled);
                s_highlight.setChecked(true);
                s_highlight.setEnabled(true);
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
            styled = new SpannableStringBuilder(ar.articleList.get(articleNum).getMainPart());

            switch ((int)rating){
                case 5:
                    for (String word:wl.l_5List) {
                        int start = ar.articleList.get(articleNum).getMainPart().indexOf(word);
                        int end = start+ word.length();
                        if (start<0){
                            continue;
                        }
                        styled.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    };
                case 4:
                    for (String word:wl.l_4List) {
                        int start = ar.articleList.get(articleNum).getMainPart().indexOf(word);
                        int end = start+ word.length();
                        if (start<0){
                            continue;
                        }
                        styled.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    };
                case 3:
                    for (String word:wl.l_3List) {
                        int start = ar.articleList.get(articleNum).getMainPart().indexOf(word);
                        int end = start+ word.length();
                        if (start<0){
                            continue;
                        }
                        styled.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    };
                case 2:
                    for (String word:wl.l_2List) {
                        int start = ar.articleList.get(articleNum).getMainPart().indexOf(word);
                        int end = start+ word.length();
                        if (start<0){
                            continue;
                        }
                        styled.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    };
                case 1:
                    for (String word:wl.l_1List) {
                        int start = ar.articleList.get(articleNum).getMainPart().indexOf(word);
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
