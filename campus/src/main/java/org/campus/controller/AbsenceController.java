package org.campus.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.campus.dao.AbsenceRepository;
import org.campus.pojo.Absence;
import org.campus.pojo.Result;
import org.campus.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/absence")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;


	/**
     * 学生申请请假
     * @param abs 请假信息
     * @return 操作结果
     */
    @PostMapping("/apply")
    public Result<String> apply(@RequestBody Absence abs) {
        // 数据校验
        if (abs.getSid() == null || abs.getStuTelephone() == null ||
            abs.getStuClassInfo() == null || abs.getAbsenceReason() == null) {
            return new Result<>("FAIL", "申请失败：请假数据不完整！", null);
        }
        return absenceService.apply(abs);
    }


    /**
     * 学生查看自己的未销假记录
     * @param studentId 学生ID
     * @return 请假记录列表
     */
    @GetMapping("/viewByStudent")
    public Result<List<Absence>> viewByStudent(@RequestParam Integer studentId) {
        try {
            List<Absence> absences = absenceService.viewByStudent(studentId);
            if (absences.isEmpty()) {
                return new Result<>("SUCCESS", "暂无未销假的请假记录！", null);
            }
            return new Result<>("SUCCESS", "查询成功！", absences);
        } catch (IllegalArgumentException e) {
            return new Result<>("FAIL", e.getMessage(), null);
        }
}





     /**
     * 家长查看孩子未销假的请假记录（仅限离校）
     * @param pid 父母ID
     * @return 孩子的请假记录列表
     */
    @GetMapping("/viewByParent")
    public Result<List<Absence>> viewByParent(@RequestParam Integer pid) {
        try {
            List<Absence> absences = absenceService.viewByParent(pid);
            if (absences.isEmpty()) {
                return new Result<>("SUCCESS", "暂无未销假的请假记录！", null);
            }
            return new Result<>("SUCCESS", "查询成功！", absences);
        } catch (IllegalArgumentException e) {
            return new Result<>("FAIL", e.getMessage(), null);
        }
    }




     /**
     * 父母处理孩子的请假申请
      * @param  pid 父母ID
     * @param id 请假ID
     * @param type 操作类型：1表示同意，-1表示不同意
     * @param reason 拒绝原因（同意时可为空）
     * @return 操作结果
     */
   @PostMapping("/processByParent")
public String processByParent(@RequestParam Integer pid, // 父母ID
                               @RequestParam Integer id,  // 请假记录ID
                               @RequestParam Integer type, // 操作类型：1表示同意，-1表示不同意
                               @RequestParam(required = false) String reason) {

    try {
        absenceService.processByParent(pid, id, type, reason);
        return "操作成功";
    } catch (IllegalArgumentException e) {
        return "操作失败: " + e.getMessage();
    }
}




    /**
     * 一个辅导员看到向他申请的请假,不离校直接看到，离校的父母同意之后，才能看到
     * @param aid
     * @return
     */
    public List<Absence> viewApply(Integer aid){
    	return null;
    }





    /**
     * 辅导员处理，
     * @param id
     * @param type
     * @param reason
     */
    public  void processByAssistant(Integer id,Integer type,String reason){
    	
    }

   /**
     * 辅导员看到同意了，但是到当前，假期还未开始的同学
     * @param aid
     * @return
     */
    public List<Absence> noStart(Integer aid){
    	return null;
    }


    /**
     * 辅导员同意，而且start<=当前时间<=end
     * @param aid
     * @return
     */
    public List<Absence> viewAbsencing(Integer aid){
    	return null;
    }


    /**
     * 辅导员同意，而且当前时间>end
     * @param aid
     * @return
     */
    public List<Absence> viewNoEnd(Integer aid){
    	return null;
    }





    /**
     * 一个辅导员下面的，请假中离校学生人数，未离校学生人数
     * 若aid是-1，是统计所有，下面一样
     * @param aid
     * @return
     */
    public Map<Integer,Integer> viewAbsencingCount(Integer aid){
    	return null;
    }





   /**
     * 辅导员查看请假中未离校的学生名单
     * @param aid 辅导员ID
     * @return 未离校的学生名单
     */
    @GetMapping("/viewAbsenceWithoutLeaving")
    public Result<List<Absence>> viewAbsenceWithoutLeaving(@RequestParam Integer aid) {
        try {
            // 从 service 层获取未离校的学生名单
            List<Absence> absences = absenceService.viewAbsenceWithoutLeaving(aid);
            if (absences.isEmpty()) {
                return new Result<>("SUCCESS", "暂无未离校的学生记录！", null);
            }
            return new Result<>("SUCCESS", "查询成功！", absences);
        } catch (IllegalArgumentException e) {
            return new Result<>("FAIL", e.getMessage(), null);
        }
    }

    /**
     * 辅导员查看请假中离校的学生名单
     * @param aid 辅导员ID
     * @return 离校的学生名单
     */
    @GetMapping("/viewAbsenceWithLeaving")
    public Result<List<Absence>> viewAbsenceWithLeaving(@RequestParam Integer aid) {
        try {
            // 从 service 层获取已离校的学生名单
            List<Absence> absences = absenceService.viewAbsenceWithLeaving(aid);
            if (absences.isEmpty()) {
                return new Result<>("SUCCESS", "暂无离校的学生记录！", null);
            }
            return new Result<>("SUCCESS", "查询成功！", absences);
        } catch (IllegalArgumentException e) {
            return new Result<>("FAIL", e.getMessage(), null);
        }
    }





    /**
     * 销假
     * @param id
     * @return
     */
    public boolean end(Integer id) {
    	return true;
    }




    /**
     * 父母不同意，或老师不同意，修改后重新提交，这时候转态回到申请状态
     * @param id
     * @return
     */
    public boolean update(Integer id) {
    	return true;
    }


}
