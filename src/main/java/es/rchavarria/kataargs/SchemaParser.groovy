package es.rchavarria.kataargs

class SchemaParser {

    def toMap(def schema){
        def map = [:]
        map[schema[0]] = schema[1]
        map
}
}
