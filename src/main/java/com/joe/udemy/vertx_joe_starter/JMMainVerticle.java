package com.joe.udemy.vertx_joe_starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public class JMMainVerticle extends AbstractVerticle {

  public static final Logger LOG = LoggerFactory.getLogger(JMMainVerticle.class);
  public static void main(String[] args) {

    System.out.println("inside main");

    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new JMMainVerticle());
    System.out.println("main vertx deployment");

  }

  /*
  @Override
  public void start() throws Exception {
    super.start();
    System.out.println("Start " +  getClass().getName());
    System.out.println("Start again "+ getVertx().getClass().getName());
  }

   */

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.debug("Start {}",getClass().getName());
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Joe Kickstart!");
    }).listen(8888, http -> {
      if (http.succeeded()) {

        LOG.info("Main HTTP server started on port 8888");
        vertx.deployVerticle(new VerticleA());
        LOG.debug("Deployed VerA {}",getClass().getName());
        vertx.deployVerticle(VerticleB.class.getName(),
          new DeploymentOptions()
            .setInstances(2)
            .setConfig(new JsonObject()
              .put("id", UUID.randomUUID().toString())
              .put("name", VerticleB.class.getSimpleName())
        )
        );

        LOG.debug("Deployed VerB 2 instance {}",getClass().getName());
        startPromise.complete();

      } else {
        LOG.debug("Deployed VerB 2 FAILED {}",getClass().getName());
        startPromise.fail(http.cause());
      }
    });
  }


}
