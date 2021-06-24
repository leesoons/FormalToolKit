package com.lee.plcanalysis.core.sps.model;


import com.lee.plcanalysis.core.sps.base.Requirement;
import com.lee.plcanalysis.core.sps.parser.RequirementBuilder;
import com.lee.plcanalysis.core.sps.parser.RequirementGrammarLexer;
import com.lee.plcanalysis.core.sps.parser.RequirementGrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 观察者对象存储在json文件中，解析json文件以获取所有的观察者pattern
 */
public class ObserverPattern {
    public static final String PATTERN_FILE = "/sps_to_obs.json";

    private final Observer observer;

    private final Requirement requirement;

    public ObserverPattern(Observer observer, Requirement requirement) throws RuntimeException {
        this.observer = observer;
        this.requirement = requirement;
    }

    public Observer getObserver() {
        return observer;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public static Map<String,ObserverPattern> loadPattern(String configuration){
        Map<String,ObserverPattern> patterns = new HashMap<>();

        ParseTreeWalker walker = new ParseTreeWalker();

        InputStream is = ObserverPattern.class.getResourceAsStream(configuration);
        if(is == null) {
            throw new RuntimeException("Resource not find!");
        }

        String json;

        try{
            json = IOUtils.toString(is);
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String reqText = obj.getString("scope");

            RequirementBuilder requirementBuilder = new RequirementBuilder();
            RequirementGrammarLexer lexer = new RequirementGrammarLexer(CharStreams.fromString(reqText));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            RequirementGrammarParser parser = new RequirementGrammarParser(tokens);

            walker.walk(requirementBuilder, parser.list());
            List<Requirement> requirementList = requirementBuilder.getContext().getReq();
            Requirement requirement = requirementList.get(0);

            ObserverPattern observerPattern = new ObserverPattern(Observer.generateObserver(obj.getJSONObject("obs")), requirement);
            patterns.put(requirement.key(), observerPattern);
        }

        return patterns;
    }

    @Override
    public String toString() {
        return "ObserverPattern{" +
                "observer=" + observer +
                ", requirement=" + requirement +
                '}';
    }
}
