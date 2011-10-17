package es.rchavarria.kataargs.exceptions


class FlagValueNotPresentException extends RuntimeException {

    public FlagValueNotPresentException(String msg){
        super(msg)
    }
}
