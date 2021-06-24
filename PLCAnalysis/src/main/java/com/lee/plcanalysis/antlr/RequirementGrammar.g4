grammar RequirementGrammar;

@header {
    package com.lee.plcanalysis.requirement.sps.parser;
}

list: requirement+;

requirement: reqID? scope ',' property delay? '.';
reqID: '[' number ']';
scope: 'Globally' | 'After' expr | 'After' expr 'until' expr | 'When' expr;
delay: delayWithEnd1 | delayWithEnd2 | delayWithoutEnd | delayOnBothSides | delayOnRightSide;
property: universality | absence | existence;

delayWithEnd1: 'between' time '(' error ')' 'timeUnits' 'and' time '(' error ')' 'timeUnits';
delayWithEnd2: 'within' time '(' error ')' 'timeUnits';
delayWithoutEnd: 'after' time '(' error ')' 'timeUnits';
delayOnBothSides: 'after' time '(' error ')' 'timeUnits' 'and' 'the' 'property' 'still' 'holds' 'for' time '(' error ')' 'timeUnits' 'after' 'the' 'end' 'of' 'the' 'scope';
delayOnRightSide: 'and' 'the' 'property' 'still' 'holds' 'for' time '(' error ')' 'timeUnits' 'after' 'the' 'end' 'of' 'the' 'scope';

universality: 'it' 'is' 'always' 'the' 'case' 'that' ID 'holds';
absence: 'it' 'is' 'never' 'the' 'case' 'that' ID 'holds';
existence: ID 'exists' 'immediately';

expr
    : '(' expr ')'
    | expr ('and' | 'or') expr
    | 'not' ID
    | ID
    ;

time: number;

error: number ',' number;

number: POSITIVE_INT | FLOAT;
positiveInt: POSITIVE_INT;

FLOAT: '-'? ('.' DIGIT+ | DIGIT+ ('.' DIGIT*)? );
POSITIVE_INT: DIGIT | NONZERODIGIT (DIGIT)*;
ID: (LETTER | CHINESE) | (LETTER | DIGIT | CHINESE) (LETTER | DIGIT | '.' | CHINESE)* (LETTER | DIGIT | CHINESE);

WS: [ \t\r\n]+ -> skip ;
LINE_COMMENT : '#' .*? '\r'? '\n' -> skip; // Match "#" stuff '\n'

fragment NONZERODIGIT: [1-9];
fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z];
fragment CHINESE: [\u4E00-\u9FA5\uff08-\uff09];