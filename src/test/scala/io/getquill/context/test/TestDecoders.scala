package io.getquill.context.test

import io.getquill.MappedEncoding
import io.getquill.context.test
import io.getquill.context.test.sql.EncodingTestType

/**
 * https://github.com/zio/zio-quill/blob/bf18ce1add6d0ce0ad4109cdaba2a4bece47f48c/quill-sql/src/test/scala/io/getquill/context/sql/TestDecoders.scala
 */
trait TestDecoders {
  implicit val encodingTestTypeDecoder: MappedEncoding[String, EncodingTestType] =
    MappedEncoding[String, EncodingTestType](EncodingTestType)
  implicit val nameDecoder: MappedEncoding[String, test.sql.Number] = MappedEncoding[String, test.sql.Number](s =>
    test.sql.Number
      .withValidation(s)
      .getOrElse(throw new Exception(s"Illegal number $s"))
  )
}
