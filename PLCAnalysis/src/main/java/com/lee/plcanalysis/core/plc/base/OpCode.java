package com.lee.plcanalysis.core.plc.base;

public enum OpCode {
        LD, //装载指令
        LDN, //取反后装载
        A, //与指令
        AN, //取反后与
        O, //或
        ON, //取反后或
        NOT, //结果取反
        ALD, //与装载
        OLD, //或装载
        TON, //延时接通定时器
        S, //置位
        R, //复位
        Equal,
        UNDEF, //未定义
}
