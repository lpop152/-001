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
    public Result apply(@RequestBody Absence abs) {
        // 数据校验
        if (abs.getSid() == null || abs.getStuTelephone() == null ||
            abs.getStuClassInfo() == null || abs.getAbsenceReason() == null) {
            return new Result("FAIL", "申请失败：请假数据不完整！");
        }
        return absenceService.apply(abs);
    }


    /**
     * 一个学生查看他的请假，除了销假
     * @param studentId
     * @return
     */
    public Absence viewByStudent(Integer studentId) {
    	return null;
    }




    /**
     * 一个家长看他的孩子请假
     * @param parentId
     * @return
     */
    public Absence viewByParent(Integer parentId) {
    	return null;
    }




    /**
     * 父母对孩子请假进行处理，同意或不同意，不同意要有原因
     * @param id
     * @param type
     * @param reason
     */
    public void processByParent(Integer id,Integer type,String reason) {
    	
    }




    /**
     * 辅导员同意了，但是到当前，假期还未开始的同学
     * @param aid
     * @return
     */
    public List<Absence> noStart(Integer aid){
    	return null;
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
     * 请假中，未离校学生名单
     * @param aid
     * @return
     */
    public List<Absence> viewAbsenceWithoutLeaving(Integer aid){
    	return null;
    
    }




    /**
     * 请假中，离校学生名单
     * @param aid
     * @return
     */
    public List<Absence> viewAbsenceWithLeaving(Integer aid){
    	return null;
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
