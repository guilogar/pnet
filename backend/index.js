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

const moviesService = require('./routes/timetable-service');
const movies = require('./routes/timetable');

app.use('/timetable', movies);

const server = http.createServer(app);

moviesService.connectDb(
    function(err) {
        if (err) {
            console.log('Could not connect with MongoDB – moviesService', err);
            process.exit(1);
        }

        server.listen(PORT, function() {
            console.log('Server up and running on localhost:' + PORT);
        });
    });