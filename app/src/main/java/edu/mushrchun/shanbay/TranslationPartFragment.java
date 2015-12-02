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
public class TranslationPartFragment extends Fragment {

    public static final String NUM = "NUM";
    private ArticleReader ar = ArticleReader.getInstance();
    private int articleNum;

    private TextView tv_translation;

    public static TranslationPartFragment newInstance(int num){
        Bundle b = new Bundle();
        b.putInt(NUM,num);
        TranslationPartFragment tpf= new TranslationPartFragment();
        tpf.setArguments(b);
        return tpf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_translation_part, container, false);

        articleNum = getArguments().getInt(NUM, -1);
        tv_translation = (TextView) view.findViewById(R.id.translations);
        tv_translation.setText(ar.articleList.get(articleNum).getTranslation());

        return view;
    }


}
