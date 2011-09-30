package org.kata.arguments;

import java.util.List;

import org.kata.arguments.exception.FlagExpectedException;

public class Main {

	/**
	 * Método de prueba
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 1;
		List<Argument> arguments;
		StringBuilder argumentsString = new StringBuilder();
		
		for(String s : args){
			argumentsString.append(s);
			argumentsString.append(" ");
		}
		argumentsString.setLength(argumentsString.length() - 1);
		
		try {
			arguments = Parser.getArguments(argumentsString.toString());
			
			for(Argument a : arguments){
				System.out.println("Argumento " + i++);
				System.out.println("===========");
				System.out.println("Flag: " + a.getFlag());
				System.out.println("Valor: " + a.getValues() + "\n");
			}
		} 
		catch (FlagExpectedException e) {
			e.printStackTrace();
		}
	}
}
