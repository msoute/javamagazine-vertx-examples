package nl.jpoint.javamagazine;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

/**
 * Simple Verticle with an eventbus handler listening on the address 'hello.eventbus'.
 * replies the concatenated incoming message body and " JavaMagazine !" to the sender.
 */
public class HelloEventBusVerticle extends Verticle {
    @Override
    public void start() {
        getVertx().eventBus().registerHandler("hello.eventbus", new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                message.reply(message.body() + " JavaMagazine !");
            }
        });
    }
}
