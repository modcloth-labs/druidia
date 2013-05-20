package com.modcloth.backend

import java.io.File

import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

object TimeHelper {
  private val millisPerMinute = 1000 * 60

  def currentTimeInMillis: Long = System.currentTimeMillis
  def minToMillis(minutes: Int): Long = millisPerMinute * minutes
}

class IntervalBasedRollingPolicy[E] extends TimeBasedRollingPolicy[E] {
  import TimeHelper.{currentTimeInMillis,minToMillis}

  private val defaultInterval = 5
  private var interval = defaultInterval
  private var intervalStart = currentTimeInMillis
  private var intervalMillis = minToMillis(defaultInterval)

  override def start() {
    this.setMaxHistory(this.getMaxHistory() * interval)
    super.start()
  }

  override def rollover() = {
    val currentTime = currentTimeInMillis

    if (isRolloverTime(currentTime)) {
      super.rollover()
      intervalStart = currentTime
    }
  }

  def setInterval(newInterval: Int) {
    if (newInterval >= 1 && newInterval <= 60) {
      interval = newInterval
      intervalMillis = minToMillis(interval)
    }
  }

  private def isRolloverTime(time: Long): Boolean =
    time >= intervalStart + intervalMillis
}
