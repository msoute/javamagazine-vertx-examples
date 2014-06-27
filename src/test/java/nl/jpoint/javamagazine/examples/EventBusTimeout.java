package nl.jpoint.javamagazine.examples;


import org.junit.Test;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.testtools.TestVerticle;

import static org.vertx.testtools.VertxAssert.testComplete;

public class EventBusTimeout  extends TestVerticle {

    @Test
    public void HelloEventBusExample() {
        // Send 'Hello' to the HelloEventBusVerticle verticle on the eventbus.
        getVertx().eventBus().sendWithTimeout("timeout", "body", 10000, new Handler<AsyncResult<Message<String>>>() {
            @Override
            public void handle(AsyncResult<Message<String>> messageAsyncResult) {
                container.logger().info("Request failed " + messageAsyncResult.failed());
                testComplete();
            }

        });
    }
}
