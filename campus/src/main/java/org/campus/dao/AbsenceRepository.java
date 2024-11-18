package org.campus.dao;

import org.campus.pojo.Absence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepository extends MongoRepository<Absence, String> {
    /**
     * 判断是否存在未销假的请假记录
     * @param sid 学生 ID
     * @param status 排除状态码
     * @return 存在未销假记录返回 true
     */
    boolean existsBySidAndStatusNot(Integer sid, int status);
}
