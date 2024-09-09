import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object SimpleKafkaProducer extends App{
    val topic = "example-topic"

    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    try {
      for (i <- 1 to 10) {
        val record = new ProducerRecord[String, String](topic, s"key-$i", s"value-$i")
        producer.send(record)
        println(s"Sent: key-$i value-$i")
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      producer.close()
    }
}
