package org.kata.arguments;

import java.util.ArrayList;
import java.util.List;

public class Argument {

	private String flag;
	
	private List<String> values;
	
	public Argument(){
		this.flag = "";
		this.values = new ArrayList<String>();
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public List<String> getValues() {
		return values;
	}
	
	public void setValues(List<String> values) {
		this.values = values;
	}
	
	public void addValue(String value){
		this.values.add(value);
	}

	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		
		if(obj == this){
			return true;
		}
		
		if(!(obj instanceof Argument)){
			return false;
		}
		
		Argument arg = (Argument) obj;
		
		return this.getFlag().equals(arg.getFlag()) &&
			   this.getValues().equals(arg.getValues());
	}
}
