package com.lee.plcanalysis.core.plc.model;

import com.lee.plcanalysis.core.plc.parser.CodeContext;

import java.util.Set;

public class IL2Model {
    private ProgramModel programModel;

    public ProgramModel translate(CodeContext context, Set<String> reqVariables, int n){
        programModel = new ProgramModel(context);

        if(reqVariables != null){
            programModel.programSliceOptimization(reqVariables);
            if(n > 0){
                programModel.variableAbstractOptimization(n);
            }
        }
        programModel.generateModel();
        return programModel;
    }
}
