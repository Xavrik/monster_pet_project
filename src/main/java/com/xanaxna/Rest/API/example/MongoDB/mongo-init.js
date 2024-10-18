db = db.getSiblingDB("mongo_learn_db");

db.createUser({
    user: "mongoOwner",
    pwd: "qwerty",
    roles: [
        {
            role: 'readWrite',
            db: 'mongo_learn_db'
        },
    ],
});

db.createCollection("got_seasons_collection");
