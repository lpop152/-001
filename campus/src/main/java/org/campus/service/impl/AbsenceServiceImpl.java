package org.campus.service.impl;

import org.campus.dao.AbsenceRepository;
import org.campus.pojo.Absence;
import org.campus.pojo.Result;
import org.campus.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceServiceImpl implements AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Override
    public Result<String> apply(Absence abs) {
        // 检查学生是否有未销假的记录
        boolean hasUnresolvedAbsence = absenceRepository.existsBySidAndStatusNot(abs.getSid(), 3);

        if (hasUnresolvedAbsence) {
            return new Result<>("FAIL", "申请失败：存在未销假的请假记录！", null);
        }

        // 设置申请状态为“申请中”（status = 0）
        abs.setStatus(0);

        // 保存到数据库
        absenceRepository.save(abs);

        return new Result<>("SUCCESS", "请假申请成功！", null);
    }

    @Override
    public List<Absence> viewByStudent(Integer studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("学生ID不能为空！");
        }
        // 查询未销假的请假记录
        return absenceRepository.findBySidAndStatusNot(studentId, 3);
    }

    @Override
    public List<Absence> viewByParent(Integer pid) {
        if (pid == null) {
            throw new IllegalArgumentException("父母ID不能为空！");
        }

        // 查询父母关联的未销假记录，只返回离校（type = 1）的请假记录
        return absenceRepository.findByPidAndStatusNotAndType(pid, 3, 1);
    }


   @Override
    public String processByParent(Integer pid, Integer id, Integer type, String reason) {
        if (pid == null || id == null || type == null) {
            throw new IllegalArgumentException("父母ID、请假ID和操作类型不能为空！");
        }

        // 检查操作类型是否合法
        if (type != 1 && type != -1) {
            throw new IllegalArgumentException("操作类型只能是1（同意）或-1（不同意）！");
        }

        // 查询父母ID关联的学生请假记录
        List<Absence> absences = absenceRepository.findByPidAndStatusNotAndType(pid, 3, 1);  // 查询未销假且离校的记录

        // 查找该父母下是否有对应的请假记录
        Optional<Absence> absenceOptional = absenceRepository.findById(id);


        if (!absenceOptional.isPresent()) {
            throw new IllegalArgumentException("未找到该父母关联的请假记录！");
        }

        Absence absence = absenceOptional.get();

        // 检查是否能操作该记录（仅限待审批的记录）
        if (absence.getStatus() != 0) {  // 状态 0 表示待审批
            throw new IllegalArgumentException("请假记录不可操作！");
        }

        // 执行父母的操作：同意或拒绝
        absence.setStatus(type);  // 更新状态：1为同意，-1为拒绝
        if (type == -1) {
            // 如果是拒绝，设置拒绝原因
            if (reason == null || reason.trim().isEmpty()) {
                throw new IllegalArgumentException("不同意操作必须提供拒绝原因！");
            }
            absence.setRejectReason(reason);
        } else {
            absence.setRejectReason(null); // 同意时清空拒绝原因
        }

        // 保存更新后的请假记录
        absenceRepository.save(absence);
       return reason;
   }



// 辅导员查看请假未离校的请假记录
    @Override
    public List<Absence> viewAbsenceWithoutLeaving(Integer aid) {
        // 获取该辅导员的所有请假记录，筛选出未离校的记录
        // 只考虑状态为已同意 (status = 2) 和请假类型为在校 (type = 0)
        return absenceRepository.findByAidAndTypeAndStatus(aid, 0, 2);
    }

    // 辅导员查看请假离校的请假记录
    @Override
    public List<Absence> viewAbsenceWithLeaving(Integer aid) {
        // 获取该辅导员的所有请假记录，筛选出已离校的记录
        // 只考虑状态为已同意 (status = 2) 和请假类型为离校 (type = 1)
        return absenceRepository.findByAidAndTypeAndStatus(aid, 1, 2);
    }


}
