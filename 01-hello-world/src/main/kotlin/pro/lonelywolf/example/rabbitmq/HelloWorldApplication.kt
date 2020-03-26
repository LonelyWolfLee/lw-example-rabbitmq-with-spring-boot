package pro.lonelywolf.example.rabbitmq

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
class HelloWorldApplication {
  @Profile("usage_message")
  @Bean
  fun usage(): CommandLineRunner = CommandLineRunner {
    println("This app uses Spring Profiles to control its behavior.\n")
    println("Sample usage: java -jar 01-hello-world-0.0.1-SNAPSHOT-boot.jar --spring.profiles.active=hello-world,receiver")
    println("Sample usage: java -jar 01-hello-world-0.0.1-SNAPSHOT-boot.jar --spring.profiles.active=hello-world,sender")
  }

  @Profile("!usage_message")
  @Bean
  fun tutorial(): CommandLineRunner {
    return RabbitAmqpTutorialsRunner()
  }
}

fun main(args: Array<String>) {
  runApplication<HelloWorldApplication>(*args)
}

@Configuration
class Config {
  @Bean
  fun hello(): Queue {
    return Queue("hello")
  }

  @Profile("sender")
  @Bean
  fun sender(): Tut1Sender {
    return Tut1Sender()
  }

  @Profile("receiver")
  @Bean
  fun receiver(): Tut1Receiver {
    return Tut1Receiver()
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