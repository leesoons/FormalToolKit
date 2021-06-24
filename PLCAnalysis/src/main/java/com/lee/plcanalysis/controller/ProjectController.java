package com.lee.plcanalysis.controller;


import com.lee.plcanalysis.service.excption.ProjectNotFoundException;
import com.lee.plcanalysis.pojo.Project;
import com.lee.plcanalysis.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getProjectsOfAuthUser();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable("id") Long id){
        return projectService.getProjectAuthUser(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<?> newProject(@Valid @RequestBody Project  project){
        return projectService.saveProject(project)
                .map(project1 -> new ResponseEntity<>(project1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        System.out.println("---");
        return projectService.getProjectAuthUser(id).map(project -> {
            projectService.deleteProject(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        }).orElseThrow(() -> new ProjectNotFoundException(id));
    }
}
