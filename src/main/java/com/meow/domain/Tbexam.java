package com.meow.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Tbexam {
    @TableField("class_id")
    String classId;
    @TableField("class_name")
    String className;
    int count;
    String score;
    int total;




}
