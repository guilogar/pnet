'use strict';

const MongoClient = require('mongodb').MongoClient;

const ObjectId = require('mongodb').ObjectID;

class Database {
    async connectDb (callback, collectionDatabase)
    {
        try
        {
            const database = await MongoClient.connect(
                "mongodb://pnet-guillermo-elias:pnet-guillermo-elias3000@ds157834.mlab.com:57834/heroku_0dql66zr",
                { useNewUrlParser: true, useUnifiedTopology: true }
            );
            this.db = await database.db('heroku_0dql66zr').collection(collectionDatabase);
            return this.db;
        } catch(err)
        {
            callback(err);
        }
    }

    async add (object, callback) {
        return await this.db.insertOne(object, callback);
    };

    async get (_id, callback) {
        return await this.db.find({_id: ObjectId(_id)}).toArray(callback);
    };
    
    async getAll (callback) {
        return await this.db.find({}).toArray(callback);
    };
    
    async update (_id, updatedObject, callback) {
        delete updatedObject._id;
        return await this.db.updateOne({_id: ObjectId(_id)}, {$set: updatedObject}, callback);};
    
    async remove (_id, callback) {
        return await this.db.deleteOne({_id: ObjectId(_id)}, callback);
    };
    
    async removeAll (callback) {
        return await this.db.deleteMany({}, callback);
    };
};

module.exports = new Database();
