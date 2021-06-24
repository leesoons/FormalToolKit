package com.lee.plcanalysis.core.plc.parser;

import com.lee.plcanalysis.core.plc.base.CodeExpression;
import com.lee.plcanalysis.core.plc.base.Ladder;
import com.lee.plcanalysis.core.plc.base.OpCode;

import java.util.*;

/**
 * 将parse后的Ladder列表转换成Context对象
 */
public class ILContextBuilder {
    private double time = 0.0;
    private final List<List<Ladder>> timerLadderList;
    private final List<List<Ladder>> mainLadderList;

    private final CodeContext context = new CodeContext();

    public ILContextBuilder() {
        this.timerLadderList = new ArrayList<>();
        this.mainLadderList = new ArrayList<>();
    }

    public List<List<Ladder>> getTimerLadderList() {
        return timerLadderList;
    }

    public List<List<Ladder>> getMainLadderList() {
        return mainLadderList;
    }

    public CodeContext getContext(List<Ladder> ladderLists) {
        setLadderList(ladderLists);
        for (List<Ladder> ladders: mainLadderList){
            CodeExpression expr = transform(ladders);
            context.addExpr(expr, true);
        }
        for (List<Ladder> ladders: timerLadderList){
            CodeExpression expr = transform(ladders);
            context.addExpr(expr, false);
        }
        //设置循环周期时间
        context.setCycleTime(1);
        return context;
    }


    /**
     * @param ladders 将Ladder列表划分为MainLadder列表部分和TimerLadder列表部分
     */
    public void setLadderList(List<Ladder> ladders){
        List<Ladder> ladderList = new ArrayList<>();

        for (Ladder ladder : ladders) {
            ladderList.add(ladder);

            OpCode opCode = ladder.getOpCode();
            if (opCode.equals(OpCode.Equal) || opCode.equals(OpCode.S) || opCode.equals(OpCode.R)) {
                mainLadderList.add(new ArrayList<>(ladderList));
                ladderList.clear();
            } else if (opCode.equals(OpCode.TON)) {
                timerLadderList.add(new ArrayList<>(ladderList));
                ladderList.clear();
            }
        }
    }

    /**
     * @param ladders 将单个Ladder列表解析成一个表达式字符串，并叠加指令执行时间和扩充变量集。
     * @return 字符串形式的表达式
     */
    public CodeExpression transform(List<Ladder> ladders){
        String leftVar = null;
        StringBuilder expression = new StringBuilder();
        LinkedList<String> stack = new LinkedList<>();
        Set<String> rightVars = new HashSet<>();
        boolean leftPart = true;
        for(Ladder ladder: ladders){
            OpCode opCode = ladder.getOpCode();
            List<String> operator = ladder.getOperatorList();
            switch (opCode){
                case LD:
                    stack.push(operator.get(0));
                    break;
                case LDN:
                    stack.push("not " + operator.get(0));
                    break;
                case A:
                    stack.push("(" + operator.get(0) + " and " + stack.pop() + ")");
                    break;
                case AN:
                    stack.push("(not " + operator.get(0) + " and " + stack.pop() + ")");
                    break;
                case O:
                    stack.push("(" + operator.get(0) + " or " + stack.pop() + ")");
                    break;
                case ON:
                    stack.push("(not " + operator.get(0) + " or " + stack.pop() + ")");
                    break;
                case NOT:
                    stack.push("not " + stack.pop());
                    break;
                case ALD:
                    stack.push("(" + stack.pop() + " and " + stack.pop() + ")");
                    break;
                case OLD:
                    stack.push("(" + stack.pop() + " or " + stack.pop() + ")");
                    break;
                case S:
                    leftVar = operator.get(0);
                    leftPart = false;
                    expression.append(leftVar).append("=").append(stack.pop()).append(" or ").append(leftVar);
                    break;
                case R:
                    leftVar = operator.get(0);
                    leftPart = false;
                    expression.append(leftVar).append("=").append("not ").append(stack.pop()).append(" and ").append(leftVar);
                    break;
                case TON:
                    leftVar = operator.get(0);
                    leftPart = false;
                    expression.append(leftVar).append(",").append(stack.pop()).append(",").append(operator.get(1));
                    break;
                case Equal:
                    leftVar = operator.get(0);
                    leftPart = false;
                    expression.append(leftVar).append("=").append(stack.pop());
                    break;
            }
            if(operator != null){
                context.addVar(operator.get(0));
                if(leftPart) {
                    rightVars.add(operator.get(0));
                }
            }
        }
        return new CodeExpression(expression.toString(), leftVar, rightVars);
    }



}