package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderGroup;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderGroupRepositoryTest extends StudyApplicationTests {

    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Test
    public void create() {
        String status = "COMPLETE";
        String orderType = "ALL";      // 주문의 형태 - 일괄 / 개별
        String revAddress = "서울시 강남구";     // 수신 주소
        String revName = "홍길동";        // 수신인
        String paymentType = "CARD";         // 카드, 현금
        BigDecimal totalPrice = BigDecimal.valueOf(900000);
        Integer totalQuantity = 1;
        LocalDateTime orderAt = LocalDateTime.now().minusDays(2);      // 주문일자
        LocalDateTime arrivalDate = LocalDateTime.now();  // 도착일자

        // 나머지 기본 4가지 공통
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        OrderGroup orderGroup = new OrderGroup();

        orderGroup.setStatus(status);
        orderGroup.setOrderType(orderType);
        orderGroup.setRevAddress(revAddress);
        orderGroup.setRevName(revName);
        orderGroup.setPaymentType(paymentType);
        orderGroup.setTotalPrice(totalPrice);
        orderGroup.setTotalQuantity(totalQuantity);
        orderGroup.setOrderAt(orderAt);
        orderGroup.setArrivalDate(arrivalDate);
        orderGroup.setCreatedAt(createdAt);
        orderGroup.setCreatedBy(createdBy);
//        orderGroup.setUserId(3L);

        OrderGroup newOrderGroup =  orderGroupRepository.save(orderGroup);

        Assert.assertNotNull(newOrderGroup);
    }

    @Test
    public void update() {
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(1L);

        Assert.assertNotNull(orderGroup);

    }

}
