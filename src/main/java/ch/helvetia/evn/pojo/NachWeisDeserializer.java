package ch.helvetia.evn.pojo;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

// If you don’t want to create a deserializer for each of your pojo,
// you can use the generic io.vertx.kafka.client.serialization.JsonObjectDeserializer TODO to try
// that will deserialize to a javax.json.JsonObject. The corresponding serializer can
// also be used: io.vertx.kafka.client.serialization.JsonObjectSerializer.
public class NachWeisDeserializer extends JsonbDeserializer<Nachweis> {
    public NachWeisDeserializer(){
        // pass the class to the parent.
        super(Nachweis.class);
    }
}
