# Order Notifications (via Kafka)

Projeto experimental para explorar os conceitos centrais do Apache Kafka (topics, producers, consumers, consumer groups e offsets) utilizando Spring Boot.

## Conceitos

- **Topic** — tópico `orders` criado via bean `NewTopic` em `KafkaConfig` com 3 partições
- **Producer** — `OrderProducer` envia eventos de pedido via `KafkaTemplate`, usando o `id` do pedido como chave
- **Consumer** — `OrderConsumer` escuta o tópico com `@KafkaListener` e exibe a notificação no log
- **Consumer Group** — `notification-service` agrupa consumers para balancear a carga entre as partições
- **Offset** — configurado com `auto-offset-reset=earliest` para que um novo consumer group leia o tópico desde o início
