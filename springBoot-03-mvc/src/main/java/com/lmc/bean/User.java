package com.lmc.bean;
/**
 * 使用JSR-303进行验证
 */

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class User {
    @NotNull(message = "名称不能为空")
    private String name;

    @NotNull(message = "密码不能为空")
    @Size(min = 6,max = 15,message = "密码长度要求6到15之间")
    private String password;

    @NotNull(message = "年龄不能为空")
    @Min(value = 1)
    @Max(value = 200)
    private Integer age;

    @Past(message = "需要一个过去的日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "出生日期不能为空")
    private Date birthday;

    @DecimalMin(value = "0.1")
    @DecimalMax(value = "10000.00")
    @NotNull
    private Double money;

    @Email(message = "邮箱格式错误")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", money=" + money +
                ", email='" + email + '\'' +
                '}';
    }
}
