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

const timetable = require('./routes/timetable');
const inscriptions = require('./routes/inscriptions');
const speakers = require('./routes/speakers');
const notifications = require('./routes/notifications');

app.use(baseAPI, timetable);
app.use(baseAPI, inscriptions);
app.use(baseAPI, speakers);
app.use(baseAPI, notifications);

const server = http.createServer(app);

server.listen(PORT, function() {
    console.log('Server up and running on localhost:' + PORT);
});