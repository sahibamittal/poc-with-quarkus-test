package org.poc;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class EventProducer {

    @Inject @Channel("event-out")
    Emitter<Record<String, List<String>>> emitter;

    public void sendEventToKafka(EventData eventData) {
        emitter.send(Record.of(eventData.project, eventData.components));
    }
}