package org.campus.pojo;

import lombok.Data;

@Data
public class Absence {
	Integer id;  //这个请假id
	Integer sid;  //学生id
	String stuTelephone; //学生电话
	String  stuClassInfo; //学生年级，专业，班级
	Integer aid;   //辅导员id
	Integer pid;  // 父母id
	int status;   //0申请，1，父母同意，2老师同意，-1 父母不同意，-2，老师不同意,3销假
	int type;     //0在校，1离校
	String rejectReason;  //老师不同意，或父母不同意原因
}
