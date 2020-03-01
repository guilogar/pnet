# pnet
Repositorio para la asignatura Programación en Internet

# Automatic validation on branch master with GitHub actions
Wee use https://github.com/marketplace/actions/html5-validator for make 
automatic validation with commit on master

#### action.yml
```
- name: Test Files
      uses: Cyb3r-Jak3/html5validator-action@v0.4
      with:
        root: src/
- uses: actions/upload-artifact@v1
      with:
        name: log
        path: log.log
```

# For check with w3c validator NodeJS
Install NodeJS (https://nodejs.org/es/download/)

```
npm i -g node-w3c-validator

node-w3c-validator -i ./src/*.html -f html -o ./reports/result.html -s
```

For more information, visit https://www.npmjs.com/package/node-w3c-validator

# For check with w3c validator PHP
Install PHP (https://www.php.net/downloads)

Install Composer (https://getcomposer.org/download/)

```
composer require 'eldadfux/w3c-validator:1.0.0'
```

Create a script with next code:

```
include_once '../src/W3CValidator.php';

// Create a new validator object
$validate = new W3CValidator('http://www.walla.co.il');

var_dump($validate->isValid()); /* output: bool(false) */
var_dump($validate->getErrorsCount()); /* output: int(463) */
var_dump($validate->getWarningsCount()); /* output: int(372) */
```

Execute the script with the command:

```
php script.php
```

For more information, see https://github.com/eldadfux/w3c-validator