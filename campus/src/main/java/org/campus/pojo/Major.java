package org.campus.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Major implements Serializable {
    private String name;//专业名称
    private String grade;//年级
    private List<Classes> classes;//班级列表

}
