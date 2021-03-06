package me.lyh.protobuf.generic.test

import java.io.ByteArrayInputStream

import com.google.protobuf.CodedInputStream
import me.lyh.protobuf.generic._
import me.lyh.protobuf.generic.proto2.Schemas._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ProtobufTypeSpec extends AnyFlatSpec with Matchers {
  private val pt = ProtobufType[Optional]
  private val record = Records.optional

  "ProtobufType.descriptor" should "work" in {
    pt.descriptor shouldBe Optional.getDescriptor
  }

  "ProtobufType.newBuilder" should "work" in {
    pt.newBuilder().build() shouldBe Optional.newBuilder().build()
  }

  "ProtobufType.parseFrom" should "support byte array" in {
    pt.parseFrom(record.toByteArray) shouldBe record
  }

  it should "support ByteString" in {
    pt.parseFrom(record.toByteString) shouldBe record
  }

  it should "support InputStream" in {
    pt.parseFrom(new ByteArrayInputStream(record.toByteArray)) shouldBe record
  }

  it should "support CodedInputStream" in {
    pt.parseFrom(CodedInputStream.newInstance(record.toByteArray)) shouldBe record
  }
}
