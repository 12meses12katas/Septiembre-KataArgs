package es.rchavarria.kataargs

class SchemaParser {

    def toMap(def schema){
        def map = [:]
        def flagsList = schema.split(",")
        flagsList.each { map[it[0]] = it[1] }
        
        map
}
}
