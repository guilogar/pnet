const express = require('express');
const app = express();
const logger = require('morgan');
const http = require('http');
const path = require('path');
const PORT = process.env.PORT || 3000;
const bodyParser = require('body-parser');
const baseAPI = '/api/v1';
const cors = require('cors');

app.use(cors());
app.use(bodyParser.json());
app.use(logger('dev'));
app.use(bodyParser.urlencoded({
    extended: true
}));

//http://localhost:8080/
app.get('/', function (req, res) {
	res.send('Hello World!');
});

const movies = require('./routes/movies');

app.use('/movies', movies);

const server = http.createServer(app);

server.listen(PORT, function () {
	console.log('Server up and running on localhost:' + PORT);
});