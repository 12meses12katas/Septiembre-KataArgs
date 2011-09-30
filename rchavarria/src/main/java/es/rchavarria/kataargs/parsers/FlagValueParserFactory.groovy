package es.rchavarria.kataargs.parsers

class FlagValueParserFactory {

    static def createParser(def flag){
        FlagValueParser parser = null
        
        if("b".equals(flag))
            parser = new BooleanValueParser()
        if("i".equals(flag))
            parser = new IntegerValueParser()
            
        parser
    }
}
