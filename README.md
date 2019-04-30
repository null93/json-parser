# JSON Parser
> JSON parser and abstract syntax tree library for Java

![MIT License](https://img.shields.io/badge/License-MIT-lightgrey.svg?style=for-the-badge)
![Version 1.0.0](https://img.shields.io/badge/Version-1.0.0-lightgrey.svg?style=for-the-badge)
![Travis CI](https://img.shields.io/travis/null93/json-parser.svg?style=for-the-badge&colorB=9f9f9f)

## Documentation

For documentation and other helpful information, please visit the [wiki](/wiki) page.

## Build System

This project uses the *Ant* build system. These commands are defined in the _build.xml_ file.  Below is a table of commands and their descriptions.

| Command          | Description                                             |
| :--------------- | :------------------------------------------------------ |
| `ant init`       | Initializes project directories                         |
| `ant build-main` | Builds the project (_build/classes/main/java/_)         |
| `ant jar-main`   | Makes .jar file from project class files (_build/jar/_) |
| `ant build-test` | Builds the test files (_build/classes/test/java/_)      |
| `ant run-test`   | Runs and saves the JUnit tests (_results/_)             |

## Additional Notes

Please note that this package was developed with the JSON specification in mind.
Said specification can be found via [RFC-7159](https://tools.ietf.org/html/rfc7159).
