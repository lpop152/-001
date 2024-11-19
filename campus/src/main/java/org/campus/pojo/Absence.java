package org.campus.pojo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Absence")
public class Absence {

	@Id
	private String _id;  //这个是自动生成的主键值

	@Field("id")  // 映射 MongoDB 中的 id 字段
	Integer id;  //这个请假id
	Integer sid;  //学生id
	String stuTelephone; //学生电话
	String  stuClassInfo; //学生年级，专业，班级
	Integer aid;   //辅导员id
	Integer pid;  // 父母id
	int status;   //0申请，1，父母同意，2老师同意，-1 父母不同意，-2，老师不同意,3销假
	int type;     //0在校，1离校

	String AbsenceReason;  //请假原因
	String rejectReason;  //老师不同意，或父母不同意原因
}
