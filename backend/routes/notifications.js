'use strict';

const express = require('express');
const router = express.Router();
const { Database } = require('./database-service');
const databaseServiceNotifications = new Database();

databaseServiceNotifications.connectDb(
    function(err)
    {
        if (err)
        {
            console.log('Could not connect with MongoDB – databaseServiceNotifications', err);
            process.exit(1);
        }
    }, 'notifications'
);

//http://localhost:8080/notifications

router.get('/notifications', function (req, res) {
	databaseServiceNotifications.getAll((err, object) => {
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

router.post('/notifications', function (req, res) {
    let object = req.body;
    databaseServiceNotifications.add(object, (err, object) => {
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

router.delete('/notifications', function (req, res) {
    databaseServiceNotifications.removeAll((err) => {
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

router.get('/notifications/:_id', function (req, res) {
    let _id = req.params._id;
    databaseServiceNotifications.get(_id, (err, object) => {
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

router.put('/notifications/:_id', function (req, res) {
    const _id = req.params._id;
    const updatedobject = req.body;
    databaseServiceNotifications.update(_id, updatedobject, (err, numUpdates) => {
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

router.delete('/notifications/:_id', function (req, res) {
    let _id = req.params._id;
    databaseServiceNotifications.remove(_id, (err) => {
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