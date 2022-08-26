package org.acme.consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import io.quarkus.kafka.client.serialization.ObjectMapperSerde;

import org.acme.model.EventData;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.KStream;

@ApplicationScoped
public class StreamsConsumer {

    @Produces
    public Topology buildTopology() {


        StreamsBuilder builder = new StreamsBuilder();
        ObjectMapperSerde<EventData> eventDataSerde = new ObjectMapperSerde<>(
                EventData.class);

        KStream<String, EventData> kStream = builder.stream("event", Consumed.with(Serdes.String(), eventDataSerde)); //receiving the message on topic event
        //Splitting the incoming message into number of components
        //Setting the message key same as the name of component to send messages on different partitions of topic event-out
        KStream<String, String> splittedStreams = kStream.flatMapValues(value->value.getComponents()).selectKey((key, value) -> value);


        kStream.foreach(new ForeachAction<String, EventData>() {
            @Override
            public void apply(String s, EventData eventData) {
                System.out.println("Events from Producer Topic - event");
                System.out.println("Project Id - " + eventData.getProject());
                System.out.println("Component Ids -");
                eventData.getComponents().forEach(System.out::println);
            }
        });

        splittedStreams.to("event-out", Produced.with(Serdes.String(), Serdes.String()));

        return builder.build();
    }

}