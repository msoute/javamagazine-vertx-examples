package nl.jpoint.javamagazine;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;


public class BlockingEventLoopVerticle extends Verticle {
    @Override
    public void start() {
        container.deployVerticle(HelloEventBusVerticle.class.getName(), 1);
        getVertx().eventBus().registerHandler("block.eventbus", new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {

                getVertx().eventBus().send("hello.eventbus", "test", new Handler<Message<String>>() {
                    @Override
                    public void handle(Message<String> result) {
                        container.logger().info("Stop the world");
                        try {
                            Thread.sleep(2000);
                        message.reply(message.body() + " JavaMagazine [BLOCKING] !");
                        } catch (InterruptedException e) {
                            container.logger().info(e.getMessage());
                        }
                    }
                });
            }
        });
    }
}
