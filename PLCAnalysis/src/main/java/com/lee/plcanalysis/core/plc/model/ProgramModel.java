package com.lee.plcanalysis.core.plc.model;

import com.lee.plcanalysis.core.plc.base.CodeExpression;
import com.lee.plcanalysis.core.plc.parser.CodeContext;

import java.util.*;

/**
 * 解析IL表达式，转化成符合xta格式的预模型，并优化生成的模型(目前考虑的方式有切片（不影响验证结果）和变量消除（可能造成假阳性）)。
 */
public class ProgramModel {
    private final CodeContext codeContext;
    private final StringBuilder definitionPart;
    private final StringBuilder programMetaModel;
    private final List<String> instanceExpression;
    private final List<String> processName;

    private List<String> mainExpr;
    private List<String> timerExpr;

    private Map<String,Set<Integer>> variableDependencyTable;
    private Map<Integer,CodeExpression> exprId2Expression;

    public ProgramModel(CodeContext context) {
        this.codeContext = context;
        this.mainExpr = new ArrayList<>();
        codeContext.getILMainExpression().forEach(codeExpression -> mainExpr.add(codeExpression.getExpression()));
        this.timerExpr = new ArrayList<>();
        codeContext.getILTimeExpression().forEach(codeExpression -> timerExpr.add(codeExpression.getExpression()));
        this.definitionPart = new StringBuilder();
        this.programMetaModel = new StringBuilder();
        this.instanceExpression = new ArrayList<>();
        this.processName = new ArrayList<>();
    }

    public CodeContext getCodeContext() {
        return codeContext;
    }

    public StringBuilder getDefinitionPart() {
        return definitionPart;
    }

    public StringBuilder getProgramMetaModel() {
        return programMetaModel;
    }

    public List<String> getInstanceExpression() {
        return instanceExpression;
    }

    public List<String> getProcessName() {
        return processName;
    }

    public Map<Integer,CodeExpression> getExprId2Expression(){
        if(exprId2Expression == null){
            exprId2Expression = new HashMap<>();
            int id = 0;
            for(int i = 0; i < codeContext.getILMainExpression().size(); ++i){
                CodeExpression expr = codeContext.getILMainExpression().get(i);
                exprId2Expression.put(id++, expr);
            }
            for(int i = 0; i < codeContext.getILTimeExpression().size(); ++i){
                CodeExpression expr = codeContext.getILTimeExpression().get(i);
                exprId2Expression.put(id++, expr);
            }
        }
        return exprId2Expression;
    }

    /**
     * @return 返回变量依赖表（变量 -> 依赖的表达式ID列表）
     */
    public Map<String, Set<Integer>> getVariableDependencyTable(){
        if(variableDependencyTable == null){
            variableDependencyTable = new HashMap<>();

            //变量表
            Set<String> varSet = new HashSet<>();
            //表达式列表（ID -> exprVars）
            List<List<String>> varList = new ArrayList<>();
            //变量->表达式ID映射表
            Map<String,Set<Integer>> var2Expr = new HashMap<>();
            for(Map.Entry<Integer,CodeExpression> entry: getExprId2Expression().entrySet()){
                CodeExpression codeExpression = entry.getValue();
                String leftVar = codeExpression.getLeftPart();

                List<String> list = new ArrayList<>();
                list.add(leftVar);
                list.addAll(codeExpression.getRightPart());
                varList.add(list);

                Set<Integer> set = var2Expr.getOrDefault(leftVar, new HashSet<>());
                set.add(entry.getKey());
                var2Expr.put(leftVar, set);

                varSet.addAll(list);
            }

            //构建变量数值映射表，方便处理
            Map<String,Integer> var2Num = new HashMap<>();
            Map<Integer,String> num2Var = new HashMap<>();
            int index = 0;
            for(String str: varSet){
                var2Num.put(str,index);
                num2Var.put(index++, str);
            }

            //构建邻接矩阵
            List<Integer>[] edges = new List[index];
            for(int i = 0; i < index; ++i){
                edges[i] = new ArrayList<>();
            }
            for(List<String> vars: varList){
                int curNum = var2Num.get(vars.get(0));

                for(int i = 1; i < vars.size(); ++i){
                    int num = var2Num.get(vars.get(i));
                    if(num != curNum){
                        edges[curNum].add(num);
                    }
                }
            }

            //核心代码，通过dfs构建出变量与表达式之间的依赖关系结构
            for(String var: varSet){
                //目标元素
                int targetVarNum = var2Num.get(var);
                //结果列表
                Set<String> res = new HashSet<>();

                boolean[] visit = new boolean[index];
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(targetVarNum);
                res.add(var);
                while(!queue.isEmpty()){
                    int size = queue.size();
                    for(int i = 0; i < size; ++i){
                        int node = queue.poll();
                        for(int x: edges[node]){
                            if(!visit[x]){
                                queue.offer(x);
                                visit[x] = true;
                            }
                        }
                        res.add(num2Var.get(node));
                    }
                }

                //目标元素对应的依赖表达式ID列表
                variableDependencyTable.computeIfAbsent(var, value -> new HashSet<>());
                for(String str: res){
                    Set<Integer> dependSet = variableDependencyTable.get(var);
                    if(var2Expr.containsKey(str)){
                        dependSet.addAll(var2Expr.get(str));
                    }
                }
            }
        }
        //System.out.println(variableDependencyTable);

        return variableDependencyTable;
    }

    /**
     * 根据变量依赖表和需求中出现的变量对模型进行优化
     * @param reqVars 模型优化阶段一：程序切片
     */
    public void programSliceOptimization(Set<String> reqVars){
        //获取所有与需求变量表中变量相关的表达式ID序列
        Set<Integer> optimizationExprSet = new HashSet<>();
        for(String reqVar: reqVars){
            optimizationExprSet.addAll(getVariableDependencyTable().get(reqVar));
        }

        //更新用于模型生成的表达式列表
        List<String> newMainExpr = new ArrayList<>();
        List<String> newTimerExpr = new ArrayList<>();

        int index = 0;
        int n = mainExpr.size();
        for(; index < n; ++index){
            if(optimizationExprSet.contains(index)){
                newMainExpr.add(mainExpr.get(index));
            }
        }
        for(; index < timerExpr.size() + n; ++index){
            if(optimizationExprSet.contains(index)){
                newTimerExpr.add(timerExpr.get(index - n));
            }
        }
        this.mainExpr = newMainExpr;
        this.timerExpr = newTimerExpr;
    }

    /**
     * @param n 模型优化阶段二：变量抽象；n为变量抽象等级
     */
    public void variableAbstractOptimization(int n){
        /*
        * ..变量抽象，考虑图数据结构，需要计算变量之间的距离
        * */
    }

    void generateModel(){
        generateDefinitionPart(codeContext.getVariablesTab());
        generateProgramMetaModel();
    }

    /**
     * @param varList 根据变量表生成模型定义部分
     */
    private void generateDefinitionPart(Set<String> varList){
        definitionPart.append("bool ");
        for(String var: varList){
            definitionPart.append(var).append(", ");
        }
        definitionPart.deleteCharAt(definitionPart.length() - 2).append(";");
        definitionPart.append("broadcast chan VERIFY, TIMER, FORCE_TIMER;");
    }

    /**
     * 生成主程序元模型代码
     */
    private void generateProgramMetaModel(){
        List<String> inputVars = new ArrayList<>();
        int cycleTime = (int)Math.ceil(codeContext.getCycleTime());
        for(String var: codeContext.getVariablesTab()){
            if(var.charAt(0) == 'I'){
                inputVars.add(var);
            }
        }
        String varDef = "clock x;\n";
        String state = "state\n" +
                "    Init,\n" +
                "    end,\n" +
                "    syn1 { x <= t },\n" +
                "    syn2,\n";
        for(int i = 0; i < mainExpr.size() + 1; ++i){
            state += "    L" + i + ",\n";
        }
        state = state.substring(0, state.length() - 2) + ";\n";
        String urgent = "urgent\n" +
                "    Init,\n" +
                "    end,\n" +
                "    syn2,\n";
        for(int i = 0; i < mainExpr.size() + 1; ++i){
            urgent += "    L" + i + ",\n";
        }
        urgent = urgent.substring(0, urgent.length() - 2) + ";\n";
        String init = "init\n " + "    Init;\n";
        String trans =
                "trans\n" +
                "    syn2 -> Init { sync FORCE_TIMER!; },\n" +
                "    end -> syn1 { sync TIMER!; assign x=0; },\n" +
                "    syn1 -> syn2 { guard x == t; },\n" +
                "    L" + mainExpr.size() +" -> end { sync VERIFY!;},\n" +
                "    Init -> L0 { ";
        String select = "";
        String assign = "";
        for(String var: inputVars){
            select += var + "e:int[0,1]" + ",";
            assign += var + "="  + var + "e,";
        }
        trans += "select "
                + select.substring(0, select.length() - 1)
                + ";assign "
                + assign.substring(0, assign.length() - 1)
                + "; },\n";
        for(int i = 0; i < mainExpr.size(); ++i){
            trans += "    L" + i + " -> L" + (i + 1) + "{ assign " + mainExpr.get(i) + "; },\n";
        }
        trans = trans.substring(0, trans.length() - 2) + ";\n";
        programMetaModel.append("process Program(int t) {\n").append(varDef).append(state).append(urgent).append(init).append(trans).append("}\n");

        instanceExpression.add("program1 = Program(" + cycleTime + ")");
        processName.add("program1");

        String tonStr = "process TON(bool &ton, int t) {\n" +
                "clock x;\n" +
                "state\n" +
                "    NR,\n" +
                "    R,\n" +
                "    TO;\n" +
                "init\n" +
                "    NR;\n" +
                "trans\n" +
                "    R -> R { guard x<t; sync TIMER?; },\n" +
                "    TO -> TO { guard <IN>; sync TIMER?; },\n" +
                "    TO -> NR { guard not <IN>; sync TIMER?; assign ton=false; },\n" +
                "    R -> TO { guard x>=t and <IN>; sync FORCE_TIMER?; assign ton=true; },\n" +
                "    R -> NR { guard not <IN>; sync TIMER?; },\n" +
                "    NR -> R { guard <IN>; sync TIMER?; assign x=0; },\n" +
                "    NR -> NR { guard not <IN>; sync TIMER?; };\n" +
                "}\n";
        for (int i = 0; i < timerExpr.size(); ++i) {
            String[] ton = timerExpr.get(i).split(",");
            programMetaModel.append(tonStr.replaceAll("<IN>", ton[1]));
            instanceExpression.add("ton" + i + "=" + "TON("+ ton[0] + ", " + ton[2] + ")");
            processName.add("ton" + i);
        }
    }
}
