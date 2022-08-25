package org.acme.consumer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;

import javax.enterprise.inject.Produces;

public class ComponentAnalyzer {

    @Produces
    public Topology buildTopology() {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<Integer, String> kStream = builder.stream("event-out", Consumed.with(Serdes.Integer(), Serdes.String()));
        kStream.foreach(new ForeachAction<Integer, String>() {
            @Override
            public void apply(Integer componentId, String componentName) {
                System.out.println("Printing from event-in topic");
                System.out.println("Got component id: "+componentId+" which has component name: "+componentName);
            }
        });
        return builder.build();

    }
}
