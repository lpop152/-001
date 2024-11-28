package org.campus.pojo;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

//因为已经有封装的班级信息了，所以不用额外写辅导员id和姓名，班级信息里都有
@Document(collection = "user")
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	 @Id
	 private String _id;

	 String name;
	 Integer sex;
	 Integer roleType;  //1.学生  2.父母  3.辅导员 4.领导
	 String roleName;//班级职务
	 String sno;  //学号
	 ClassInfo cIno;  //学生班级信息
	 String pid;   //父母Id
	 String telephone; //电话
	 int status; //0 正常，1休学 2服兵役
}
