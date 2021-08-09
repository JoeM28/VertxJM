package com.joe.udemy.vertx_starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestReponse {



  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
    vertx.deployVerticle(new ResponseVerticle1());
  }

  static class RequestVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(RequestReponse.class);
    public static final String ADDRESS = "my.request.address";

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      super.start(startPromise);
      var eventB = vertx.eventBus();
      final String message = "Hello Joe Request Message Sent";

      vertx.setPeriodic(2000, id -> {
        LOG.debug("Sending : {}", message);
        eventB.<String>request(ADDRESS, message, reply -> {
          //eventB.request("my.request.address","Hello",reply -> {
          LOG.debug("Response :{} ", reply.result().body());
        });
      });
    }
  }


  static class ResponseVerticle1 extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(RequestReponse.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      super.start(startPromise);
      vertx.eventBus().<String>consumer(RequestVerticle.ADDRESS, message -> {
        LOG.debug("Receiving :{}",message.body());
        message.reply("Received your message. Thanks");
      });
    }
  }
}
