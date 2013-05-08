package com.modcloth.backend

import java.io.File

import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

object TimeHelper {
  def currentTimeInMillis: Long = System.currentTimeMillis
  def minToMillis(minutes: Int): Long = 1000 * 60 * minutes
}

class IntervalBasedRollingPolicy[E] extends TimeBasedRollingPolicy[E] {
  import TimeHelper.{currentTimeInMillis,minToMillis}

  private[this] var intervalStart = currentTimeInMillis
  private[this] var interval = minToMillis(5)

  override def rollover() = {
    val currentTime = currentTimeInMillis

    if (isRolloverTime(currentTime)) {
      super.rollover()
      intervalStart = currentTime
    }
  }

  def setInterval(newInterval: Int) {
    if (newInterval >= 5 && newInterval <= 60) {
      interval = minToMillis(newInterval)
    }
  }

  private def isRolloverTime(time: Long): Boolean =
    time >= intervalStart + interval
}
