package org.campus.service;

import org.campus.pojo.Absence;
import org.campus.pojo.Result;

public interface AbsenceService {
     /**
     * 学生申请请假
     * @param abs 请假信息
     * @return 结果信息
     */
    Result apply(Absence abs);
}
