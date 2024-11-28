package org.campus.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "classInfo")
@Data
public class ClassInfo {
	 @Id
	 String _id;
	 int grade;  //级
	 String major; //专业
	 String clas;  //班级
	 String aid;  //辅导员Id
	 String aname;  //辅导员名称
	 int number;    //学生人数
	 
}
