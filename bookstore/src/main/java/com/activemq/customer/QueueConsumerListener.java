//package com.activemq.customer;
//
//import com.activemq.dao.ActivemqDao;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//public class QueueConsumerListener {
//
//    @Resource
//    private ActivemqDao activemqDao;
//
//    //queue模式的消费者
//    @JmsListener(destination="${spring.jms.queue-name}", containerFactory="queueListener")
//    public void readActiveQueue(String goodClassifyInfo) {
//        System.out.println("queue接收到：" + goodClassifyInfo);
//        activemqDao.addQueue(goodClassifyInfo);
//    }
//}
