package io.getquill.context.finagle.mysql

import io.getquill.{FinagleMysqlContext, Literal}
import io.getquill.context.sql.{TestDecoders, TestEncoders, TestEntities}

object testContext
    extends FinagleMysqlContext(
      Literal,
      "testDB"
    )
    with TestEntities
    with TestEncoders
    with TestDecoders
