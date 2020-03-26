# Tutorial 2 : Work Queue

## REFERENCE
[https://www.rabbitmq.com/tutorials/tutorial-two-spring-amqp.html](https://www.rabbitmq.com/tutorials/tutorial-two-spring-amqp.html)

## About

In this part of the tutorial we'll create a Work Queue that will be used to distribute time-consuming tasks among multiple workers.

The main idea behind Work Queues (aka: Task Queues) is to avoid doing a resource-intensive task immediately and having to wait for it to complete. Instead we schedule the task to be done later. We encapsulate a task as a message and send it to a queue. A worker process running in the background will pop the tasks and eventually execute the job. When you run many workers the tasks will be shared between them.

This concept is especially useful in web applications where it's impossible to handle a complex task during a short HTTP request window.

<p align="center">
  <img src="https://www.rabbitmq.com/img/tutorials/python-two.png" alt="tutorial 02 : Work Queue" />
</p>