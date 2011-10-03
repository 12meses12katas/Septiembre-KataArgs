package kataArgs;

import groovy.util.GroovyTestCase;

class ArgsTest extends GroovyTestCase {
	void testArgsSingleArg(){
		Args args = new Args (["l":"b","p":"i","d":"s"]	, "-l true")
		assert args.l == true
	}
	 void testLoggingFalse(){
		Args args = new Args (["l":"b","p":"i","d":"s"]	, "-l false")
		assert args.l == false
	}
	 void testLoggingFalseWithTwoParameters(){
		 Args args = new Args (["l":"b","p":"i","d":"s"]	, "-l false -p 8080")
		 assert args.l == false
		 assert args.p == 8080
	 }
	 void testWithThreeParameters(){
		 Args args = new Args (["l":"b","p":"i","d":"s"]	, "-l false -p 8080 -d miDirectorio")
		 assert args.l == false
		 assert args.p == 8080
		 assert args.d == 'miDirectorio'
	 }
	void testCallMethod(){
		String flag = "L"	
		Args args = new Args (["l":"b","p":"i","d":"s"]	, "-l true")
		String metodo = "setP"
		args."$metodo"(33)
		assert args.p == 33 
	}
	void testWithNoExistentParameter(){
		Args args = new Args (["w":"i"]	, "-w 25")		
		def xx = args.p
		assert xx == 25
	}

}
