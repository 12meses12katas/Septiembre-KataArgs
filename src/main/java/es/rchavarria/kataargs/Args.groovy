package es.rchavarria.kataargs

class Args {
    private schema
    private argList
    private currentArg
    
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
        currentArg = argList.remove(0)
        currentArg.substring(1)
}
    
    def getValueOfFlag(def flag){
        def flagSchema = schema[flag]
        flagSchema != null? currentArg.indexOf(flag) > 0 : false
}
}
