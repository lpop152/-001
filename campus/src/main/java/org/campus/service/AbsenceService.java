package org.campus.service;

import org.campus.pojo.Absence;
import org.campus.pojo.Result;

import java.util.List;

public interface AbsenceService {
     /**
     * 学生申请请假
     * @param abs 请假信息
     * @return 结果信息
     */
    Result apply(Absence abs);

    /**
     * 学生查看自己的未销假记录
     * @param studentId 学生ID
     * @return 请假记录列表
     */
    List<Absence> viewByStudent(Integer studentId);

    /**
     * 根据父母ID查看孩子未销假的请假记录
     * @param pid 父母ID
     * @return 请假记录列表
     */
    List<Absence> viewByParent(Integer pid);

     /**
     * 父母处理孩子的请假申请
     * @param id 请假ID
     * @param type 操作类型：1表示同意，-1表示不同意
     * @param reason 拒绝原因（同意时为空）
     */
    String processByParent(Integer pid, Integer id, Integer type, String reason);


    /**
     * 辅导员查看请假未离校的请假记录
     * @param aid 管理员ID
     * @return 请假记录列表
     */
    List<Absence> viewAbsenceWithoutLeaving(Integer aid);


    /**
     * 辅导员查看请假离校的请假记录
     * @param aid 管理员ID
     * @return 请假记录列表
     */
    List<Absence> viewAbsenceWithLeaving(Integer aid);
}
