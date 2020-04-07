'use strict';

const MongoClient = require('mongodb').MongoClient;
let db;
let ObjectId = require('mongodb').ObjectID;
const TimeTable = function () {};


TimeTable.prototype.connectDb = function (callback) {
    MongoClient.connect("mongodb://heroku_0dql66zr:heroku_0dql66zr3000@ds157834.mlab.com:57834/heroku_0dql66zr",
        { useNewUrlParser: true, useUnifiedTopology: true },
        function (err, database) {
            if (err)
            {
            	console.log(err);
                callback(err);
            }
            db = database.db('pnet-guillermo-elias').collection('timetable');
            callback(err, database);
        });
};

TimeTable.prototype.add = function (timetable, callback) {
    return db.insertOne(timetable, callback);
};

TimeTable.prototype.get = function (_id, callback) {
    return db.find({_id: ObjectId(_id)}).toArray(callback);
};

TimeTable.prototype.getAll = function (callback) {
    return db.find({}).toArray(callback);
};


TimeTable.prototype.update = function (_id, updatedTimeTable, callback) {
    delete updatedTimeTable._id;
    return db.updateOne({_id: ObjectId(_id)}, {$set: updatedTimeTable}, callback);};

TimeTable.prototype.remove = function (_id, callback) {
    return db.deleteOne({_id: ObjectId(_id)}, callback);
};

TimeTable.prototype.removeAll = function (callback) {
    return db.deleteMany({}, callback);
};

module.exports = new TimeTable();
