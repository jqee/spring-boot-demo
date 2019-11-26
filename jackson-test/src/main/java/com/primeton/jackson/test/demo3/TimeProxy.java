package com.primeton.jackson.test.demo3;

import java.lang.Override;
import java.lang.System;

class TimeProxy implements Flyable {
  private Flyable flyable;

  public TimeProxy(Flyable flyable) {
    this.flyable=flyable;
  }

  @Override
  public void fly() {
    long start =System.currentTimeMillis();

    this.flyable.fly();

    long end =System.currentTimeMillis();
    System.out.println("Fly time = "+(end-start)/1000+"s");
  }
}
