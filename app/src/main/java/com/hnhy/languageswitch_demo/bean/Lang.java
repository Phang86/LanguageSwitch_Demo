package com.hnhy.languageswitch_demo.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class Lang extends LitePalSupport {

    private int id;
    //activity名字
    public String activityName;
    //控件id   例如：2131231049；设置控件id唯一，避免重复
    @Column(unique = true)
    private long viewId;
    //控件类型  例如：Button、TextView
    private  String viewType;
    //控件文本英文
    @Column(nullable = false)
    private  String enName;
    //控件文本中文
    private  String cnName;
    //控件语言状态；zh-cn：中文；en-us：英文；默认中文
    @Column(defaultValue = "zh-cn")
    private String languageState;

    public String getLanguageState() {
        return languageState;
    }

    public void setLanguageState(String languageState) {
        this.languageState = languageState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public long getViewId() {
        return viewId;
    }

    public void setViewId(long viewId) {
        this.viewId = viewId;
    }

    public String getViewTpye() {
        return viewType;
    }

    public void setViewTpye(String viewTpye) {
        this.viewType = viewTpye;
    }

    public Lang(int id, String activityName, long viewId, String viewTpye, String enName, String cnName) {
        this.id = id;
        this.activityName = activityName;
        this.viewId = viewId;
        this.viewType = viewTpye;
        this.enName = enName;
        this.cnName = cnName;
    }

    public Lang(String activityName, long viewId, String viewTpye, String enName, String cnName) {
        this.activityName = activityName;
        this.viewId = viewId;
        this.viewType = viewTpye;
        this.enName = enName;
        this.cnName = cnName;
    }

    public Lang(String activityName, long viewId, String viewTpye, String enName, String cnName, String languageState) {
        this.activityName = activityName;
        this.viewId = viewId;
        this.viewType = viewTpye;
        this.enName = enName;
        this.cnName = cnName;
        this.languageState = languageState;
    }

    public Lang() {
    }

    @Override
    public String toString() {
        return "Lang{" +
                "id=" + id +
                ", activityName='" + activityName + '\'' +
                ", viewId=" + viewId +
                ", viewType='" + viewType + '\'' +
                ", enName='" + enName + '\'' +
                ", cnName='" + cnName + '\'' +
                ", languageState=" + languageState +
                '}';
    }


}
