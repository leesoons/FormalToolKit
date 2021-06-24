package com.lee.plcanalysis.service;

import com.lee.plcanalysis.dao.RequirementMapper;
import com.lee.plcanalysis.pojo.Requirement;
import com.lee.plcanalysis.service.excption.FailedToCreateRequirementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RequirementService {
    private RequirementMapper requirementMapper;

    @Autowired
    public RequirementService(RequirementMapper requirementMapper) {
        this.requirementMapper = requirementMapper;
    }

    public List<Requirement> getProjectRequirements(Long projectId){
        return requirementMapper.findByProjectOrderById(projectId);
    }

    public Optional<Requirement> createRequirement(Requirement requirement){
        if(requirementMapper.save(requirement) == 1){
            return Optional.of(requirement);
        }
        return Optional.empty();
    }

    public void deleteRequirement(Long id){
        requirementMapper.deleteById(id);
    }

    public Optional<Requirement> getRequirement(Long reqId){
        return Optional.ofNullable(requirementMapper.findById(reqId));
    }

    public Optional<Requirement> updateRequirement(Requirement requirement){
        if(requirementMapper.updateRequirement(requirement) == 1){
            return Optional.of(requirement);
        }
        return Optional.empty();
    }

    public List<Requirement> parseFile(MultipartFile file, Long projectId){
        try{
            List<Requirement> requirements = new ArrayList<>();
            Scanner scanner = new Scanner(file.getInputStream());

            while (scanner.hasNextLine()){
                String line = scanner.nextLine().trim();
                if(line.isEmpty() || line.contains("#")){
                    continue;
                }
                Requirement req = new Requirement(line, "", Requirement.ReqState.NOT_CHECKED, projectId, "");
                createRequirement(req).orElseThrow(() -> new FailedToCreateRequirementException(req));
                requirements.add(req);
            }
            return requirements;
        }catch (IOException e){
            return null;
        }
    }
}
