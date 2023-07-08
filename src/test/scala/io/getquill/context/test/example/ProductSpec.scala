package io.getquill.context.test.example

import io.getquill.Query
import io.getquill.context.sql.SqlContext
import org.scalatest.BeforeAndAfterAll
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

case class Id(value: Long) extends AnyVal

/**
 * @see
 *   [[https://github.com/zio/zio-quill/blob/bf18ce1add6d0ce0ad4109cdaba2a4bece47f48c/quill-sql/src/test/scala/io/getquill/context/sql/ProductSpec.scala]]
 */
trait ProductSpec extends AnyFreeSpec with Matchers with BeforeAndAfterAll {

  val context: SqlContext[_, _]

  import context._

  case class Product(id: Long, description: String, sku: Long)

  val product = quote {
    query[Product]
  }

  val productInsert = quote { (p: Product) =>
    query[Product].insertValue(p)
  }

  val productInsertReturningId = quote { (p: Product) =>
    query[Product].insertValue(p).returningGenerated(_.id)
  }

  val productInsertBatch = quote { (b: Query[Product]) =>
    b.foreach(p => productInsertReturningId.apply(p))
  }

  val productById = quote { (id: Long) =>
    product.filter(_.id == id)
  }

  val productEntries = List(
    Product(0L, "Notebook", 1001L),
    Product(1L, "Soap", 1002L),
    Product(2L, "Pencil", 1003L)
  )

  val productSingleInsert = quote {
    product.insert(_.id -> 0, _.description -> "Window", _.sku -> 1004L).returningGenerated(_.id)
  }
}
