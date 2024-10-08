package com.nursyah.finance.core

import com.nursyah.finance.R

object Constants {
  const val TIME_WITH_HOUR = "yyyy-MM-dd_HH-mm-ss"
  const val TIME_TEXT_MONTH = "dd-MMMM-yyyy"
  const val SETTINGS_STATE_DELETE = "delete"
  const val SETTINGS_STATE_RESTORE = "restore"
  const val SETTINGS_STATE_BACKUP = "backup"

  enum class Action {
    PAYMENTS, TRANSACT, ANALYTICS
  }
}