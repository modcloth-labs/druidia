package com.modcloth.backend

import java.io.File

import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

class IntervalBasedRollingPolicy[E] extends TimeBasedRollingPolicy[E] {
  private[this] var interval = 0

  override def rollover() = {
    if (interval != 0 && interval % 5 == 0) {
      super.rollover()
      interval = 0
    }
    interval += 1
  }
}
