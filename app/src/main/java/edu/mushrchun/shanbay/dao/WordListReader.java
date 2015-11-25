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

/**
 * Created by mushrchun on 15-11-25.
 */
public class WordListReader {

    private WordListReader(){}
    private static WordListReader wordListReader= new WordListReader();
    public static WordListReader getInstance(){
        return wordListReader;
    }

    private Map<String,Integer> wordList = new HashMap<String,Integer>();
    public List<String> l_1List = new ArrayList<String>();
    public List<String> l_2List = new ArrayList<String>();
    public List<String> l_3List = new ArrayList<String>();
    public List<String> l_4List = new ArrayList<String>();
    public List<String> l_5List = new ArrayList<String>();

    public boolean Load(InputStream is){
        try {
            InputStreamReader isr =  new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            line = br.readLine();
            while(line!=null){
                String[] strArr = line.split("\t");
                wordList.put(strArr[0],Integer.valueOf(strArr[1]));
                switch(Integer.parseInt(strArr[1])){
                    case 1:l_1List.add(strArr[0]);break;
                    case 2:l_2List.add(strArr[0]);break;
                    case 3:l_3List.add(strArr[0]);break;
                    case 4:l_4List.add(strArr[0]);break;
                    case 5:l_5List.add(strArr[0]);break;
                }
                line = br.readLine();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
