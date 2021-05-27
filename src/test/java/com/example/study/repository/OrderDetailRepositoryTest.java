package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // C R U D

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));

//        orderDetail.setOrderGroupId(1L);          // 장바구니 아이디
//        orderDetail.setItemId(1L);                // 상품 아이디

        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");


        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        // create 는 새로운 인스턴스를 생성할 필요없이 지금의 참조변수가 NotNull 인지만 체크하면 된다.
       Assert.assertNotNull(newOrderDetail);
    }


    public void read(){}
    public void update(){}

    @Test
    public void delete(){

        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(5L);

//        Assert.assertTrue(orderDetail.isPresent());
        orderDetail.ifPresent(i -> {
            orderDetailRepository.delete(i);
        } );


        Optional deletedOrderDetail = orderDetailRepository.findById(5L);

        Assert.assertFalse(deletedOrderDetail.isPresent());
    }

}
