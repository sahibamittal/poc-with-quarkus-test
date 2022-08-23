/*
package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import io.quarkus.kafka.client.serialization.ObjectMapperSerde;
import org.acme.model.EventData;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

@ApplicationScoped
public class StreamConsumer1 {

    public static void main(String[] args) {


        StreamsBuilder builder = new StreamsBuilder();
        Properties config = new Properties();
        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "parser");

        ObjectMapperSerde<EventData> eventDataSerde = new ObjectMapperSerde<>(
                EventData.class);


        KStream<String, EventData> kStream1 = builder.stream("event-out", Consumed.with(Serdes.String(), eventDataSerde));
        kStream1.foreach(new ForeachAction<String, EventData>() {
            @Override
            public void apply(String s, EventData eventData) {
                System.out.println("started");
                try {
                    Thread.sleep(40000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(eventData.getProject());
            }
        });

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        // only do this in dev - not in prod
        streams.cleanUp();
        streams.start();
    }

}
*/
