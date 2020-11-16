package mongo

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.result.UpdateResult
import mongo.Constant.Companion.COLLECTION_NAME
import org.bson.Document
import org.bson.conversions.Bson

class DocumentOperation {
    val dollar = "$"

    private var databaseConnection: MongoDatabase = DatabaseConnection.getInstance()
    private var collection: MongoCollection<Document>

    init {
        collection = databaseConnection.getCollection(COLLECTION_NAME)
    }

    fun create(document: Document): InsertOneResult {
        return collection.insertOne(document)
    }

    fun read(filter: Bson): Document? {
        return collection.find(filter).limit(1).first()
    }

    fun readAll(): FindIterable<Document> {
        return collection.find()
    }

    fun updateOne(filter: Bson, update: Document): UpdateResult {
        return collection.updateOne(
            filter,
            Document.parse(
                """
                {${dollar}set:${update.toJson()} }
            """.trimIndent()
            )
        )
    }

    fun delete(filter: Bson): DeleteResult {
        return collection.deleteOne(filter)
    }
}