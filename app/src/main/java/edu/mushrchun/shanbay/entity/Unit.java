package edu.mushrchun.shanbay.entity;

/**
 * Created by mushrchun on 15-11-21.
 */
public class Unit {
    private int unitNum;
    private int lessonNum;

    public Unit(int unitNum){
        this.unitNum = unitNum;
    }
    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public int getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(int lessonNum) {
        this.lessonNum = lessonNum;
    }
}
