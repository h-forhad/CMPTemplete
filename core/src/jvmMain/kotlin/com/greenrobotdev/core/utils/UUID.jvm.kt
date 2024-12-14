package com.greenrobotdev.core.utils

import java.util.UUID

/**
 * Generates a random UUID as a [String].
 */
actual fun randomUUIDString(): String = UUID.randomUUID().toString()