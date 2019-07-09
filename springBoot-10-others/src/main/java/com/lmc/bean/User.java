package com.lmc.bean;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 使用elasticsearch的pojo对象
 * @indexName: 数据库名称
 * @type: 表名称
 */
@Document(indexName = "demo",type = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String username;

    private String password;

    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(long id, String username, String password, int age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User() {
    }
}
