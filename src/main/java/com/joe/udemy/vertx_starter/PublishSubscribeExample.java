package com.joe.udemy.vertx_starter;

  import io.vertx.core.AbstractVerticle;
  import io.vertx.core.DeploymentOptions;
  import io.vertx.core.Promise;
  import io.vertx.core.Vertx;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;

  import java.time.Duration;

public class PublishSubscribeExample {



  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new PublishVerticle());
    vertx.deployVerticle(new SubscribeVerticle1());
    vertx.deployVerticle(new SubscribeVerticle2());
    //vertx.deployVerticle(new SubscribeVerticle2(),new DeploymentOptions().setInstances(2));
   // vertx.deployVerticle(SubscribeVerticle2.class.getName(),new DeploymentOptions().setInstances(2));
  }

  public static class PublishVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(PublishVerticle.class);
    public static final String ADDRESS = "my.request.address";

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      super.start(startPromise);
      var eventB = vertx.eventBus();
      final String message = "Hello Joe Request Message Sent";

      vertx.setPeriodic(Duration.ofSeconds(5).toMillis(), id -> {
        LOG.debug("Sending : {}", message);
        eventB.<String>publish(PublishVerticle.class.getName(),"Publishing from Joe");
        //LOG.debug("Response :{} ", reply.result().body());
        });
      }
    }



  public static class SubscribeVerticle1 extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(SubscribeVerticle1.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      super.start(startPromise);
      vertx.eventBus().<String>consumer(PublishSubscribeExample.PublishVerticle.class.getName() , message -> {
              LOG.debug("Receiving :{}",message.body());
      });
    }
  }




static class SubscribeVerticle2 extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(SubscribeVerticle2.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    vertx.eventBus().<String>consumer(PublishSubscribeExample.PublishVerticle.class.getName() , message -> {
      LOG.debug("Receiving :{}",message.body());
    });
  }
}
}
