package ml.subs.app;

import ml.subs.app.config.KafkaConfiguration;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class TwitterKafkaConsumer {

    String groupName = "TwiterBigdata";

    public void run() {
        KafkaConsumer<Long, String> consumer = null;

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfiguration.SERVERS);

        properties.put("group.id", groupName);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put("enable.auto.commit", "false");


        try{
            consumer = consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(Arrays.asList(KafkaConfiguration.TOPIC));

            while (true){
                ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<Long, String> record : records){
                    System.out.println("THE DATA " +String.valueOf(record.value()));
                }
                consumer.commitAsync();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            consumer.commitSync();
            consumer.close();
        }
    }
}
