package es.rchavarria.kataargs.parsers

import es.rchavarria.kataargs.exceptions.FlagValueNotPresentException;

class IntegerValueParser implements FlagValueParser {
    private static Integer DEFAULT_VALUE = 0
    
    @Override
    def parse(def flag, def argList) {
        def expectedFlag = "-" + flag
        def idx = argList.indexOf(expectedFlag)
        if(idx < 0)
            return DEFAULT_VALUE
        
        try {
            def value = argList[idx + 1]
            return value.toInteger()
        
        }catch(Exception e){
            throw new FlagValueNotPresentException("Flag ${flag} has no value")
        }
    }
}
