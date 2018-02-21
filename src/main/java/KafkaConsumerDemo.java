import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id","test");
        properties.setProperty("enable.auto.commit","true");
        properties.setProperty("auto.commit.interval.ms","1000");
        properties.setProperty("auto.offset.reset","earliest");

        Consumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(properties);

        consumer.subscribe(Collections.singletonList("second_topic"));


        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
            for (ConsumerRecord<String, String> cr : consumerRecords) {
                System.out.println(cr);
            }
        }

    }
}
