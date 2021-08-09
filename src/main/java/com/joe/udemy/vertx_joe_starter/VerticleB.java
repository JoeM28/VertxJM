package com.joe.udemy.vertx_joe_starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import static com.joe.udemy.vertx_joe_starter.JMMainVerticle.LOG;

public class VerticleB extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
   LOG.info("Start Promise Verticle B {}",getClass().getName());
   LOG.info("Start{} Promise Verticle B with config {}",config().toString());
   LOG.info("Start{} Promise Verticle B with config {}" ,getClass().getName(),config().toString());
    startPromise.complete();


  }
}
