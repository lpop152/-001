package org.campus.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;


import java.io.Serializable;

@Data
public class Classes implements Serializable {
    @Field("class_id")
    Integer classId;
    String name;//班级名称
    Integer number;//班级人数
    String aid;//辅导员id
    String aname;//辅导员姓名
}
