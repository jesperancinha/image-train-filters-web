package com.jesperancinha.imagecontour.filters

/**
  * Created by joao on 14-3-16.
  */
trait ImageFilter[A, B] extends (A => B)
