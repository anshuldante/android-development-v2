package com.example.hiltditrials.dependencies;

import javax.inject.Inject;

/** A thermosiphon to pump the coffee. */
public class Thermosiphon implements Pump {
  private final CoffeeLogger logger;
  private final Heater heater;

  @Inject
  Thermosiphon(CoffeeLogger logger, Heater heater) {
    this.logger = logger;
    this.heater = heater;
  }

  @Override
  public void pump() {
    if (heater.isHot()) {
      logger.log("=> => pumping => =>");
    }
  }
}
