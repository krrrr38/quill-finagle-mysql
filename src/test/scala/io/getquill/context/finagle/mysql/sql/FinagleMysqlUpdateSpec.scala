package io.getquill.context.finagle.mysql.sql

import com.twitter.util.{Await, Future}
import io.getquill.context.finagle.mysql.testContext
import io.getquill.context.test.LogCapture
import io.getquill.context.test.sql.QuerySpec
import org.scalatest.BeforeAndAfterEach

class FinagleMysqlUpdateSpec extends QuerySpec with BeforeAndAfterEach with LogCapture {

  val context = testContext
  import testContext._

  def await[T](r: Future[T]): T = Await.result(r)

  override def beforeEach(): Unit = {
    await(testContext.run(deleteAll))
    ()
  }

  "update" - {
    "batch which is not optimized" in {
      val (logs, _) = logCapture {
        await(testContext.run(liftQuery(productEntries).foreach { e =>
          query[Product].filter(_.id == e.id).update(_.description -> "updated")
        }))
      }
      val occurrence = logs.split("""UPDATE Product x1 SET description = 'updated' WHERE x1.id = \?""").length - 1
      occurrence must be(3)
    }
  }
}
