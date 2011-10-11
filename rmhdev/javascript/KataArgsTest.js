/**
 * KataArgs (12Meses12Katas: Sep 2011)
 *
 * @author  rmhdev
 * @license MIT License
 */

function KataArgsTest() {}
registerTestSuite(KataArgsTest);

KataArgsTest.prototype.getValueForSimpleSchemaReturnsDefaultValue = function() {
    var schema = new Schema();
    schema.addInteger("p", 123);

    var parser = new Parser(schema);
    expectThat(parser.getValue("p"), equals(123));

    parser.setArgs("-p");
    expectThat(parser.getValue("p"), equals(123));
};

KataArgsTest.prototype.getValueForSimpleSchemaReturnsValue = function() {
    var schema = new Schema();
    schema.addInteger("p", 123);

    var parser = new Parser(schema);
    parser.setArgs("-p 456");
    expectThat(parser.getValue("p"), equals(456));
}

KataArgsTest.prototype.getValueForDoubleSchemaReturnsDefaultValue = function() {
    var schema = new Schema();
    schema.addInteger("p", 123);
    schema.addInteger("n", 789);

    var parser = new Parser(schema);
    parser.setArgs("-p -n");
    expectThat(parser.getValue("p"), equals(123));
    expectThat(parser.getValue("n"), equals(789));
}

KataArgsTest.prototype.getValueForDoubleSchemaReturnsValue = function() {
    var schema = new Schema();
    schema.addInteger("p", 123);
    schema.addInteger("n", 789);

    var parser = new Parser(schema);
    parser.setArgs("-p 456 -n 1000");
    expectThat(parser.getValue("p"), equals(456));
    expectThat(parser.getValue("n"), equals(1000));
}

KataArgsTest.prototype.getValueForDifferentTypesReturnsCorrectValue = function() {
    var schema = new Schema();
    schema.addInteger("p", 123);
    schema.addBoolean("b", false);
    schema.addString("s", "Hello");

    var parser = new Parser(schema);
    parser.setArgs("-p 456 -b true -s world");
    expectThat(parser.getValue("p"), equals(456));
    expectThat(parser.getValue("b"), equals(true));
    expectThat(parser.getValue("s"), equals("world"));
}

KataArgsTest.prototype.getValueForNegativeNumberReturnsNegativeNumber = function () {
    var schema = new Schema();
    schema.addInteger("p", 123);
    schema.addBoolean("b", false);

    var parser = new Parser(schema);
    parser.setArgs("-p -2 -b true");
    expectThat(parser.getValue("p"), equals(-2));
}

KataArgsTest.prototype.getValuesForUnorderedTypesReturnsCorrectValues = function() {
    var schema = new Schema();
    schema.addInteger("p", 123);
    schema.addBoolean("b", false);
    schema.addString("s", "Hello");

    var parser = new Parser(schema);
    parser.setArgs("-s world -b true -p 456");
    expectThat(parser.getValue("p"), equals(456));
    expectThat(parser.getValue("b"), equals(true));
    expectThat(parser.getValue("s"), equals("world"));
}

KataArgsTest.prototype.getValuesForListTypeReturnsDefaultList = function() {
    var schema = new Schema();
    schema.addList("g", "a,b,c,d,e");

    var parser = new Parser(schema);
    parser.setArgs("-g");
    expectThat(parser.getValue("g"), elementsAre(['a', 'b', 'c', 'd', 'e']));
}

KataArgsTest.prototype.getValuesForUnexpectedArgReturnsError = function() {
    var myFunction = function() {
        var schema = new Schema();
        schema.addInteger("p", 123);

        var parser = new Parser(schema);
        parser.setArgs("-v testing");
    }
    expectThat(myFunction, throwsError(/Error*/));
}
