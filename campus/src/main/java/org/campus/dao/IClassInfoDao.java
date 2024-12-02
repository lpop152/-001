package org.campus.dao;

import org.campus.pojo.ClassInfo;
import org.campus.pojo.Classes;
import org.campus.pojo.Major;

import java.util.List;

public interface IClassInfoDao {
    List<ClassInfo> getGrade();

    List<Major> getMajor(String grade);
    List<Classes> getClass(String majorId);

    List<Classes> getClassById(Integer id);
}
