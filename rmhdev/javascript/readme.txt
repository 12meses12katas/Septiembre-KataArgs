KataArgs:

Entidades del problema:

1. Parser
  - Encargado de recibir y tratar la cadena de argumentos.

2. Schema
  - Lista de definiciones de argumentos aceptados por "Parser".


Definición de las entidades:

1. Parser
  - Necesita un "Schema" para definir los argumentos aceptados.
  - Acepta cadenas de argumentos.
  - Si alguno de los argumentos no está definido en el "Schema", debe mostrar un mensaje de error
  - El "Parser" permite saber el valor de un argumento consultando su "flag"

2. Schema
  - Lista de definiciones de argumentos.
  - Un argumento constará de:
    - Nombre del flag
    - Valor por defecto


Desarrollo

Programado en Javascript y testeado con JS Unit testing "Google JS Code"

multivac:javascript rmhdev$ gjstest --js_files=Parser.js,Schema.js,KataArgsTest.js
[----------]
[ RUN      ] KataArgsTest.getValueForSimpleSchemaReturnsDefaultValue
[       OK ] KataArgsTest.getValueForSimpleSchemaReturnsDefaultValue (13 ms)
[ RUN      ] KataArgsTest.getValueForSimpleSchemaReturnsValue
[       OK ] KataArgsTest.getValueForSimpleSchemaReturnsValue (1 ms)
[ RUN      ] KataArgsTest.getValueForDoubleSchemaReturnsDefaultValue
[       OK ] KataArgsTest.getValueForDoubleSchemaReturnsDefaultValue (2 ms)
[ RUN      ] KataArgsTest.getValueForDoubleSchemaReturnsValue
[       OK ] KataArgsTest.getValueForDoubleSchemaReturnsValue (3 ms)
[ RUN      ] KataArgsTest.getValueForDifferentTypesReturnsCorrectValue
[       OK ] KataArgsTest.getValueForDifferentTypesReturnsCorrectValue (3 ms)
[ RUN      ] KataArgsTest.getValueForNegativeNumberReturnsNegativeNumber
[       OK ] KataArgsTest.getValueForNegativeNumberReturnsNegativeNumber (0 ms)
[ RUN      ] KataArgsTest.getValuesForUnorderedTypesReturnsCorrectValues
[       OK ] KataArgsTest.getValuesForUnorderedTypesReturnsCorrectValues (1 ms)
[ RUN      ] KataArgsTest.getValuesForListTypeReturnsDefaultList
[       OK ] KataArgsTest.getValuesForListTypeReturnsDefaultList (3 ms)
[ RUN      ] KataArgsTest.getValuesForUnexpertedArgReturnsError
[       OK ] KataArgsTest.getValuesForUnexpertedArgReturnsError (1 ms)
[----------]

[  PASSED  ]
multivac:javascript rmhdev$