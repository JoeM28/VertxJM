package com.joe.udemy.vertx_joe_starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import static com.joe.udemy.vertx_joe_starter.JMMainVerticle.LOG;

public class VerticleA extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
        LOG.info("START PROMISE Verticle A {}",getClass().getName());
        vertx.deployVerticle(new VerticleAA(), whenDeployed -> {
        LOG.debug("Deployed Verticle AA instance {}",getClass().getName());
        vertx.undeploy(whenDeployed.result());
    } );
    vertx.deployVerticle(new VerticleAB());
    startPromise.complete();



  }
}
