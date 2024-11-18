package org.campus.service.impl;

import org.campus.dao.AbsenceRepository;
import org.campus.pojo.Absence;
import org.campus.pojo.Result;
import org.campus.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbsenceServiceImpl implements AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Override
public Result apply(Absence abs) {
    // 检查学生是否有任何非销假状态的记录（非status=3）
    boolean hasUnresolvedAbsence = absenceRepository.existsBySidAndStatusNot(abs.getSid(), 3);

    if (hasUnresolvedAbsence) {
        // 如果有未销假的记录，返回失败
        return new Result("FAIL", "申请失败：存在未销假的请假记录！");
    }

    // 设置新申请的状态为“申请中”（status = 0）
    abs.setStatus(0);

    // 保存到数据库
    absenceRepository.save(abs);

    return new Result("SUCCESS", "请假申请成功！");
}
}
