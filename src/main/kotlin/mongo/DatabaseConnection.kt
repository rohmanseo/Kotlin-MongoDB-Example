package mongo

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import mongo.Constant.Companion.DATABASE_NAME


class DatabaseConnection {
    companion object {
        private var database: MongoDatabase? = null

        fun getInstance(): MongoDatabase {
            if (database == null) {
                val mongoClient = MongoClients.create("mongodb://localhost:27017")
                database = (mongoClient as MongoClient).getDatabase(DATABASE_NAME)
            }
            return database as MongoDatabase
        }

    }
}