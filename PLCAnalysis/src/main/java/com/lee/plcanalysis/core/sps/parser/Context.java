package com.lee.plcanalysis.core.sps.parser;

import com.lee.plcanalysis.core.sps.base.Requirement;

import java.util.ArrayList;

public class Context {
    private ArrayList<Requirement> requirementList= new ArrayList<Requirement>();

    public ArrayList<Requirement> getReq() {
        return requirementList;
    }

    public void addReq(Requirement spec) {
        this.requirementList.add(spec);
    }
}
