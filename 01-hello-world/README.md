# Tutorial 1 : Hello World

## REFERENCE
[https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html](https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html)

## About

In this part of the tutorial we'll write two programs using the spring-amqp library; a producer that sends a single message, and a consumer that receives messages and prints them out. We'll gloss over some of the detail in the Spring AMQP API, concentrating on this very simple thing just to get started. It's a "Hello World" of messaging.

In the diagram below, "P" is our producer and "C" is our consumer. The box in the middle is a queue - a message buffer that RabbitMQ keeps on behalf of the consumer.

<p align="center">
  <img src="https://www.rabbitmq.com/img/tutorials/python-one.png" alt="tutorial 01 : Hello World" />
</p>
