package org.kata.arguments;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.kata.arguments.exception.FlagExpectedException;

public class Parser {

	/**
	 * Método que transforma una cadena de argumentos en una lista
	 * @param argumentsString
	 * @return
	 * @throws FlagExpectedException
	 */
	public static List<Argument> getArguments(String argumentsString) throws FlagExpectedException {
		String flag = "";
		List<String> values = new ArrayList<String>();
		List<Argument> arguments = new ArrayList<Argument>();
		 
		StringTokenizer st = new StringTokenizer(argumentsString.trim(), " ");
		
		while(st.hasMoreTokens()){
			flag = getFlag(st.nextToken());
			values = getValues(st);
			
			// Comprobamos si hay dos flags seguidos, para no crear argumentos
			// que tengan como valor un flag
			while(values.size() == 1 && values.get(0).startsWith("-") && !esNumero(values.get(0))){
				addArgument(flag, arguments);
				
				flag = getFlag(values.get(0));
				values = getValues(st);
			}
			
			addArgument(flag, values, arguments);
		}

		return arguments;
	}

	/**
	 * Método que recupera el flag del argumento. Se lanza una excepción si 
	 * el flag no empieza por '-' o es un número
	 * @param flag
	 * @return
	 * @throws FlagExpectedException
	 */
	private static String getFlag(String flag) throws FlagExpectedException {
		if(!flag.startsWith("-") || esNumero(flag)){
			throw new FlagExpectedException();
		}
		
		return flag.substring(1);
	}

	/**
	 * Método que recupera la lista de valores asociados al argumento
	 * @param st
	 * @return
	 */
	private static List<String> getValues(StringTokenizer st) {
		String value = "";
		List<String> values = new ArrayList<String>();
		
		if(st.hasMoreTokens()){
			value = st.nextToken();
			
			// Los argumentos con más de un valor van entre corchetes
			if(value.startsWith("[") && value.endsWith("]")){
				StringTokenizer st2 = new StringTokenizer(value.substring(1, value.length() - 1), ",");
				
				while(st2.hasMoreTokens()){
					values.add(st2.nextToken());
				}
			}
			else{
				values.add(value);
			}
		}
		else{
			// Si no hay tokens es por que el valor asociado al flag
			// es de tipo booleano
			values.add(Boolean.TRUE.toString());
		}
		
		return values;
	}

	/**
	 * Método que añade un argumento con su lista de valores a la lista de argumentos
	 * @param flag
	 * @param values
	 * @param arguments
	 */
	private static void addArgument(String flag, List<String> values, List<Argument> arguments) {
		Argument argument = new Argument();
		
		argument.setFlag(flag);
		argument.setValues(values);
		
		arguments.add(argument);
	}
	
	/**
	 * Método que añade un argumento con valor 'true' a la lista de argumentos
	 * @param flag
	 * @param arguments
	 */
	private static void addArgument(String flag, List<Argument> arguments) {
		Argument argument = new Argument();
		
		argument.setFlag(flag);
		argument.addValue(Boolean.TRUE.toString());
		
		arguments.add(argument);
	}

	/**
	 * Método que nos dice si una cadena representa un número
	 * @param str
	 * @return
	 */
	private static boolean esNumero(String str) {
		NumberFormat nf = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		nf.parse(str, pos);
		return str.length() == pos.getIndex();
	}
}
