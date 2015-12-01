package edu.mushrchun.shanbay;


import android.app.FragmentManager;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {


    public static String PAGE_NUM = "PAGE_NUM";

    private int m_PageNum;

    public static ContentFragment newInstance(int num){
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_NUM,num);
        ContentFragment cf = new ContentFragment();
        cf.setArguments(bundle);
        return cf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        m_PageNum = getArguments().getInt("PAGE_NUM");
        View view = inflater.inflate(R.layout.fragment_viewpager,container,false);
        TextView textView = (TextView) view.findViewById(R.id.pageview_text);
        textView.setText("#"+m_PageNum);
        return view;
    }


}
