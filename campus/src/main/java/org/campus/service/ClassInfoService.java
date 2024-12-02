package org.campus.service;

import org.campus.dao.IClassInfoDao;
import org.campus.pojo.ClassInfo;
import org.campus.pojo.Classes;
import org.campus.pojo.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoService implements IClassInfoService{
    @Autowired
    private IClassInfoDao classInfoDao;

    /**
     * 获取级信息
     * @return
     */
    @Override
    public List<ClassInfo> getGrade() {
        return classInfoDao.getGrade();
    }

    /**
     * 一个年级下的专业
     * @param grade
     * @return
     */
    @Override
    public List<Major> getMajor(String grade) {
        return classInfoDao.getMajor(grade);
    }

    /**
     * 一个专业下的班级
     * @param major
     * @return
     */
    @Override
    public List<Classes> getClass(String major) {
        return classInfoDao.getClass(major);
    }

    @Override
    public List<Classes> getClassById(Integer id) {

        return classInfoDao.getClassById(id);
    }
}
