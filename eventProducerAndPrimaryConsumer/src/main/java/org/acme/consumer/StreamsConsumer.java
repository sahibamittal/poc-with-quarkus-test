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

        //receiving the event
        KStream<String, EventData> kStream = builder.stream("event", Consumed.with(Serdes.String(), eventDataSerde));
        KStream<String, String> splittedStreams = kStream.flatMapValues(value->value.getComponents());


        kStream.foreach(new ForeachAction<String, EventData>() {
            @Override
            public void apply(String s, EventData eventData) {
                System.out.println("Received new event " + eventData.eventType + " with project " + eventData.getProject());
            }
        });
        splittedStreams.to("event-out", Produced.with(Serdes.String(), Serdes.String()));

        //sending event out
        KStream<String, String> streamRecv = builder.stream("event-out", Consumed.with(Serdes.String(), Serdes.String()));
        streamRecv.foreach(new ForeachAction<String, String>() {
            @Override
            public void apply(String eventId, String componentName) {
                System.out.println("Sending component - " + componentName + " to each analyser.");
            }
        });

        return builder.build();
    }

}