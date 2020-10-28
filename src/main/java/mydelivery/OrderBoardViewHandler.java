package mydelivery;

import mydelivery.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderBoardViewHandler {


    @Autowired
    private OrderBoardRepository orderBoardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderRequested_then_CREATE_1 (@Payload OrderRequested orderRequested) {
        try {
            if (orderRequested.isMe()) {
                // view 객체 생성
                OrderBoard orderBoard = new OrderBoard();
                // view 객체에 이벤트의 Value 를 set 함
                orderBoard.setId(orderRequested.getId());
                orderBoard.setFoodName(orderRequested.getFoodName());
                orderBoard.setFoodStatus(orderRequested.getFoodQty());
                orderBoard.setOrderStatus(orderRequested.getOrderStatus());
                // view 레파지 토리에 save
                orderBoardRepository.save(orderBoard);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryRequested_then_UPDATE_1(@Payload DeliveryRequested deliveryRequested) {
        try {
            if (deliveryRequested.isMe()) {
                // view 객체 조회
                Optional<OrderBoard> orderBoardOptional = orderBoardRepository.findById(deliveryRequested.getOrderId());
                if( orderBoardOptional.isPresent()) {
                    OrderBoard orderBoard = orderBoardOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderBoard.setOrderStatus(deliveryRequested.getOrderSatus());
                    // view 레파지 토리에 save
                    orderBoardRepository.save(orderBoard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDelivered_then_UPDATE_2(@Payload Delivered delivered) {
        try {
            if (delivered.isMe()) {
                // view 객체 조회
                Optional<OrderBoard> orderBoardOptional = orderBoardRepository.findById(delivered.getOrderId());
                if( orderBoardOptional.isPresent()) {
                    OrderBoard orderBoard = orderBoardOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderBoard.setOrderStatus(delivered.getOrderStatus());
                    // view 레파지 토리에 save
                    orderBoardRepository.save(orderBoard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_DELETE_1(@Payload OrderCanceled orderCanceled) {
        try {
            if (orderCanceled.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                orderBoardRepository.deleteById(orderCanceled.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}