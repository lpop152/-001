package org.campus.dao;

import org.campus.pojo.Absence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends MongoRepository<Absence, String> {
    /**
     * 判断是否存在未销假的请假记录
     * @param sid 学生 ID
     * @param status 排除状态码
     * @return 存在未销假记录返回 true
     */
    boolean existsBySidAndStatusNot(Integer sid, int status);



    // 查询学生的所有未销假的请假记录
    List<Absence> findBySidAndStatusNot(Integer sid, Integer status);

     // 查询父母ID关联的未销假离校记录
    List<Absence> findByPidAndStatusNotAndType(Integer pid, int status, int type);


    //查询时通过来根据 id 字段查找请假记录，而不是通过 _id 字段
     Optional<Absence> findById(Integer id);

      // 查询辅导员下所有未离校（离校）的请假记录
    List<Absence> findByAidAndTypeAndStatus(Integer aid, Integer type, Integer status);


}
