import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.getByName<BootJar>("bootJar") {
  archiveClassifier.set("boot")
  mainClassName = "pro.lonelywolf.example.rabbitmq.HelloWorldApplication"
}