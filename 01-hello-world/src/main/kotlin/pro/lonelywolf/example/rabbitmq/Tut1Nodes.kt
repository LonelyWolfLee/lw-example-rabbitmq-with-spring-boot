package pro.lonelywolf.example.rabbitmq

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled


class Tut1Sender {
  @Autowired
  private lateinit var template: RabbitTemplate

  @Autowired
  private lateinit var queue: Queue

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  fun send() {
    val message = "Hello World!"
    template.convertAndSend(queue.name, message)
    println(" [x] Sent '$message'")
  }
}

@RabbitListener(queues = ["hello"])
class Tut1Receiver {
  @RabbitHandler
  fun receive(`in`: String) {
    println(" [x] Received '$`in`'")
  }
}
