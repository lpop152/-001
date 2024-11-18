package org.campus.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	 @Id
	 private String id;
	 private String name;

	 private String telephone;

	 Integer sex;
	 Integer roleType;  //1.学生  2.父母  3.辅导员，4 领导
	 String roleName;
	 String sno;  //学号
	 ClassInfo cIno;  //学生班级信息
	 Integer pid;   //父母Id
	 Integer aid;   //辅导员Id
	 String aname;   //辅导员姓名
	 int status; //0 正常，1休学 2服兵役

	
	 /**
	 public String toString() {
		 return "id:"+id+","+"name:"+name;
	 }
	 **/
	 public static void main(String[] args) {
		 User u=new User();
		 System.out.println(u);
	 }

}
