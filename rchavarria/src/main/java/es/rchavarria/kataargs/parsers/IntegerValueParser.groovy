package es.rchavarria.kataargs.parsers

class IntegerValueParser implements FlagValueParser {

    @Override
    def parse(def flag, def argList) {
        def expectedFlag = "-" + flag
        def idx = argList.indexOf(expectedFlag)
        if(idx < 0)
            throw new FlagNotPresentException("Flag ${flag} not present")
        
        try {
            def value = argList[idx + 1]
            return value.toInteger()
        
        }catch(Exception e){
            throw new FlagValueNotPresentException("Flag ${flag} has no value")
        }
    }
}
