package mongo

import org.bson.Document
import org.bson.conversions.Bson

val documentOperation = DocumentOperation()
fun main() {
    printAll()
    insert()
    printAll()
    readOne()
    deleteDocument()
    printAll()

    val apel = Document.parse("""
        {nama: "Apel"}
    """.trimIndent())
    val nanas = Document.parse("""
        {nama: "Nanas"}
    """.trimIndent())

    update(apel,nanas)
    printAll()
    update(nanas,apel)
    printAll()
}

fun update(filter:Bson,update:Document) {
    println("Update")
    documentOperation.updateOne(filter,update)
}

fun readOne() {
    println("Read One")
    val filteredDocument = Document.parse(
        """
        {nama:"Jeruk"}
    """.trimIndent()
    )
    val result = documentOperation.read(filteredDocument)
    println(result)
}

fun deleteDocument() {
    println("Delete")
    val deleteDocument = Document.parse(
        """
        {nama: "Buah Baru"}
    """.trimIndent()
    )
    documentOperation.delete(deleteDocument)
}

fun insert() {
    println("Insert")
    val insertDocument = Document.parse(
        """
        {
            "nama" : "Buah Baru",
            "jenis" : [ 
                "purut", 
                "nipis", 
                "kumquat", 
                "bali"
            ],
            "harga" : 50000.0,
            "berat" : 20.0,
            "asal" : "PT. Baru",
            "info_berat" : {
                "berat" : 20.0,
                "uom" : "Kg"
            }
        }
    """.trimIndent()
    )

    documentOperation.create(document = insertDocument)
}

fun printAll() {
    println("== Print all documents ==")
    documentOperation.readAll().forEach {
        println(it.toJson())
    }

}