package com.lee.plcanalysis.controller;

import com.lee.plcanalysis.pojo.Task;
import com.lee.plcanalysis.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{id}")
    public Task postVerifyTask(@PathVariable("id") Long projectId){
        return taskService.doVerifyTask(projectId);
    }

    @PostMapping
    public Task postTestTask(@RequestParam("selected") List<Integer> selected){
        return taskService.doTestTask(selected);
    }
}
