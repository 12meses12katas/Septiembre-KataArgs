package es.rchavarria.kataargs.parsers

class FlagValueParserFactory {

    static def createParser(def flag){
        FlagValueParser parser = null
        
        if("B".equals(flag))
            parser = new BooleanValueParser()
        if("I".equals(flag))
            parser = new IntegerValueParser()
        if("S".equals(flag))
            parser = new StringValueParser()

        parser
    }
}
