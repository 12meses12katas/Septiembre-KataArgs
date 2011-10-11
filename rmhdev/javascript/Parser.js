/**
 * KataArgs (12Meses12Katas: Sep 2011)
 *
 * @author  rmhdev
 * @license MIT License
 */

function Parser (schema){
    this.schema = schema;
    this.args = {};
}

Parser.prototype.setArgs = function(args) {
    this.args = this.retrieveArgsFromString(args);
}

Parser.prototype.getValue = function(flag) {
    if (!this.schema.isAcceptedFlag(flag)){
        return undefined;
    }
    if (this.isValueDefinedInArgs(flag)){
        return this.args[flag];
    }
    
    return this.schema.getDefaultValue(flag);
}

Parser.prototype.isValueDefinedInArgs = function (flag){
    return (flag in this.args && this.args[flag] !== undefined);
}


Parser.prototype.retrieveArgsFromString = function (inputText) {
    var args = {};
    var unparsedText = inputText;

    while (unparsedText.length > 0){

        var rawValue = "";
        var flag = unparsedText.substring(1, 2);

        if (!this.schema.isAcceptedFlag(flag)){
            throw "Error: unexpected '" + flag + "' arg";
        }

        unparsedText = unparsedText.substring(3);
        var indexNextSpace = unparsedText.indexOf(" ");
        if (indexNextSpace < 0){
            if (unparsedText[0] == "-"){
                rawValue = isNaN(unparsedText) ? "" : unparsedText;
            } else {
                rawValue = unparsedText;
                unparsedText = "";
            }
        } else {
            rawValue = unparsedText.substring(0, indexNextSpace);
            unparsedText = unparsedText.substring(indexNextSpace + 1);
        }
        args[flag] = (rawValue != "") ? this.schema.prepareValue(flag, rawValue) : undefined;
    }

    return args;
}
