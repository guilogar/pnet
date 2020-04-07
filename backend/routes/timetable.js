'use strict';

const express = require('express');
const router = express.Router();
const timetableService = require('./timetable-service');

//http://localhost:8080/timetable

router.get('/', function (req, res) {
	timetableService.getAll((err, timetable) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (timetable === null){
            	res.status(500).send({
                    msg: "timetable null"
                });
            } else {
                res.status(200).send(timetable);
            }
        }
    );
});

router.post('/', function (req, res) {
    let timetable = req.body;
    timetableService.add(timetable, (err, timetable) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (movie !== null) {
                res.status(201).send({
                    msg: 'timetable created!'
                });
            }
        }
    );
});

router.delete('/', function (req, res) {
    timetableService.removeAll((err) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'Films deleted!'
            });
        }
    });
});

router.get('/:_id', function (req, res) {
    let _id = req.params._id;
    timetableService.get(_id, (err, timetable) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
        } else if (timetable === null){
            res.status(500).send({
                msg: "timetable null"
            });
        } else {
            res.status(200).send(timetable);
        }
    });
});

router.put('/:_id', function (req, res) {
    const _id = req.params._id;
    const updatedTimetable = req.body;
    timetableService.update(_id, updatedTimetable, (err, numUpdates) => {
        if (err || numUpdates === 0) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'Timetable updated!'
            });
        }
    });
});

router.delete('/:_id', function (req, res) {
    let _id = req.params._id;
    timetableService.remove(_id, (err) => {
        if (err) {
            res.status(404).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'Timetable deleted!'
            });
        }
    });
});

module.exports = router;