'use strict';

const MongoClient = require('mongodb').MongoClient;
let db;
let ObjectId = require('mongodb').ObjectID;
const Movies = function () {
};


Movies.prototype.connectDb = function (callback) {
    MongoClient.connect("mongodb+srv://guilogar:guilogar@dcp-pnet-2019-2020-58msp.mongodb.net/test?retryWrites=true&w=majority",
        {useNewUrlParser: true, useUnifiedTopology: true},
        function (err, database) {
            if (err) {
                callback(err);
            }

   db = database.db('pnet-davidcorral').collection('movies');

            callback(err, database);
        });
};

Movies.prototype.add = function (movie, callback) {
    return db.insertOne(movie, callback);
};

Movies.prototype.get = function (_id, callback) {
    return db.find({_id: ObjectId(_id)}).toArray(callback);
};

Movies.prototype.getAll = function (callback) {
    return db.find({}).toArray(callback);
};


Movies.prototype.update = function (_id, updatedMovie, callback) {
    delete updatedMovie._id;
    return db.updateOne({_id: ObjectId(_id)}, {$set: updatedMovie}, callback);};

Movies.prototype.remove = function (_id, callback) {
    return db.deleteOne({_id: ObjectId(_id)}, callback);
};

Movies.prototype.removeAll = function (callback) {
    return db.deleteMany({}, callback);
};

module.exports = new Movies();