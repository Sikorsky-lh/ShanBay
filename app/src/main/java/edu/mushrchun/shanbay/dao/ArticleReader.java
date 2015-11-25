package edu.mushrchun.shanbay.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mushrchun.shanbay.entity.Article;
import edu.mushrchun.shanbay.entity.Unit;

/**
 * Created by mushrchun on 15-11-21.
 */
public class ArticleReader {

    private StringBuilder m_sb;
    private String m_s;

    public List<Article> articleList;

    public ArticleReader articleReader = this;

    private ArticleReader(){

    }

    private static ArticleReader s_articleReader = new ArticleReader();
    public static ArticleReader getInstance(){
        return s_articleReader;
    }
    public boolean Read(int order){
        return true;
    }

    public int ScanNum(){

        m_s.split("Unit");
        return 0;
    }

    public boolean Analyse(){
        m_s = m_sb.toString();

        articleList = new ArrayList<Article>();
        String[] articleArr = m_s.split("Lesson [0-9][0-9\\s]");
            for(int j=1;j<=articleArr.length;j++){
                Article a;
                if(!articleArr[j-1].trim().startsWith("Unit")){
                    a = new Article();
                    a.setLessonNum(j);
                    a.setContent(articleArr[j-1]);
                    articleList.add(a);
                }


            }


        return true;
    }

    public String ReadAll(){
        return m_sb.toString();
    }

    public Boolean Load(InputStream is){
        m_sb = new StringBuilder();
        try {
            InputStreamReader isr =  new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while((line=br.readLine())!=null){
                m_sb.append(line);
                m_sb.append('\n');

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
