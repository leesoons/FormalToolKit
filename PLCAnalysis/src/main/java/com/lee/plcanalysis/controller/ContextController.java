package com.lee.plcanalysis.controller;

import com.lee.plcanalysis.service.excption.FailedToUpdateContextException;
import com.lee.plcanalysis.pojo.Context;
import com.lee.plcanalysis.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/context")
public class ContextController {

    ContextService contextService;

    @Autowired
    public ContextController(ContextService contextService) {
        this.contextService = contextService;
    }

    @PostMapping
    public ResponseEntity<?> addContext(@Valid @RequestBody Context context){
        return contextService.addContext(context)
                .map(context1 -> new ResponseEntity<>(context1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @PutMapping
    public Context updateContext(@RequestBody Context context){
        return contextService.updateContext(context)
                .orElseThrow(() -> new FailedToUpdateContextException(context));
    }
}
