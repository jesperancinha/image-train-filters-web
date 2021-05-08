package com.jesperancinha.imagecontour.filters

trait ImageFilter[B] extends (() => B)
