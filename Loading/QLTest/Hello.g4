/**
 * Define a grammar called Hello
 */

grammar Hello;

//@parser::header
//{
//	package org.uva.sea.ql.parser.antlr;
//}
//
//@lexer::header
//{
//	package org.uva.sea.ql.parser.antlr;
//}



unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($x.result); }
    |  '-' x=unExpr { $result = new Neg($x.result); }
    |  '!' x=unExpr { $result = new Not($x.result); }
    |  x=primary    { $result = $x.result; }
    ;
    
mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr 
    { 
      if ($op.text.equals("*")) {
        $result = new Mul($result, rhs);
      }
      if ($op.text.equals("<=")) {
        $result = new Div($result, rhs);      
      }
    })*
    ;
    
  
addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result = new Add($result, rhs);
      }
      if ($op.text.equals("-")) {
        $result = new Sub($result, rhs);      
      }
    })*
    ;
  
relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr 
    { 
      if ($op.text.equals("<")) {
        $result = new LT($result, rhs);
      }
      if ($op.text.equals("<=")) {
        $result = new LEq($result, rhs);      
      }
      if ($op.text.equals(">")) {
        $result = new GT($result, rhs);
      }
      if ($op.text.equals(">=")) {
        $result = new GEq($result, rhs);      
      }
      if ($op.text.equals("==")) {
        $result = new Eq($result, rhs);
      }
      if ($op.text.equals("!=")) {
        $result = new NEq($result, rhs);
      }
    })*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And($result, rhs); } )*
    ;
    

orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new Or($result, rhs); } )*
    ;

root: 'form' ID block;

block: '{' question* '}';

question : ( ID':' STRING type computed_question* );

type: ( 'boolean' | 'date' | 'decimal' | 'integer' | 'money' | 'string' ) ;

computed_question: '(' addExpr | mulExpr ')' ;

statement
 : 'if' relExpr block ('else if' relExpr block)* ('else' block)? ;

primary returns [Expr result]: 
         // ID { $result = IntLiteral(ID.text, ID.getLine()) } | 
		 INT{ $result = IntLiteral(INT.text, INT.getLine())}
		 ;

// TODO look up conventions tokens/names capital letters
ID:  ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

INT: ('0'..'9')+;

STRING: ('"' .*? '"');

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

// http://stackoverflow.com/questions/14778570/antlr-4-channel-hidden-and-options
COMMENT 
    :   ( '//' ~[\r\n]* '\r'? '\n'
        | '/*' .*? '*/'
        ) -> skip
    ;
