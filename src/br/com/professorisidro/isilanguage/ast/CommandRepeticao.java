package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaWhile;
	
	//private ArrayList<AbstractCommand> listaTrue;
	//private ArrayList<AbstractCommand> listaFalse;
	
	public CommandRepeticao(String condition, ArrayList<AbstractCommand> lw) {
		this.condition = condition;
		this.listaWhile = lw;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+") {\n");
		for (AbstractCommand cmd: listaWhile) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}\n");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandRepeticao [condition=" + condition + ", listaWhile=" + listaWhile + "]";
	}

}
