package es.rchavarria.kataargs.parsers

class FlagValueNotPresentException extends RuntimeException {

    public FlagValueNotPresentException(String msg){
        super(msg)
    }
}
