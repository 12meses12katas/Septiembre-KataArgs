package es.rchavarria.kataargs.parsers

class BooleanValueParser implements FlagValueParser {

    @Override
    def parse(def flag, def argList) {
        def expectedFlagText = "-" + flag
        
        argList.indexOf(expectedFlagText) >= 0
    }
}
