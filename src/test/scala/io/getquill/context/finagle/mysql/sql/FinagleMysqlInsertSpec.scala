package io.getquill.context.finagle.mysql.sql

import com.twitter.util.{Await, Future}
import io.getquill.context.finagle.mysql.testContext
import io.getquill.context.test.LogCapture
import io.getquill.context.test.sql.QuerySpec
import org.scalatest.BeforeAndAfterEach

class FinagleMysqlInsertSpec extends QuerySpec with BeforeAndAfterEach with LogCapture {

  val context = testContext
  import testContext._

  def await[T](r: Future[T]): T = Await.result(r)

  override def beforeEach(): Unit = {
    await(testContext.run(deleteAll))
    ()
  }

  "insert" - {
    "batch which is optimized as bulk insert action" in {
      val (logs, _) = logCapture {
        await(testContext.run(liftQuery(productEntries).foreach(e => productInsert(e))))
      }
      logs must include("INSERT INTO Product (id,description,sku) VALUES (?, ?, ?), (?, ?, ?), (?, ?, ?)")
    }
    "batch returning which is not optimized" in {
      val (logs, _) = logCapture {
        await(testContext.run(liftQuery(productEntries).foreach(e => productInsertReturningId(e))))
      }
      val occurrence = logs.split("""INSERT INTO Product \(description,sku\) VALUES \(\?, \?\)""").length - 1
      occurrence must be(3)
    }
  }
}
