package com.modcloth.druidia

import org.json4s._

import org.scalatra._
import org.scalatra.json._

import org.slf4j.{Logger,LoggerFactory,MDC}

class LoggingServlet extends DruidiaStack with JacksonJsonSupport with jackson.JsonMethods {
  val logger = LoggerFactory.getLogger(getClass)
  val hostname = System.getenv("HOSTNAME");

  get("/") {
    contentType="text/html"
    layoutTemplate("/WEB-INF/templates/views/status.jade")
  }

  post("/:source/:type") {
    val logName = "%s_%s_%s".format(hostname, params("source"), params("type"))

    MDC.put("baseLogName", logName)
    logger.info(compact(parsedBody))
  }

  protected implicit val jsonFormats: Formats = DefaultFormats
}
