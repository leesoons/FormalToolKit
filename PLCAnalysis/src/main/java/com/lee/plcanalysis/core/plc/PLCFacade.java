package com.lee.plcanalysis.core.plc;

import com.lee.plcanalysis.core.plc.model.IL2Model;
import com.lee.plcanalysis.core.plc.model.ProgramModel;
import com.lee.plcanalysis.core.plc.parser.CodeContext;
import com.lee.plcanalysis.core.plc.parser.ILContextBuilder;
import com.lee.plcanalysis.core.plc.parser.ILCodeParse;

import java.io.IOException;
import java.util.Set;

public class PLCFacade {
    private ILCodeParse parser;
    private ILContextBuilder builder;
    private CodeContext context;

    public PLCFacade() {
        this.parser = new ILCodeParse();
        this.builder = new ILContextBuilder();
    }

    public ILCodeParse getParser() {
        return parser;
    }
    
    public ILContextBuilder getBuilder() {
        return builder;
    }

    public void parseString(String ilCode) throws RuntimeException{
        parser.parse(ilCode);
    }

    public void parseFile(String filePath) throws IOException {
        parser.parseFile(filePath);
    }

    public CodeContext getCodeContext(){
        if(context == null){
            this.context = builder.getContext(parser.getLadders());
        }
        return context;
    }

    /**
     * @param reqVariables 规约所包含的变量列表（为空则表明是用户选择了测试生成）
     * @param n 优化等级，若由于程序模型过于复杂导致验证难度增加，则采用更强力的优化算法（n = 0: 切片; n > 0: 变量约简）
     * @return 程序的uppaal模型形式（部分）
     */
    public ProgramModel getProgramModel(Set<String> reqVariables, int n){
        return new IL2Model().translate(getCodeContext(), reqVariables, n);
    }
}
