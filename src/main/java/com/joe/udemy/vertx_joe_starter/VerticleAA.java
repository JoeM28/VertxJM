package com.joe.udemy.vertx_joe_starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import static com.joe.udemy.vertx_joe_starter.JMMainVerticle.LOG;

public class VerticleAA extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    LOG.info("Start Promise Verticle AA {}",getClass().getName());
    startPromise.complete();
  }
    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {
      super.stop();
      LOG.info("STOP Promise Verticle AA {}",getClass().getName());
      stopPromise.complete();
    }


}
