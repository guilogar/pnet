// movies.js
'use strict';

const express = require('express');
const router = express.Router();

/// DB
let movies = [
    {
        "title": "Jurassic Park",
        "director": "Steven Spielberg",
        "year": 1993
    },
    {
        "title": "The Lion King",
        "director": ["Rob Minkoff", "Roger Allers"],
        "year": 1994
    }
];


//http://localhost:8080/movies

router.get('/', function (req, res) {
	res.send(movies);
});

router.post('/', function (req, res) {
    movies.push(req.body);
    res.status(201).send("Film created!");
});

router.delete('/', function (req, res) {
    movies = [];
    res.status(200).send("Films deleted!");
});

//http://localhost:8080/movies/Titulo

router.get('/:title', function (req, res) {
    let title = req.params.title;
    res.send(movies.filter(m => m.title === title));
});

router.put('/:title', function (req, res) {
    let title = req.params.title;
    movies = movies.filter(m => m.title !== title);
    movies.push(req.body);
    res.status(200).send("Film updated!");
});

router.delete('/:title', function (req, res) {
    let title = req.params.title;
    movies = movies.filter(m => m.title !== title);
    res.status(200).send("Film deleted!");
});

module.exports = router;