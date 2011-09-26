package es.rchavarria.kataargs

class Args {
    private schema
    private argList
    
    public Args(def schema, def argList){
        this.schema = parseSchema(schema)
        this.argList = argList
}
    
    private parseSchema(def schema){
        def flagsMap = [:]
        def flagsList = schema.split(",")
        flagsList.each { flagsMap[it[0]] = it[1] }
        
        flagsMap
}

    def nextFlag(){
        argList[0].substring(1)
}
    
    def getValueOfFlag(def flag){
        schema[nextFlag()] != null 
}
}
