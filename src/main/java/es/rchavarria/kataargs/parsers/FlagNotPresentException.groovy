package es.rchavarria.kataargs.parsers

class FlagNotPresentException extends RuntimeException {

    public FlagNotPresentException(String msg){
        super(msg)
    }
}
