package nl.jpoint.javamagazine.examples;

import nl.jpoint.javamagazine.HelloEventBusVerticle;
import org.junit.Test;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.testtools.TestVerticle;

import static org.vertx.testtools.VertxAssert.testComplete;

public class HelloEventBus extends TestVerticle {

    @Override
    public void start() {
        initialize();
        container.deployVerticle(HelloEventBusVerticle.class.getName(), 1, new AsyncResultHandler<String>() {
            @Override
            public void handle(AsyncResult<String> asyncResult) {
                // Deployment is asynchronous, so we need to wait for the deployment to finish.
                startTests();
            }
        });
    }

    @Test
    public void HelloEventBusExample() {

        // Send 'Hello' to the HelloEventBusVerticle verticle on the eventbus.
        getVertx().eventBus().send("hello.eventbus", "Hello", new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                container.logger().info(message.body());
                testComplete();
            }
        });
    }
}
