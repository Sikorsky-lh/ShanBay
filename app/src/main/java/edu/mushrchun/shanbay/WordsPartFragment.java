package edu.mushrchun.shanbay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.mushrchun.shanbay.dao.ArticleReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordsPartFragment extends Fragment {

    public static final String NUM = "NUM";
    private ArticleReader ar = ArticleReader.getInstance();
    private int articleNum;
    private TextView tv_newWords;

    public static WordsPartFragment newInstance(int num){
        Bundle b = new Bundle();
        b.putInt(NUM,num);
        WordsPartFragment wpf= new WordsPartFragment();
        wpf.setArguments(b);
        return wpf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_words_part, container, false);

        articleNum = getArguments().getInt(NUM, -1);
        tv_newWords = (TextView) view.findViewById(R.id.new_words);
        tv_newWords.setText(ar.articleList.get(articleNum).getWordsPart());

        return view;
    }


}
