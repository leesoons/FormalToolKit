package com.lee.plcanalysis.core.plc.parser;

import com.lee.plcanalysis.core.plc.base.Ladder;
import com.lee.plcanalysis.core.plc.base.OpCode;
import com.lee.plcanalysis.core.sps.model.ObserverPattern;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 解析代码文件，将代码转换成Ladder列表
 */
public class ILCodeParse {
    private final List<Ladder> ladders;

    public ILCodeParse() {
        this.ladders = new ArrayList<>();
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    /**
     * @param iLCode 解析字符串形式的IL代码，转换成Ladder列表
     */
    public void parse(String iLCode){
        String[] ils = iLCode.split("\n");

        for (String il : ils) {
            if (!il.trim().isEmpty() && il.charAt(0) != '#') {
                String[] statements = il.replace(".", "_").split("[\\f\\r\\t\\n|,]");
                OpCode o = getOpCode(statements[0]);
                if (o == OpCode.UNDEF) {
                    throw new RuntimeException("代码解析异常，请检查代码是否正确！");
                }
                if (statements.length > 1) {
                    List<String> operators = new ArrayList<>(Arrays.asList(statements).subList(1, statements.length));
                    ladders.add(new Ladder(o, operators));

                } else {
                    ladders.add(new Ladder(o, null));
                }
            }
        }
    }

    /**
     * @param filePath 按文件路径解析IL代码
     */
    public void parseFile(String filePath) throws IOException{
        String target;

        //将文件转化成字符串
        InputStream is = ObserverPattern.class.getResourceAsStream(filePath);
        InputStreamReader reader = null;
        if(is == null) {
            throw new IOException("Resource not find!");
        }
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(is);
            char[] buffer = new char[1024];
            int n;
            while((n = reader.read(buffer)) != -1){
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        target = writer.toString();

        parse(target);
    }


    public OpCode getOpCode(String str){
        OpCode opCode = OpCode.UNDEF;
        switch (str){
            case "LD":
                opCode = OpCode.LD;
                break;
            case "LDN":
                opCode = OpCode.LDN;
                break;
            case "A":
                opCode = OpCode.A;
                break;
            case "AN":
                opCode = OpCode.AN;
                break;
            case "O":
                opCode = OpCode.O;
                break;
            case "ON":
                opCode = OpCode.ON;
                break;
            case "NOT":
                opCode = OpCode.NOT;
                break;
            case "ALD":
                opCode = OpCode.ALD;
                break;
            case "OLD":
                opCode = OpCode.OLD;
                break;
            case "TON":
                opCode = OpCode.TON;
                break;
            case "S":
                opCode = OpCode.S;
                break;
            case "R":
                opCode = OpCode.R;
                break;
            case "=":
                opCode = OpCode.Equal;
                break;
            default:
                break;
        }
        return opCode;
    }
}
