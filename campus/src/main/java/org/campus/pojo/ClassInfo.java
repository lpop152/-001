package org.campus.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "classInfo")
@Data
public class ClassInfo {
	 @Field("id")
	 Integer id;
	 @Id
	 Integer _id;
	 int grade;  //级
	 String major; //专业
	 String clas;  //班级
	 Integer aid;  //辅导员Id
	 String aname;  //辅导员名称
	 int number;    //学生人数
	 
}
