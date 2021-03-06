package com.joe.udemy.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerExample extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(WorkerExample.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(new WorkerVerticle(),
      new DeploymentOptions()
        .setWorker(true)
      .setWorkerPoolSize(1)
      .setWorkerPoolName("my worker verticle")
    );
    super.start(startPromise);
    vertx.executeBlocking(event -> {
      LOG.debug("Executing Blocking code");
      try {
        Thread.sleep(5000);
        event.complete();
        //event.fail("Failing forcefully");
      } catch (InterruptedException e) {
        LOG.error("Failed : ", e);
        event.fail(e);
      }
    }, result -> {
      if(result.succeeded()){
        LOG.debug("Blocking call done");
      } else {
        LOG.debug("Blocking call failed :", result.cause());
      }

      });

  }
}
