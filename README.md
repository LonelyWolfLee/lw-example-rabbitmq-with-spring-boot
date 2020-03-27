# RabbitMQ Tutorials with Spring Boot

## Reference

- Get Started : [https://www.rabbitmq.com/getstarted.html](https://www.rabbitmq.com/getstarted.html)
- Installation : [https://www.rabbitmq.com/download.html](https://www.rabbitmq.com/download.html)
- Official Docker : [https://registry.hub.docker.com/_/rabbitmq/](https://registry.hub.docker.com/_/rabbitmq/)
- AMQP 0-9-1 Model Explain : [https://www.rabbitmq.com/tutorials/amqp-concepts.html](https://www.rabbitmq.com/tutorials/amqp-concepts.html)
- AMQP 0-9-1 Quick Reference : [https://www.rabbitmq.com/amqp-0-9-1-quickref.html](https://www.rabbitmq.com/amqp-0-9-1-quickref.html)

## Overview

RabbitMQ 는 AMQP(Advanced Message Queuing Protocol)을 충실히 구현하고 있는 Open Source Message Queue 입니다. RabbitMQ 는 Server 와 Client 로 이루어져 있으며, Server 는 `Message Queue System`으로써의  `Broker` 의 역할을, Client 는 `Producer`/`Consumer` 의 역할을 수행한다. 각각의 Server 와 Client 는 반드시 같은 host 에 존재 할 필요는 없습니다.

<p align="center">
  <img src="https://www.rabbitmq.com/img/tutorials/intro/hello-world-example-routing.png" alt="AMQP Model"/>
</p>

### Jargon (용어)

###### RabbitMQ Server == MQ System == (Message) Broker

##### Producer

`Sender`. MQ 시스템으로 메세지를 보내는 프로그램 입니다.

##### Consumer

`Receiver`. MQ 시스템으로 부터 메세지를 받기 위해 기다리는 프로그램 입니다.

##### Exchange

`MQ 시스템의 교환기`. Producer 가 MQ 시스템으로 메세지를 보내게 되면 우선 Exchange 가 메세지를 받아서 0개 이상의 Queue 로 `Routing` 합니다. Routing 되는 알고리즘은 `Exchange Type` 및 `Binding` 이라 불리는 rule 에 따라 달라집니다. 

|   Exchange type  |        Default pre-declared names       |
| :--------------: | :-------------------------------------: |
| Direct exchange  |      (Empty string) and amq.direct      |
| Fanout exchange  |                amq.fanout               |
|  Topic exchange  |                 amq.topic               |
| Headers exchange | amq.match (and amq.headers in RabbitMQ) |

##### Binding

##### Queue

`Message Buffer`. 메세지가 MQ 시스템과 어플리케이션을 따라 흐를 수 있게 하는 메세지 대기열 입니다. 메세지는 Queue 내부에만 저장이 될수 있으며 그 양은 설정된 Host 의 메모리 및 디스크 용량에 의해서만 제한됩니다. 여러 Client 가 하나의 Queue 에서 메세지를 주고 받을 수 있습니다.

##### Connection

##### Channel

##### Virtual Host (vhost)

