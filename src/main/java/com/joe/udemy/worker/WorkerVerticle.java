package com.joe.udemy.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import static com.joe.udemy.vertx_joe_starter.JMMainVerticle.LOG;

public class WorkerVerticle extends AbstractVerticle {

   @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.debug("Deployed as worker verticle");
    super.start(startPromise);
    Thread.sleep(5000);
    LOG.debug(("Blocking code done in Worker Verticle"));

  }
}
