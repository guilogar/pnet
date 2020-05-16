# pnet
Repositorio para la asignatura Programación en Internet

# Previous execution
``` npm install ```

# Backend
NodeJS + MongoDB

## How to init the Backend
Execute in main directory ``node .``

## Hot to init the frontend
Init the front in a server with the port 5500. The extension of Visual Code called ``Live Server`` with extension id ``ritwickdey.liveserver`` can help to this adventure <3.

## Enjoy de web
Put in your navigator http://localhost:5500/src/index.html

# Automatic validation on branch master with GitHub actions
Wee use https://www.npmjs.com/package/html5-validator for make automatic validation of html5

#### .github/workflows/main.yml
```
name: Integration

on:
  push:
    branches:
      - master
      - test-github-actions
  schedule:
    - cron: '0 0 * * 0' # Weekly on Sundays at 02:00


jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@master
    - run: npm install
    - run: node . &> log.log
    - uses: pCYSl5EDgo/cat@master
      id: log
      with:
        path: log.log
      env:
        TEXT: ${{ steps.log.outputs.text }}
    - uses: actions/upload-artifact@v1
      with:
        name: log
        path: log.log
```

# For check with W3C validator with NodeJS
For check in local, execute the command:
```
node .

or 

node testhtml.js
```

Previously, execute:
```
npm install
```

# Access to global domain heroku
The backend is upload with 
`
https://pnet.herokuapp.com/api/v1/
`
url. To access to any endpoint, must complete the url with
`timetable`, `inscriptions`, `speakers` and `notifications`.

By example, to access `timetable` data, you must access to `https://pnet.herokuapp.com/api/v1/timetable`

# Coming soon
Automatic unit a integration test with mocha, postman and maybe, with selenium.