package com.lee.plcanalysis.service;


import com.lee.plcanalysis.core.verify.VerifyFacade;
import com.lee.plcanalysis.core.verify.work.Trace;
import com.lee.plcanalysis.pojo.Context;
import com.lee.plcanalysis.pojo.Requirement;
import com.lee.plcanalysis.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskService {
    private RequirementService requirementService;
    private ContextService contextService;
    private VerifyFacade verifyFacade;

    @Autowired
    public TaskService(RequirementService requirementService, ContextService contextService) {
        this.requirementService = requirementService;
        this.contextService = contextService;
    }

    public Task doVerifyTask(Long projectId){
        List<Requirement> requirements = requirementService.getProjectRequirements(projectId);
        StringBuilder reqStr = new StringBuilder();
        requirements.forEach(req -> reqStr.append(req.getText()).append("\n"));

        Context context = contextService.getContext(projectId);

        try{
            if(verifyFacade == null){
                verifyFacade = new VerifyFacade();
                verifyFacade.submitRequirement(reqStr.toString(), context.getPriorityArrayList(), false);
                verifyFacade.submitProgram(context.getCode(), false);
            }
            verifyFacade.submitVerifyTaskProcess();
        }catch (Exception e){
            e.printStackTrace();
            return new Task("验证任务执行失败，请查看日志！");
        }

        List<Map<String, Boolean>> results = verifyFacade.getResults();
        List<Map<String, Trace>> traces = verifyFacade.getTraces();

        /*
        * 需求更新逻辑，验证状态改变，失败的状态添加反例
        * */

        return new Task("执行成功！需求列表已更新！");
    }

    public Task doTestTask(List<Integer> selected){
        if(verifyFacade == null){
            return new Task("请先进行验证任务！");
        }
        try{
            verifyFacade.submitTestGenTaskProcess(selected);
        }catch (Exception e){
            e.printStackTrace();
            return new Task("测试生成任务执行失败，请查看日志！");
        }

        Map<List<String>, List<Trace>> testCases = verifyFacade.getTestCases();

        /*
        * 测试用例填充
        * */

        return new Task("测试生成完毕！");
    }
}
