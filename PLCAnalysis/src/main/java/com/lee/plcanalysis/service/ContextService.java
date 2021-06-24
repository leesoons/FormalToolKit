package com.lee.plcanalysis.service;

import com.lee.plcanalysis.dao.ContextMapper;
import com.lee.plcanalysis.pojo.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContextService {
    private ContextMapper contextMapper;

    @Autowired
    public ContextService(ContextMapper contextMapper) {
        this.contextMapper = contextMapper;
    }

    public Context getContext(Long id){
        return contextMapper.findByProjectId(id);
    }

    public Optional<Context> addContext(Context context){
        if(contextMapper.save(context) == 1){
            return Optional.of(context);
        }
        return Optional.empty();
    }

    public Optional<Context> updateContext(Context context){
        if(contextMapper.updateContext(context) == 1){
            return Optional.of(context);
        }
        return Optional.empty();
    }
}
