package com.modcloth.druidia

import org.json4s._

import org.scalatra._
import org.scalatra.json._

import org.slf4j.{Logger,LoggerFactory}

class LoggingServlet extends DruidiaStack with JacksonJsonSupport with jackson.JsonMethods {
  val logger = LoggerFactory.getLogger(getClass)

  get("/") {
    contentType="text/html"
    layoutTemplate("/WEB-INF/templates/views/status.jade")
  }

  post("/:type") {
    logger.info(compact(parsedBody))
  }

  protected implicit val jsonFormats: Formats = DefaultFormats
}