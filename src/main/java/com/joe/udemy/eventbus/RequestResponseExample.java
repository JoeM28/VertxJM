package com.joe.udemy.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class RequestResponseExample {

  public static void main(String[] args) {
    var vertx=Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
     }

  static class RequestVerticle extends AbstractVerticle {

  }

  static class ResponseVerticle extends AbstractVerticle {

  }
}
