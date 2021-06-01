//package com.example.elastic.serice;
//
//import org.springframework.messaging.Message;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.function.Consumer;
//
//@Service
//public class LogService implements Consumer<Message> {
//    @Override
//    public void accept(Message message) {
//       Optional.of( message.getPayload()).ifPresent(System.out::println);
//    }
//}
