package ml.subs.app;

import ml.subs.app.TwitterKafkaConsumer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("The Data is coming!");
        TwitterKafkaConsumer consumer = new TwitterKafkaConsumer();
        consumer.run();
    }
}
