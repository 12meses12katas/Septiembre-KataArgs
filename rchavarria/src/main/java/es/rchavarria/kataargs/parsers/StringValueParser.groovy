package es.rchavarria.kataargs.parsers

import es.rchavarria.kataargs.exceptions.FlagValueNotPresentException 


class StringValueParser implements FlagValueParser {
    private static String DEFAULT_VALUE = ""
    
    @Override
    def parse(def flag, def argList) {
        def expectedFlag = "-" + flag
        def idx = argList.indexOf(expectedFlag)
        if(idx < 0)
            return DEFAULT_VALUE
        
        if(idx >= (argList.size() - 1))
            throw new FlagValueNotPresentException("Flag ${flag} has no value")
                
        argList[idx + 1]
    }
}
