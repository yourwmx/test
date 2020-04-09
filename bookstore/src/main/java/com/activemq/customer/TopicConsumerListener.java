//package com.activemq.customer;
//
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TopicConsumerListener {
//
//    //topic模式的消费者
//    @JmsListener(destination="${spring.jms.topic-name}", containerFactory="topicListener")
//    public void readActiveQueue(String goodClassifyInfo) {
//        System.out.println("topic接收到：");
//    }
//}
