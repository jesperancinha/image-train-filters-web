package com.jesperancinha.imagecontour.filters

trait ImageFilter[A, B] extends (A => B)
