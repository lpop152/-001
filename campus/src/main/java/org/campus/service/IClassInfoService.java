package org.campus.service;

import org.campus.pojo.ClassInfo;
import org.campus.pojo.Classes;
import org.campus.pojo.Major;

import java.util.List;

public interface IClassInfoService {
    /**
     * 获取班级信息
     * @return
     */
    List<ClassInfo> getGrade();

    /**
     * 一个年级下的专业
     * @param grade
     * @return
     */
    List<Major> getMajor(String grade);
    /**
     * 一个专业下的班级
     * @param major
     * @return
     */
    List<Classes> getClass(String major);

    List<Classes> getClassById(Integer id);
}
