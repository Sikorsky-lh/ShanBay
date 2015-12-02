package edu.mushrchun.shanbay.entity;

/**
 * Created by mushrchun on 15-11-21.
 */
public class Article {

    private int lessonNum;
    private int unitNum;
    private String content;
    private String [] splitArr= new String[3];;

    public int getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(int lessonNum) {
        this.lessonNum = lessonNum;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMainPart(){
        if(splitArr[0]==null){
            doSplit();
        }
        return splitArr[0];
    }

    private void doSplit(){
        String[] tString= content.split("New words and expressions?.(生词和短语)?");
        splitArr[0] = tString[0];
        splitArr[1] = tString[1].split("参考译文")[0];
        splitArr[2] = tString[1].split("参考译文")[1];
    }

    public String getWordsPart(){
        if(splitArr[1]==null){
            doSplit();
        }
        return splitArr[1];
    }

    public String getTranslation(){
        if(splitArr[2]==null){
            doSplit();
        }
        return splitArr[2];
    }

}
