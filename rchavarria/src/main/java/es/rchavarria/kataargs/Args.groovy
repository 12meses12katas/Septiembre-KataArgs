package es.rchavarria.kataargs

import es.rchavarria.kataargs.exceptions.FlagNotDefinedException 
import es.rchavarria.kataargs.parsers.BooleanValueParser;


class Args {
    private Map schema
    private List argList
    private String currentArg
    private int nextArgIndex
    
    public Args(def schema, def argList){
        this.schema = new SchemaParser().toMap(schema)
        this.argList = argList
    }
    
    def nextFlag(){
        currentArg = argList[nextArgIndex]
        def flag = extractFlag(currentArg)
        nextArgIndex += computeNextIndexIncrement(flag)
        
        return flag
    }
    
    private extractFlag(def argument){
        argument.substring(1)
    }

    private computeNextIndexIncrement(def flag){
        def parser = schema[flag]
        def increment = 1
        if(! (parser instanceof BooleanValueParser))
            increment = 2

        return increment
    }

    def getValueOfFlag(def flag){
        def parser = schema[flag]
        
        if(parser == null)
            throw new FlagNotDefinedException("Flag ${flag} not defined in scheme")
        
        parser.parse(flag, argList) 
    }
}
