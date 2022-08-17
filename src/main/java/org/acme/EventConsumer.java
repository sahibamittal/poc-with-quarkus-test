package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EventConsumer {
    private final Logger logger = Logger.getLogger(EventConsumer.class);

    @Incoming("event")
    public void receive(Record<String, List<String>> event) {
        logger.infof("Got an event for analysis : %s - %s", event.key(), event.value());
    }
}
