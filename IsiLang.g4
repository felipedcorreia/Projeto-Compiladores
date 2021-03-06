grammar IsiLang;

@header{
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.CommandLeitura;
	import br.com.professorisidro.isilanguage.ast.CommandEscrita;
	import br.com.professorisidro.isilanguage.ast.CommandAtribuicao;
	import br.com.professorisidro.isilanguage.ast.CommandDecisao;
	import br.com.professorisidro.isilanguage.ast.CommandRepeticao;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;
	private String _exprRepetition;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> listaWhile;
	
	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateTarget();
	}
}

prog	: 'programa' decl bloco  'fimprog;'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());
           	 
           } 
		;
		
decl    :  (declaravar)+
        ;
        
        
declaravar :  tipo ID  {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    } 
              (  VIR 
              	 ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
              )* 
               SC
           ;
           
tipo       : 'numero' { _tipo = IsiVariable.NUMBER;  }
           | 'texto'  { _tipo = IsiVariable.TEXT;  }
           ;
        
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }
          (cmd)+
		;
		

cmd		:  cmdleitura
 		|  cmdescrita
 		|  cmdattrib
 		|  cmdselecao
 		|  cmdrepeticao
		;
		
cmdleitura	: 'leia' AP
                     ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                        } 
                     FP 
                     SC 
                     
              {
              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
              	var.setAttrib(1);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }   
			;
			
cmdescrita	: 'escreva' 
                 AP 
                 ID { verificaID(_input.LT(-1).getText());
	                  _writeID = _input.LT(-1).getText();
                    } 
                 FP 
                 SC
                 {
               	 	CommandEscrita cmd = new CommandEscrita(_writeID);
               	 	stack.peek().add(cmd);
                 }
			;
			
cmdattrib	:  ID {
                  verificaID(_input.LT(-1).getText());
                  _exprID = _input.LT(-1).getText();

                  // guarda o tipo da variàvel à esquerda da atribuição
                  {
                    IsiVariable var = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
                    var.setAttrib(1);
                    _tipo = var.getType();
                  }

                }
               ATTR { _exprContent = ""; } 
               expr 
               SC
               {
               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
               	 stack.peek().add(cmd);
               }
			;
			
			
cmdselecao  :  'se' AP
                    ID    {
                            _exprDecision = _input.LT(-1).getText();

                            // guarda o tipo da variàvel à esquerda do operador relacional
                            {
                              IsiVariable var = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
                              _tipo = var.getType();
                            }

                          }
                    OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID | NUMBER)
                          {
                            _exprDecision += _input.LT(-1).getText();

                            // verifica se tipos são diferentes
                            {
                              IsiVariable var = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
                              int proximoTipo = var.getType();
                              if(_tipo != proximoTipo)
                              {
                                if(_tipo == 0)
                                  throw new IsiSemanticException("Incompatible types: Bad operand types for binary operator ( " + _exprDecision + " -> first type: NUMERO | second type: TEXTO )");
                                else
                                  throw new IsiSemanticException("Incompatible types: Bad operand types for binary operator ( " + _exprDecision + " -> first type: TEXTO | second type: NUMERO )");
                              }
                            }

                          }
                    FP 
                    ACH 
                    { curThread = new ArrayList<AbstractCommand>(); 
                      stack.push(curThread);
                    }
                    
                    (cmd)+ 
                    
                    FCH 
                    {
                       listaTrue = stack.pop();	
                    } 
                    ('senao' 
                   	ACH
                   	{
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	} 
                   	(cmd+) 
                   	FCH
                   	{
                   		listaFalse = stack.pop();
                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                   		stack.peek().add(cmd);
                   	}
                   )?
            ;
            
cmdrepeticao  :  'enquanto'
				  AP
				  ID  { _exprRepetition = _input.LT(-1).getText(); }
				  OPREL  { _exprRepetition += _input.LT(-1).getText(); }
				  (ID | NUMBER)  { _exprRepetition += _input.LT(-1).getText(); }
				  FP
				  ACH
				  { curThread = new ArrayList<AbstractCommand>(); 
                    stack.push(curThread);
                  }
				  (cmd)+
				  FCH
				  {
				  	listaWhile = stack.pop();
				 	CommandRepeticao cmd = new CommandRepeticao(_exprRepetition, listaWhile);
				 	stack.peek().add(cmd);
				  }
              ;
			
expr		:  termo ( 
	             OP  { _exprContent += _input.LT(-1).getText();}
	            termo
	            )*
			;
			
termo		: ID {
                verificaID(_input.LT(-1).getText());
                _exprContent += _input.LT(-1).getText();

                // verifica se tipos são diferentes
                {
                   IsiVariable var = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
                   int proximoTipo = var.getType();
                   if(_tipo != proximoTipo)
                   {
                     if(_tipo == 0)
                       throw new IsiSemanticException("Incompatible types: TEXTO cannot be converted to NUMERO");
                     else
                       throw new IsiSemanticException("Incompatible types: NUMERO cannot be converted to TEXTO");
                   }
                 }

                 IsiVariable varAux = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
                 if(varAux.getAttrib() == 0)
                   throw new IsiSemanticException("Variable " + _input.LT(-1).getText() + " not initialized");

             }
            | 
              NUMBER
              {
              	_exprContent += _input.LT(-1).getText();
              }
			;
			
AP	: '('
	;
	
FP	: ')'
	;
	
SC	: ';'
	;
	
OP	: '+' | '-' | '*' | '/'
	;
	
ATTR : '='
	 ;
	 
VIR  : ','
     ;
     
ACH  : '{'
     ;
     
FCH  : '}'
     ;
	  
OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;
      
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;
		
WS	: (' ' | '\t' | '\n' | '\r') -> skip;