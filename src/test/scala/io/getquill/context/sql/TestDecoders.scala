package io.getquill.context.sql

import io.getquill.MappedEncoding
import io.getquill.context.sql.example.EncodingTestType

/**
 * https://github.com/zio/zio-quill/blob/bf18ce1add6d0ce0ad4109cdaba2a4bece47f48c/quill-sql/src/test/scala/io/getquill/context/sql/TestDecoders.scala
 */
trait TestDecoders {
  implicit val encodingTestTypeDecoder: MappedEncoding[String, EncodingTestType] =
    MappedEncoding[String, EncodingTestType](EncodingTestType)
  implicit val nameDecoder: MappedEncoding[String, example.Number] = MappedEncoding[String, example.Number](s =>
    example.Number
      .withValidation(s)
      .getOrElse(throw new Exception(s"Illegal number $s"))
  )
}
