package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user", "orderDetailList"})
@EntityListeners(AuditingEntityListener.class)
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String orderType;      // 주문의 형태 - 일괄 / 개별

    private String revAddress;     // 수신 주소

    private String revName;        // 수신인

    private String paymentType;         // 카드, 현금

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;      // 주문일자

    private LocalDateTime arrivalDate;  // 도착일자


    // 공통부분 ---> 감시자 annotation 붙어 있음
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy   // LoginUserAuditorAware 클래스의 getCurrentAuditor() 의 리턴값인 AdminServer 의 감시를 받는다.
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy // LoginUserAuditorAware 클래스의 getCurrentAuditor() 의 리턴값인 AdminServer 의 감시를 받는다.
    private String updatedBy;

//    private Long userId;

    /*
    User 클래스와 관계 설정
      나          와  다른클래스의 관계
    OrderGroup   :   User
        N        :     1
      Many       To     One
     */

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;


}
