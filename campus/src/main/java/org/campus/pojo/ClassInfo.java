package org.campus.pojo;

import lombok.Data;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Document(collection="classInfo")
@Data
public class ClassInfo implements Serializable {
	@Id
	private String id;
	@Field("depart_id")
	Integer departId;//学院编号
	String name;//学院名称
	List<Major> children;//学院下的专业


}
