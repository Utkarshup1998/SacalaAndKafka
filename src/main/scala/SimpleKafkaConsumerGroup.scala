import java.time.Duration
import java.util.{Collections, Properties}
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

object SimpleKafkaConsumerGroup extends App{
    val topic = "example-topic"

    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("group.id", "example-group")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(Collections.singletonList(topic))

    try {
      while (true) {

        //here we are using laster version of kafka which is not supporting Duratin so we have to add L
        val records = consumer.poll(100L)
        records.forEach { record =>
          println(s"Received: key=${record.key()} value=${record.value()} offset=${record.offset()}")
        }
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      consumer.close()
    }
}
