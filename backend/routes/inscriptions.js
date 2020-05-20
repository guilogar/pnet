'use strict';

const express = require('express');
const router = express.Router();
const { Database } = require('./database-service');
const databaseServiceInscriptions = new Database();

databaseServiceInscriptions.connectDb(
    function(err)
    {
        if (err)
        {
            console.log('Could not connect with MongoDB – databaseServiceInscriptions', err);
            process.exit(1);
        }
    }, 'inscriptions'
);

router.get('/inscriptions', function (req, res) {
	databaseServiceInscriptions.getAll((err, object) => {
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

router.post('/inscriptions', function (req, res) {
    let object = req.body;
    if(object.urlToRedirect !== undefined) delete object.urlToRedirect;
    
    databaseServiceInscriptions.add(object, (err, object) => {
            if (err)
            {
                res.status(500).send({
                    msg: err
                });
            } else if (object !== null)
            {
                res.status(201).send({
                    msg: 'object created!'
                });
            }
        }
    );
});

router.delete('/inscriptions', function (req, res) {
    databaseServiceInscriptions.removeAll((err) => {
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

router.get('/inscriptions/:_id', function (req, res) {
    let _id = req.params._id;
    databaseServiceInscriptions.get(_id, (err, object) => {
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

router.put('/inscriptions/:_id', function (req, res) {
    const _id = req.params._id;
    const updatedobject = req.body;
    databaseServiceInscriptions.update(_id, updatedobject, (err, numUpdates) => {
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

router.delete('/inscriptions/:_id', function (req, res) {
    let _id = req.params._id;
    databaseServiceInscriptions.remove(_id, (err) => {
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