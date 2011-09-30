package org.kata.arguments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kata.arguments.exception.FlagExpectedException;

public class ArgumentTest {
	
	private Argument argumentL;
	
	private Argument argumentP;
	
	private Argument argumentPMultiple;
	
	private Argument argumentD;
	
	private Argument argumentTimeOut;
	
	private Argument argumentTimeOutNegativo;
	
	private Argument argumentS;
	
	private Argument argumentN;
	
	@Before
	public void setUp(){
		argumentL = new Argument();
		argumentL.setFlag("l");
		argumentL.addValue(Boolean.TRUE.toString());
		
		argumentP = new Argument();
		argumentP.setFlag("p");
		argumentP.addValue("8080");
		
		argumentPMultiple = new Argument();
		argumentPMultiple.setFlag("p");
		argumentPMultiple.addValue("8080");
		argumentPMultiple.addValue("8443");
		
		argumentD = new Argument();
		argumentD.setFlag("d");
		argumentD.addValue("/home/kata");
		
		argumentTimeOut = new Argument();
		argumentTimeOut.setFlag("tout");
		argumentTimeOut.addValue("10.5");
		
		argumentTimeOutNegativo = new Argument();
		argumentTimeOutNegativo.setFlag("tout");
		argumentTimeOutNegativo.addValue("-10.5");
		
		argumentS = new Argument();
		argumentS.setFlag("s");
		argumentS.addValue(Boolean.TRUE.toString());
		
		argumentN = new Argument();
		argumentN.setFlag("n");
		argumentN.addValue(Boolean.TRUE.toString());
	}
	
	@Test(expected = FlagExpectedException.class)
	public void noFlagTest() throws FlagExpectedException{
		Parser.getArguments("8080");
	}
	
	@Test(expected = FlagExpectedException.class)
	public void flagTwoValuesTest() throws FlagExpectedException{
		Parser.getArguments("-l -p 8080 8443");
	}
	
	@Test
	public void basicTest() throws FlagExpectedException{
		assertTrue(Parser.getArguments("").isEmpty());
		assertEquals(argumentL, Parser.getArguments("-l").get(0));
		assertEquals(argumentP, Parser.getArguments("-p 8080").get(0));
	}
	
	@Test
	public void onlyFlagsTest() throws FlagExpectedException{
		List<Argument> arguments = Parser.getArguments("-l -s -n");
		
		assertEquals(argumentL, arguments.get(0));
		assertEquals(argumentS, arguments.get(1));
		assertEquals(argumentN, arguments.get(2));
	}
	
	@Test
	public void booleanFlagFirstTest() throws FlagExpectedException{
		List<Argument> arguments = Parser.getArguments("-l -p 8080 -d /home/kata");
		
		assertEquals(argumentL, arguments.get(0));
		assertEquals(argumentP, arguments.get(1));
		assertEquals(argumentD, arguments.get(2));
	}
	
	@Test
	public void booleanFlagMiddleTest() throws FlagExpectedException{
		List<Argument> arguments = Parser.getArguments("-p 8080 -l -d /home/kata");
		
		assertEquals(argumentP, arguments.get(0));
		assertEquals(argumentL, arguments.get(1));
		assertEquals(argumentD, arguments.get(2));
	}
	
	@Test
	public void booleanFlagLastTest() throws FlagExpectedException{
		List<Argument> arguments = Parser.getArguments("-p 8080 -d /home/kata -l");
		
		assertEquals(argumentP, arguments.get(0));
		assertEquals(argumentD, arguments.get(1));
		assertEquals(argumentL, arguments.get(2));
	}
	
	@Test
	public void flagLargeNameTest() throws FlagExpectedException{
		List<Argument> arguments = Parser.getArguments("-p 8080 -tout 10.5 -d /home/kata -l");
		
		assertEquals(argumentP, arguments.get(0));
		assertEquals(argumentTimeOut, arguments.get(1));
		assertEquals(argumentD, arguments.get(2));
		assertEquals(argumentL, arguments.get(3));
	}
	
	@Test
	public void negativeNumberTest() throws FlagExpectedException{
		List<Argument> arguments = Parser.getArguments("-p 8080 -tout -10.5 -d /home/kata -l");
		
		assertEquals(argumentP, arguments.get(0));
		assertEquals(argumentTimeOutNegativo, arguments.get(1));
		assertEquals(argumentD, arguments.get(2));
		assertEquals(argumentL, arguments.get(3));
	}
	
	@Test
	public void multipleValuesTest() throws FlagExpectedException{
		List<Argument> arguments = Parser.getArguments("-p [8080,8443]");
		
		assertEquals(argumentPMultiple, arguments.get(0));
	}
	
	@Test
	public void complexTest() throws FlagExpectedException{
		List<Argument> arguments = Parser.getArguments("-p [8080,8443] -tout -10.5 -d /home/kata -l");
		
		assertEquals(argumentPMultiple, arguments.get(0));
		assertEquals(argumentTimeOutNegativo, arguments.get(1));
		assertEquals(argumentD, arguments.get(2));
		assertEquals(argumentL, arguments.get(3));
	}
}
