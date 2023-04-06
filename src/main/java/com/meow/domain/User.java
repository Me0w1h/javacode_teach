package com.meow.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.context.annotation.Bean;


@Data
@TableName("tb_user")
public class User {
    private String id;
    private String username;
    private String password;
    private String name;
    @TableField("class_name")
    private String className;
    @TableField("class_id")
    private String classId;
    private String school;
    private String exam;//用作显示已提交考试ID

    public User() {
    }

    public User(String id, String username, String password, String name, String className, String classId, String school, String exam) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.className = className;
        this.classId = classId;
        this.school = school;
        this.exam = exam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", classId='" + classId + '\'' +
                ", school='" + school + '\'' +
                ", exam='" + exam + '\'' +
                '}';
    }
}
