package ru.nsu.connector;

/**
 * Created by Kirill Prokofiev on 18.11.2022.
 * MIT License.
 */

public class KafkaConnector {

    protected KafkaConfiguration configuration;

    public static void main( String[] args ) { }

    public void init(KafkaConfiguration configuration) {
        if (configuration.isConsumer()) {
            System.out.println("[STATE] Connector is CONSUMER");
        } else if (configuration.isProducer()) {
            System.out.println("[STATE] Connector is PRODUCER");
        }
    }
}
