'use strict';

const express = require('express');
const router = express.Router();
const databaseService = require('./database-service');

databaseService.connectDb(
    function(err)
    {
        if (err)
        {
            console.log('Could not connect with MongoDB – databaseService', err);
            process.exit(1);
        }
    }, 'speakers'
);

//http://localhost:8080/speakers

router.get('/speakers', function (req, res) {
	databaseService.getAll((err, object) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (object === null){
            	res.status(500).send({
                    msg: "object null"
                });
            } else {
                res.status(200).send(object);
            }
        }
    );
});

router.post('/speakers', function (req, res) {
    let object = req.body;
    databaseService.add(object, (err, object) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (movie !== null) {
                res.status(201).send({
                    msg: 'object created!'
                });
            }
        }
    );
});

router.delete('/speakers', function (req, res) {
    databaseService.removeAll((err) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'object deleted!'
            });
        }
    });
});

router.get('/speakers/:_id', function (req, res) {
    let _id = req.params._id;
    databaseService.get(_id, (err, object) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
        } else if (object === null){
            res.status(500).send({
                msg: "object null"
            });
        } else {
            res.status(200).send(object);
        }
    });
});

router.put('/speakers/:_id', function (req, res) {
    const _id = req.params._id;
    const updatedobject = req.body;
    databaseService.update(_id, updatedobject, (err, numUpdates) => {
        if (err || numUpdates === 0) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'object updated!'
            });
        }
    });
});

router.delete('/speakers/:_id', function (req, res) {
    let _id = req.params._id;
    databaseService.remove(_id, (err) => {
        if (err) {
            res.status(404).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'object deleted!'
            });
        }
    });
});

module.exports = router;