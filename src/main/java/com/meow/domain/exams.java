package com.meow.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("exams")
public class exams {
    private String id ;
    @TableField("exam_name")
    private String examName;
    private String date;
    private int status;
    private  String description;

}
