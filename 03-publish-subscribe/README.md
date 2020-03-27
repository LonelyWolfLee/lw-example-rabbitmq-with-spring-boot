# Tutorial 3 : Publish/Subscribe

## REFERENCE
[https://www.rabbitmq.com/tutorials/tutorial-three-spring-amqp.html](https://www.rabbitmq.com/tutorials/tutorial-three-spring-amqp.html)

## About

In this part of the tutorial we'll implement the fanout pattern to deliver a message to multiple consumers. This pattern is also known as "publish/subscribe" and is implemented by configuring a number of beans in our `Tut3Config` file.
                             
Essentially, published messages are going to be broadcast to all the receivers.

The core idea in the messaging model in RabbitMQ is that the producer never sends any messages directly to a queue. Actually, quite often the producer doesn't even know if a message will be delivered to any queue at all.

Instead, the producer can only send messages to an exchange. An exchange is a very simple thing. On one side it receives messages from producers and the other side it pushes them to queues. The exchange must know exactly what to do with a message it receives. Should it be appended to a particular queue? Should it be appended to many queues? Or should it get discarded. The rules for that are defined by the exchange type.

<p align="center">
  <img src="https://www.rabbitmq.com/img/tutorials/exchanges.png" alt="tutorial 02 : Work Queue" />
</p>

There are a few exchange types available: `direct`, `topic`, `headers` and `fanout`. We'll focus on the last one -- the fanout. Let's configure a bean to describe an exchange of this type, and call it `tut.fanout`