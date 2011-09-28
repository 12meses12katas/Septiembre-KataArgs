package es.rchavarria.kataargs.parsers

class FlagValueParserFactory {

    static def createParser(def flag){
        new BooleanValueParser()
    }
}
