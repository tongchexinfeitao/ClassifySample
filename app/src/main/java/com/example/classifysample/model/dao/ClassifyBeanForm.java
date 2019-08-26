package com.example.classifysample.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

//5、声明一个类，用来生成对应的表
@Entity
public class ClassifyBeanForm {
    @Id
    private Long id;

    //6、在表中声明一个String 类型的字段，用来存储json
    //这里存储的是联网请求下来的json字符串
    private String classifyBeanjson;

    @Generated(hash = 261319713)
    public ClassifyBeanForm(Long id, String classifyBeanjson) {
        this.id = id;
        this.classifyBeanjson = classifyBeanjson;
    }

    @Generated(hash = 283563024)
    public ClassifyBeanForm() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassifyBeanjson() {
        return this.classifyBeanjson;
    }

    public void setClassifyBeanjson(String classifyBeanjson) {
        this.classifyBeanjson = classifyBeanjson;
    }
}
