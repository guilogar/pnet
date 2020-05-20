'use strict';

const express = require('express');
const router = express.Router();

const { renewal } = require('../insertData');

router.get('/renewalData', async function (req, res) {
    await renewal();
    
    res.status(200).send({
        status: 200,
        msg: 'data renewal!'
    });
});

module.exports = router;