package io.getquill.context.sql

import io.getquill.MappedEncoding
import io.getquill.context.sql.base.EncodingTestType

/**
 * https://github.com/zio/zio-quill/blob/bf18ce1add6d0ce0ad4109cdaba2a4bece47f48c/quill-sql/src/test/scala/io/getquill/context/sql/TestDecoders.scala
 */
trait TestDecoders {
  implicit val encodingTestTypeDecoder = MappedEncoding[String, EncodingTestType](EncodingTestType)
  implicit val nameDecoder = MappedEncoding[String, base.Number](s =>
    base.Number
      .withValidation(s)
      .getOrElse(throw new Exception(s"Illegal number $s"))
  )
}