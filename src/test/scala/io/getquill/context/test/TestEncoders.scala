package io.getquill.context.test

import io.getquill.MappedEncoding
import io.getquill.context.test.sql.EncodingTestType

/**
 * @see
 *   [[https://github.com/zio/zio-quill/blob/bf18ce1add6d0ce0ad4109cdaba2a4bece47f48c/quill-sql/src/test/scala/io/getquill/context/sql/TestEncoders.scala]]
 */
trait TestEncoders {
  implicit val encodingTestTypeEncoder: MappedEncoding[EncodingTestType, String] =
    MappedEncoding[EncodingTestType, String](_.value)
  implicit val nameEncoder: MappedEncoding[sql.Number, String] = MappedEncoding[sql.Number, String](_.value)
}
