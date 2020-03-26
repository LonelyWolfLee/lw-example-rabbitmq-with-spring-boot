package pro.lonelywolf.example.rabbitmq

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.util.StopWatch
import java.util.concurrent.atomic.AtomicInteger


class Tut2Sender {
  @Autowired
  private lateinit var template: RabbitTemplate

  @Autowired
  private lateinit var queue: Queue

  private var dots = AtomicInteger(0)
  private var count = AtomicInteger(0)

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  fun send() {
    val builder = StringBuilder("Hello")
    if (dots.incrementAndGet() == 3) {
      dots.set(1)
    }
    for (i in 0 until dots.get()) {
      builder.append('.')
    }
    builder.append(count.incrementAndGet())
    val message = builder.toString()
    template.convertAndSend(queue.name, message)
    println(" [x] Sent '$message'")
  }
}

@RabbitListener(queues = ["hello"])
class Tut2Receiver(private val instance: Int) {

  @RabbitHandler
  fun receive(`in`: String) {
    val watch = StopWatch()
    watch.start()
    println("instance " + this.instance.toString() + " [x] Received '" + `in` + "'")
    doWork(`in`)
    watch.stop()
    println("instance " + this.instance.toString() + " [x] Done in " + watch.totalTimeSeconds.toString() + "s")
  }

  @Throws(InterruptedException::class)
  private fun doWork(`in`: String) {
    for (ch in `in`.toCharArray()) {
      if (ch == '.') {
        Thread.sleep(1000)
      }
    }
  }
}
