grammar Expression;
import Configuration;

root : sum EOF;

sum : product ('+' product)*;

product : primitive ('*' primitive)*;

primitive : NUMBER 
         | VARIABLE 
         | '(' sum ')'
         ;

NUMBER : [0-9]+ ('.' [0-9]+)?;
VARIABLE : [A-Za-z]+;
SPACES : [ \t\r\n]+ -> skip;