package nl.jpoint.javamagazine.examples;

import nl.jpoint.javamagazine.BlockingEventLoopVerticle;
import org.junit.Test;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.testtools.TestVerticle;

import java.util.concurrent.atomic.AtomicInteger;

import static org.vertx.testtools.VertxAssert.testComplete;

public class BlockingEventLoopWithWorker extends TestVerticle {

    private AtomicInteger latch = new AtomicInteger(0);
    @Override
    public void start() {
        initialize();

        container.deployWorkerVerticle(BlockingEventLoopVerticle.class.getName(), new JsonObject(), 1, true, new AsyncResultHandler<String>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {
                startTests();
            }
        });
    }

    @Test
    public void HelloEventBusExample() {
        // Send 'Hello' to the HelloEventBusVerticle verticle on the eventbus.
        final long start = System.currentTimeMillis();
        for (int i=0;i<20;i++) {
            getVertx().eventBus().send("block.eventbus", "Hello " + i, new Handler<Message<String>>() {
                @Override
                public void handle(Message<String> response) {
                    container.logger().info("Request failed " + response.body());
                    if (latch.incrementAndGet() == 20) {
                        container.logger().info("Duration :" + String.valueOf(System.currentTimeMillis() - start));
                        testComplete();
                    }
                }
            });
        }


    }

}
