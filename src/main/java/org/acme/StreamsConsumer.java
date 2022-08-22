package org.acme;

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

import java.util.Properties;

@ApplicationScoped
public class StreamsConsumer {

    @Produces
    public Topology buildTopology(){
        StreamsBuilder builder = new StreamsBuilder();
        ObjectMapperSerde<EventData> eventDataSerde = new ObjectMapperSerde<>(
                EventData.class);
        KStream<String, EventData> kStream = builder.stream("event", Consumed.with(Serdes.String(), eventDataSerde));
        kStream.foreach(new ForeachAction<String, EventData>() {
            @Override
            public void apply(String s, EventData eventData) {
                System.out.println(eventData.getProject());
            }
        });

        kStream.to("event-output", Produced.with(Serdes.String(), eventDataSerde));
        return builder.build();
    }
}
