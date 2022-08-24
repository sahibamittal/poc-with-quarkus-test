/*
package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.acme.model.EventData;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventConsumer {
    private final Logger logger = Logger.getLogger(EventConsumer.class);

    @Incoming("event-in")
    public void receive(Record<Integer, EventData> event) {
        logger.infof("Got an event for analysis : %s - %s", event.key(), event.value().getProject());
    }
}
*/

