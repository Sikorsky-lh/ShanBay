package edu.mushrchun.shanbay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.mushrchun.shanbay.dao.ArticleReader;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        int articleNum = getIntent().getIntExtra("article-num", -1);
        TextView tv_article = (TextView) findViewById(R.id.articleContent);
        ArticleReader ar = ArticleReader.getInstance();
        tv_article.setText(ar.articleList.get(articleNum).getContent());

    }
}
