package nl.jpoint.javamagazine;

import org.vertx.java.platform.Verticle;

public class HelloVertxVerticle extends Verticle {

    public void start() {
        container.logger().info("Hello Vert.x !");
    }
}
