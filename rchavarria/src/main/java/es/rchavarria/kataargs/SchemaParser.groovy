package es.rchavarria.kataargs

import es.rchavarria.kataargs.parsers.FlagValueParser;
import es.rchavarria.kataargs.parsers.FlagValueParserFactory;

class SchemaParser {

    def toMap(def schema){
        def map = [:]
        def flagsList = schema.split(",")
        flagsList.each {
            def flag = it[0]
            def flagValueParserType = it[1]
            FlagValueParser parser = FlagValueParserFactory.createParser(flagValueParserType) 
            map[flag] = parser
        }
        
        map
    }
}
