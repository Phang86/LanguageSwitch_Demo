package com.hnhy.languageswitch_demo.greenDao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {
    @Id(autoincrement = true)
    private long id;
    @Unique
    private long viewId;
    private String cnName;
    private String enName;
    private String languageState;
    @Generated(hash = 505861997)
    public Student(long id, long viewId, String cnName, String enName,
            String languageState) {
        this.id = id;
        this.viewId = viewId;
        this.cnName = cnName;
        this.enName = enName;
        this.languageState = languageState;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getViewId() {
        return this.viewId;
    }
    public void setViewId(long viewId) {
        this.viewId = viewId;
    }
    public String getCnName() {
        return this.cnName;
    }
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
    public String getEnName() {
        return this.enName;
    }
    public void setEnName(String enName) {
        this.enName = enName;
    }
    public String getLanguageState() {
        return this.languageState;
    }
    public void setLanguageState(String languageState) {
        this.languageState = languageState;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", viewId=" + viewId +
                ", cnName='" + cnName + '\'' +
                ", enName='" + enName + '\'' +
                ", languageState='" + languageState + '\'' +
                '}';
    }
}
