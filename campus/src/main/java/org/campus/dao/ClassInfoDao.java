package org.campus.dao;

import org.campus.pojo.ClassInfo;
import org.campus.pojo.Classes;
import org.campus.pojo.Major;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassInfoDao implements IClassInfoDao{
    private static final Logger log = LoggerFactory.getLogger(ClassInfo.class);
    @Autowired
    MongoTemplate mongoTemplate;
    /**
     * 获取级信息
     * @return
     */
    @Override
    public List<ClassInfo> getGrade() {
        List<ClassInfo> gradeClasses = mongoTemplate.findAll(ClassInfo.class);
        return gradeClasses;
    }

    /**
     * 一个年级下的专业信息
     *
     * @param grade
     * @return
     */
    public List<Major> getMajor(String grade) {
        Query query = new Query(Criteria.where("children.grade").is(grade));

        // 使用projection来仅选择children字段
        query.fields().include("children");

        List<ClassInfo> classInfos = mongoTemplate.find(query, ClassInfo.class);

        // 创建一个新的列表来存储所有的Major对象
        List<Major> majors = new ArrayList<>();

        // 遍历classInfos列表，并将每个ClassInfo对象中的Major对象添加到新的列表中
        for (ClassInfo classInfo : classInfos) {
            List<Major> children = classInfo.getChildren();
            for (Major child : children) {
                if (child.getGrade().equals(grade)){
                    majors.add(child);
                }
            }
        }
        return majors;
    }


    @Override
    public List<Classes> getClass(String major) {
        Query query = new Query(Criteria.where("children.name").is(major));
        // 使用projection来仅选择classes字段
        query.fields().include("children");
        List<ClassInfo> classInfos = mongoTemplate.find(query, ClassInfo.class);
        List<Classes> classes = new ArrayList<>();
        for (ClassInfo classInfo : classInfos) {
            for (Major major1 : classInfo.getChildren()) {
                if (major1.getName().equals(major)){
                    classes = major1.getClasses();
                }
            }

        }
        return classes;
    }

    @Override
    public List<Classes> getClassById(Integer id) {
        List<ClassInfo> classInfos = mongoTemplate.findAll(ClassInfo.class);
        List<Classes> classesInfos = new ArrayList<>();
        for (ClassInfo classInfo : classInfos) {
            for (Major major : classInfo.getChildren()) {
                for (Classes classes : major.getClasses()) {
                    if (classes.getClassId() != null && classes.getClassId().equals(id)) {
                        classesInfos.add(classes);
                    }
                }
            }
        }
        return classesInfos;
    }

}
