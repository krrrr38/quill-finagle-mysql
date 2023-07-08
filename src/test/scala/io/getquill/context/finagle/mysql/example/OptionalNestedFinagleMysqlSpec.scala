package io.getquill.context.finagle.mysql.example

import com.twitter.util.{Await, Future}
import io.getquill.context.finagle.mysql.testContext
import io.getquill.context.test.example.OptionalNestedSpec

class OptionalNestedFinagleMysqlSpec extends OptionalNestedSpec {

  val context: testContext.type = testContext
  import testContext._

  def await[T](future: Future[T]): T = Await.result(future)

  override protected def beforeEach(): Unit = {
    import Setup._
    await(testContext.run(query[Contact].delete))
    ()
  }

  "1.Optional Inner Product" - {
    import `1.Optional Inner Product`._
    "1.Ex1 - Not null inner product" in {
      await(context.run(`1.Ex1 - Not null inner product insert`))
      await(context.run(data)) mustEqual List(`1.Ex1 - Not null inner product result`)
    }
    "1.Ex1 Auto - Not null inner product" in {
      val result = `1.Ex1 - Not null inner product result`
      await(context.run(data.insertValue(lift(result))))
      await(context.run(data)) mustEqual List(result)
    }

    "1.Ex2 - null inner product" in {
      await(context.run(`1.Ex2 - null inner product insert`))
      // Finagle's equivalent to an NPE here is an `IllegalStateException: Value 'NullValue' can't be decoded to 'int'`
      assertThrows[IllegalStateException] {
        await(context.run(data))
      }
    }
    "1.Ex2 Auto - null inner product" in {
      val result = `1.Ex2 - null inner product result`
      await(context.run(data.insertValue(lift(result))))
      await(context.run(data)) mustEqual List(result)
    }
  }

  "2.Optional Inner Product" - {
    import `2.Optional Inner Product with Optional Leaf`._
    "2.Ex1 - Not null inner product" in {
      await(context.run(`2.Ex1 - not-null insert`))
      await(context.run(data)) mustEqual List(`2.Ex1 - not-null result`)
    }
    "2.Ex1 Auto - Not null inner product" in {
      val result = `2.Ex1 - not-null result`
      await(context.run(data.insertValue(lift(result))))
      await(context.run(data)) mustEqual List(result)
    }

    "2.Ex2 - Not null inner product" in {
      await(context.run(`2.Ex2 - Null inner product insert`))
      await(context.run(data)) mustEqual List(`2.Ex2 - Null inner product result`)
    }
    "2.Ex2 Auto - Not null inner product" in {
      val result = `2.Ex2 - Null inner product result`
      await(context.run(data.insertValue(lift(result))))
      await(context.run(data)) mustEqual List(result)
    }

    "2.Ex3 - Null inner leaf" in {
      await(context.run(`2.Ex3 - Null inner leaf insert`))
      await(context.run(data)) mustEqual List(`2.Ex3 - Null inner leaf result`)
    }
    "2.Ex3 Auto - Null inner leaf" in {
      val result = `2.Ex3 - Null inner leaf result`
      await(context.run(data.insertValue(lift(result))))
      await(context.run(data)) mustEqual List(result)
    }
  }

  "3.Optional Nested Inner Product" - {
    import `3.Optional Nested Inner Product`._
    "3.Ex1 - Null inner product insert" in {
      await(context.run(`3.Ex1 - Null inner product insert`))
      await(context.run(data)) mustEqual List(`3.Ex1 - Null inner product result`)
    }
    "3.Ex1 Auto - Null inner product insert" in {
      val result = `3.Ex1 - Null inner product result`
      await(context.run(data.insertValue(lift(result))))
      await(context.run(data)) mustEqual List(result)
    }

    "3.Ex2 - Null inner leaf" in {
      await(context.run(`3.Ex2 - Null inner leaf insert`))
      await(context.run(data)) mustEqual List(`3.Ex2 - Null inner leaf result`)
    }
    "3.Ex2 Auto - Null inner leaf" in {
      val result = `3.Ex2 - Null inner leaf result`
      await(context.run(data.insertValue(lift(result))))
      await(context.run(data)) mustEqual List(result)
    }
  }
}
