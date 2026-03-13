package ru.nikskul.springdockercomposekafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

  public static final String SIMPLE_TOPIC = "simple-topic";
  private static final Logger log = LogManager.getLogger(KafkaConfiguration.class);

  @Bean
  NewTopic topic() {
    return TopicBuilder.name(SIMPLE_TOPIC)
        .partitions(1)
        .replicas(1)
        .build();
  }

  @KafkaListener(id = "spring-consumer", groupId = "spring-consumer-group", topics = SIMPLE_TOPIC)
  public void listener(String in) {
    log.info("Consume message: {}", in);
  }

}
