package com.modcloth.backend

import org.json4s._

class JacksonSerializer extends JsonSerializer[JValue] with jackson.JsonMethods

abstract class JsonSerializer[T] extends JsonMethods[T] {
  import JsonAST.concat
  import JsonDSL._
}
