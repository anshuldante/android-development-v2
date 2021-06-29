package com.example.hiltditrials.dependencies;

/** A heater to heat the coffee. */
public interface Heater {
  void on();

  void off();

  boolean isHot();
}
