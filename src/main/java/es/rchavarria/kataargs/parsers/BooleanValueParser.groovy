package es.rchavarria.kataargs.parsers

class BooleanValueParser implements FlagValueParser {

    @Override
    def parse(def flag, def argList) {
        return true;
    }
}
