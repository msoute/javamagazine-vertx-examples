package nl.jpoint.javamagazine.examples;

import nl.jpoint.javamagazine.HelloVertxVerticle;
import org.junit.Test;
import org.vertx.testtools.TestVerticle;

import static org.vertx.testtools.VertxAssert.testComplete;

public class HelloVertx extends TestVerticle {

    @Test
    public void HelloVertxExample() {
        container.deployVerticle(HelloVertxVerticle.class.getName());
        testComplete();
    }
}
