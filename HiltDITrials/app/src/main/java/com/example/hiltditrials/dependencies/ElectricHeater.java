package com.example.hiltditrials.dependencies;

import javax.inject.Inject;

/** An electric heater to heat the coffee. */
public class ElectricHeater implements Heater {

  private final CoffeeLogger logger;
  private boolean heating;

  @Inject
  ElectricHeater(CoffeeLogger logger) {
    this.logger = logger;
  }

  @Override
  public void on() {
    this.heating = true;
    logger.log("~ ~ ~ heating ~ ~ ~");
  }

  @Override
  public void off() {
    this.heating = false;
  }

  @Override
  public boolean isHot() {
    return heating;
  }
}
