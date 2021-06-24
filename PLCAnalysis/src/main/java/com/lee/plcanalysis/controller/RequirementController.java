package com.lee.plcanalysis.controller;

import com.lee.plcanalysis.pojo.Requirement;
import com.lee.plcanalysis.service.RequirementService;
import com.lee.plcanalysis.service.excption.FailedToCreateRequirementException;
import com.lee.plcanalysis.service.excption.RequirementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/requirements")
public class RequirementController {

    RequirementService requirementService;

    @Autowired
    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping
    public List<Requirement> getProjectRequirements(@RequestParam("project") Long projectId){
        return requirementService.getProjectRequirements(projectId);
    }

    @GetMapping("/{id}")
    public Requirement getRequirement(@PathVariable("id") Long reqId){
        return requirementService.getRequirement(reqId).orElseThrow(() -> new RequirementNotFoundException(reqId));
    }

    @PostMapping
    public Requirement createRequirement(@RequestBody Requirement requirement){
        return requirementService.createRequirement(requirement).orElseThrow(() -> new FailedToCreateRequirementException(requirement));
    }

    @PutMapping
    public Requirement updateRequirement(@RequestBody Requirement requirement){
        return requirementService.updateRequirement(requirement).orElseThrow(() -> new FailedToCreateRequirementException(requirement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequirement(@PathVariable("id") Long id){
        return requirementService.getRequirement(id)
                .map(requirement -> {
                    requirementService.deleteRequirement(id);
                    return new ResponseEntity<>(requirement, HttpStatus.OK);
                })
                .orElseThrow(() -> new RequirementNotFoundException(id));
    }

    @PostMapping("/file")
    public ResponseEntity<?> singleFileUpload(@RequestParam("pId") Long projectId,
                                              @RequestParam("file") MultipartFile file,
                                              RedirectAttributes redirectAttributes){
        List<Requirement> requirements = requirementService.parseFile(file, projectId);
        if(requirements != null){
            return new ResponseEntity<>(requirements, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
