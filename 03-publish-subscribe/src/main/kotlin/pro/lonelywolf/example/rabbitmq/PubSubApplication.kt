package pro.lonelywolf.example.rabbitmq

import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
class PubSubApplication {
  @Profile("usage_message")
  @Bean
  fun usage(): CommandLineRunner = CommandLineRunner {
    println("This app uses Spring Profiles to control its behavior.\n")
    println("Sample usage: java -jar 03-publish-subscribe-0.0.1-SNAPSHOT-boot.jar --spring.profiles.active=pub-sub,receiver --tutorial.client.duration=60000")
    println("Sample usage: java -jar 03-publish-subscribe-0.0.1-SNAPSHOT-boot.jar --spring.profiles.active=pub-sub,sender --tutorial.client.duration=60000")
  }

  @Profile("!usage_message")
  @Bean
  fun tutorial(): CommandLineRunner {
    return RabbitAmqpTutorialsRunner()
  }
}

fun main(args: Array<String>) {
  runApplication<PubSubApplication>(*args)
}

@Profile("pub-sub")
@Configuration
class Tut3Config {
  @Bean
  fun fanout(): FanoutExchange {
    return FanoutExchange("tut.fanout")
  }

  @Profile("sender")
  @Bean
  fun sender(): Tut3Sender {
    return Tut3Sender()
  }

  @Profile("receiver")
  class ReceiverConfig {
    @Bean
    fun autoDeleteQueue1(): Queue {
      return AnonymousQueue()
    }

    @Bean
    fun autoDeleteQueue2(): Queue {
      return AnonymousQueue()
    }

    @Bean
    fun binding1(fanout: FanoutExchange, autoDeleteQueue1: Queue): Binding {
      return BindingBuilder.bind(autoDeleteQueue1).to(fanout)
    }

    @Bean
    fun binding2(fanout: FanoutExchange, autoDeleteQueue2: Queue): Binding {
      return BindingBuilder.bind(autoDeleteQueue2).to(fanout)
    }

    @Bean
    fun receiver(): Tut3Receiver {
      return Tut3Receiver()
    }
  }
}

class RabbitAmqpTutorialsRunner : CommandLineRunner {
  @Value("\${tutorial.client.duration:0}")
  private val duration = 0

  @Autowired
  private lateinit var ctx: ConfigurableApplicationContext

  @Throws(Exception::class)
  override fun run(vararg arg0: String) {
    println("Ready ... running for " + duration + "ms")
    Thread.sleep(duration.toLong())
    ctx.close()
  }
}