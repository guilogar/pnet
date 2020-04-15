'use strict';

const express = require('express');
const router = express.Router();
const { Database } = require('./database-service');
const databaseServiceTimetable = new Database();

databaseServiceTimetable.connectDb(
    function(err)
    {
        if (err)
        {
            console.log('Could not connect with MongoDB – databaseServiceTimetable', err);
            process.exit(1);
        }
    }, 'timetable'
);

//http://localhost:8080/timetable

router.get('/timetable', function (req, res) {
	databaseServiceTimetable.getAll((err, object) => {
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

router.post('/timetable', function (req, res) {
    let object = req.body;
    databaseServiceTimetable.add(object, (err, object) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (object !== null) {
                res.status(201).send({
                    msg: 'object created!'
                });
            }
        }
    );
});

router.delete('/timetable', function (req, res) {
    databaseServiceTimetable.removeAll((err) => {
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

router.get('/timetable/:_id', function (req, res) {
    let _id = req.params._id;
    databaseServiceTimetable.get(_id, (err, object) => {
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

router.put('/timetable/:_id', function (req, res) {
    const _id = req.params._id;
    const updatedobject = req.body;
    databaseServiceTimetable.update(_id, updatedobject, (err, numUpdates) => {
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

router.delete('/timetable/:_id', function (req, res) {
    let _id = req.params._id;
    databaseServiceTimetable.remove(_id, (err) => {
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