package com.joe.udemy.vertx_joe_starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleAB extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    System.out.println("Inside Verticle A");
    System.out.println("Verticle A " + getClass().getName());
    startPromise.complete();


  }
}
