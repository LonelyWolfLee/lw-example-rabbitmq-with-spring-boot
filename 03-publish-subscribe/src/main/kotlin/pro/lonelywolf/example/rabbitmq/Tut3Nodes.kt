package pro.lonelywolf.example.rabbitmq

import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.util.StopWatch
import java.util.concurrent.atomic.AtomicInteger


class Tut3Sender {
  @Autowired
  private lateinit var template: RabbitTemplate

  @Autowired
  private lateinit var fanout: FanoutExchange

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
    template.convertAndSend(fanout.name, "", message)
    println(" [x] Sent '$message'")
  }
}

class Tut3Receiver {

  @RabbitListener(queues = ["#{autoDeleteQueue1.name}"])
  @Throws(InterruptedException::class)
  fun receive1(`in`: String) {
    receive(`in`, 1)
  }

  @RabbitListener(queues = ["#{autoDeleteQueue2.name}"])
  @Throws(InterruptedException::class)
  fun receive2(`in`: String) {
    receive(`in`, 2)
  }

  @Throws(InterruptedException::class)
  fun receive(`in`: String, receiver: Int) {
    val watch = StopWatch()
    watch.start()
    println("instance $receiver [x] Received '$`in`'")
    doWork(`in`)
    watch.stop()
    println("instance $receiver [x] Done in ${watch.totalTimeSeconds}s")
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
