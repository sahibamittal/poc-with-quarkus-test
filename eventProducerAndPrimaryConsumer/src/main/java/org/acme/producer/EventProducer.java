package org.acme.producer;

import io.smallrye.reactive.messaging.kafka.Record;
import org.acme.model.EventData;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class  EventProducer {

    @Inject @Channel("event-out")
    Emitter<Record<Integer, EventData>> emitter;

    public void sendEventToKafka(EventData eventData) {
        emitter.send(Record.of(eventData.eventType, eventData));
    }

}
