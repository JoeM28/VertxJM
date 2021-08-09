package com.joe.udemy.vertx_starter;

  import io.vertx.core.AbstractVerticle;
  import io.vertx.core.Promise;
  import io.vertx.core.Vertx;
  import io.vertx.core.eventbus.EventBus;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;

public class PointtoPointExample {


  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new SenderVerticle());
    vertx.deployVerticle(new ReceiverVerticle());
  }

  static class SenderVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(RequestReponse.class);
    public static final String ADDRESS = "my.request.address";

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      super.start(startPromise);
      EventBus eventB = vertx.eventBus();
      final String message = "Hello Joe Request Message";

      vertx.setPeriodic(2000, id ->{
        vertx.eventBus().send(SenderVerticle.class.getName(),"Sender Joe Sending message");
        LOG.debug("Sending : {}",message);
      });
    }
  }


  static class ReceiverVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(RequestReponse.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      super.start(startPromise);

      vertx.eventBus().consumer(PointtoPointExample.SenderVerticle.class.getName() , message ->{
        LOG.debug("Received: {} ",message.body());
      });
    }
  }
}
