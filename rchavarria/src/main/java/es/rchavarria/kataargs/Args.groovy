package es.rchavarria.kataargs

class Args {
    private Map schema
    private List argList
    private String currentArg
    private int nextArgIndex
    
    public Args(def schema, def argList){
        this.schema = new SchemaParser().toMap(schema)
        this.argList = argList
        nextArgIndex = 0
    }
    
    def nextFlag(){
        currentArg = argList[nextArgIndex]
        nextArgIndex++
        
        extractFlag(currentArg)
    }
    
    private extractFlag(def argument){
        argument.substring(1)
    }

    def getValueOfFlag(def flag){
        def parser = schema[flag]
        
        if(parser != null){
            return parser.parse(flag, argList) 
        } else {
            return false
        }
    }
}
