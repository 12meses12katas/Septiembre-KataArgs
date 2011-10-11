/**
 * KataArgs (12Meses12Katas: Sep 2011)
 *
 * @author  rmhdev
 * @license MIT License
 */

function Schema () {
    this.items = {};
}

Schema.prototype.add = function (flag, valueType, defaultValue, getCorrectValueFunction) {
    this.items[flag] = {
        "type"              : valueType,
        "default"           : defaultValue,
        "getCorrectValue"   : getCorrectValueFunction
    }
}

Schema.prototype.addInteger = function (flag, defaultValue) {
    var getCorrectValueFunction = function (rawValue) {
        return parseInt(rawValue);
    }
    this.add(
        flag, "integer", getCorrectValueFunction(defaultValue), getCorrectValueFunction
    );
}

Schema.prototype.addBoolean = function (flag, defaultValue) {
    var getCorrectValueFunction = function (rawValue) {
        switch (rawValue){
            case "true" :return true;
            case "false":return false;
            default     :return Boolean(rawValue);
        }
    };
    this.add(
        flag, "boolean", getCorrectValueFunction(defaultValue), getCorrectValueFunction
    );
}

Schema.prototype.addString = function (flag, defaultValue) {
    var getCorrectValueFunction = function (rawValue) {
        return rawValue.toString();
    }
    this.add(
        flag, "string", getCorrectValueFunction(defaultValue), getCorrectValueFunction
    );
}

Schema.prototype.addList = function (flag, defaultValue) {
    var getCorrectValueFunction = function (rawValue) {
        return rawValue.split(",");
    }
    this.add(flag, "list", getCorrectValueFunction(defaultValue), getCorrectValueFunction);
}

Schema.prototype.getDefaultValue = function (flag) {
    if (this.isAcceptedFlag(flag)){
        return this.items[flag]["default"];
    }
    throw "error";
}

Schema.prototype.getCorrectValue = function (flag, rawValue) {
    if (this.isAcceptedFlag(flag)){
        var myFunction = this.items[flag]["getCorrectValue"];
        return myFunction(rawValue);
    }
    throw "error";
}

Schema.prototype.isAcceptedFlag = function (flag) {
    return (flag in this.items);
}

Schema.prototype.prepareValue = function (flag, rawValue){
    if (this.isAcceptedFlag(flag) && rawValue !== "") {
        return this.getCorrectValue(flag, rawValue);
    }
    return undefined;
}
