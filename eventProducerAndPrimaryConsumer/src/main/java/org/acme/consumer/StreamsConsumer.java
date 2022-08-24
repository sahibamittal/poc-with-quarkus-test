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
public class StreamsConsumer {

    @Produces
    public Topology buildTopology() {


        StreamsBuilder builder = new StreamsBuilder();
        ObjectMapperSerde<EventData> eventDataSerde = new ObjectMapperSerde<>(
                EventData.class);

        //receiving the event
        KStream<String, EventData> kStream = builder.stream("event", Consumed.with(Serdes.String(), eventDataSerde));
        KStream<String, String> splittedStreams = kStream.flatMapValues(value->value.getComponents());


        kStream.foreach(new ForeachAction<String, EventData>() {
            @Override
            public void apply(String s, EventData eventData) {
                System.out.println("Printing from event-in topic");
                /*try {
                    Thread.sleep(40000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
                System.out.println(eventData.getProject());
            }
        });
        splittedStreams.to("event-out", Produced.with(Serdes.String(), Serdes.String()));

       /* //sending event out
        KStream<String, String> streamRecv = builder.stream("event-out", Consumed.with(Serdes.String(), Serdes.String()));
        streamRecv.foreach(new ForeachAction<String, String>() {
            @Override
            public void apply(String s, String component) {
                System.out.println("Printing from event-out topic");
                *//*try {
                    Thread.sleep(40000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*//*
                System.out.println(component);
            }
        });*/

        return builder.build();
    }

}
