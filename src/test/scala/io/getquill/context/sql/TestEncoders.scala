package io.getquill.context.sql

import io.getquill.MappedEncoding
import io.getquill.context.sql.base.{EncodingTestType, Number}

/**
 * @see [[https://github.com/zio/zio-quill/blob/bf18ce1add6d0ce0ad4109cdaba2a4bece47f48c/quill-sql/src/test/scala/io/getquill/context/sql/TestEncoders.scala]]
 */
trait TestEncoders {
  implicit val encodingTestTypeEncoder = MappedEncoding[EncodingTestType, String](_.value)
  implicit val nameEncoder             = MappedEncoding[Number, String](_.value)
}
