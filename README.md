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

## Jargon (용어)

    RabbitMQ Server == MQ System == (Message) Broker

### Declare

Queue 또는 Exchange 를 선언/생성 하는 행위입니다. Name 이 같은 것이 있는 경우 redeclare 되지는 않습니다.

### Producer

`Sender`. MQ 시스템으로 메세지를 보내는 프로그램 입니다.

### Consumer

`Receiver`. MQ 시스템으로 부터 메세지를 받기 위해 기다리는 프로그램 입니다.

### Exchange

`MQ 시스템의 교환기`. Producer 가 MQ 시스템으로 메세지를 보내게 되면 우선 Exchange 가 메세지를 받아서 0개 이상의 Queue 로 `Routing` 합니다. Routing 되는 알고리즘은 `Exchange Type` 및 `Binding` 이라 불리는 rule 에 따라 달라집니다. 

|   Exchange type  |        Default pre-declared names       |
| :--------------: | :-------------------------------------: |
| Direct exchange  |      (Empty string) and amq.direct      |
| Fanout exchange  |                amq.fanout               |
|  Topic exchange  |                 amq.topic               |
| Headers exchange | amq.match (and amq.headers in RabbitMQ) |

그 밖에도 Exchange 는 몇 가지의 속성(attributes)을 가집니다.
- 이름 (Name) : Binding 될 routing key
- 내구성 (Durability) : Broker 재기동시 지속 가능성
- 자동 삭제 (Auto-delete) : Binding 된 마지막 queue 가 unbind 될 때에 자동 삭제
- 인자 (Arguments) : optional

Exchange 는 내구성이 있을 수도 있고 (durable) 일시적일 수도 있습니다 (transient). Durable 한 경우 Broker 가 재기동 되어도 지속되지만, Transient 한 경우는 재기동 후에는 다시 정의 해주어야 합니다.

#### Default exchange

`Default exchange` 는 direct 방식이지만 `이름` 속정의 정의 되지 않은 경우 입니다(empty string). 어플리케이션에서 간단하게 사용 할 때에 유용합니다. Queue 가 declare 되면 자신의 이름과 같은 routing key 를 가진 Exchange 와 bind 됩니다. 해당 Exchange 가 없는 경우 Queue Name 과 같은 routing key 로 declare 후 Binding 됩니다.   

#### Direct exchange

`Direct exchange` 는 routing key 를 기반으로 queue 로 메세지를 전달 합니다. Direct exchange 는 (물론 multicast routing 도 가능하지만) unicast routing 에 이상적입니다. 동작 방식은 아래와 같습니다.

- Queue 가 routing key 가 일치하는 Exchange 에 Binding 됩니다.
- 새로운 메세지가 Direct exchange 로 도착을 하면 routing key 가 일치 할 경우 해당 queue 로 메세지를 routing 해줍니다.

 Direct exchange 를 사용하여 복수개의 Worker 에 Round Robin 방식으로 메세지를 분배 할 수 있는데, 메세지의 Load Balancing 은 queue 간이 아니라 Consumer 단위로 Load Balancing 됨을 이해하는 것이 중요합니다.

#### Fanout exchange

#### Topic exchange

#### Headers exchange

### Binding

### Queue

`Message Buffer`. 메세지가 MQ 시스템과 어플리케이션을 따라 흐를 수 있게 하는 메세지 대기열 입니다. 메세지는 Queue 내부에만 저장이 될수 있으며 그 양은 설정된 Host 의 메모리 및 디스크 용량에 의해서만 제한됩니다. 여러 Client 가 하나의 Queue 에서 메세지를 주고 받을 수 있습니다.

### Connection

### Channel

### Virtual Host (vhost)

