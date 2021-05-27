package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    // 공통부분 ---> 감시자 annotation 붙어 있음
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy   // LoginUserAuditorAware 클래스의 getCurrentAuditor() 의 리턴값인 AdminServer 의 감시를 받는다.
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy // LoginUserAuditorAware 클래스의 getCurrentAuditor() 의 리턴값인 AdminServer 의 감시를 받는다.
    private String updatedBy;


    /* 연관 관계 설정 ***********
    <나>   와  <다른 클래스>  의 관계
    <User> 와 <OrderGroup> 의 관계
    유저는 여러개의 상품묶음(장바구니를 만들수 있지만 역은 성립하지 않는다.)
      1    :    N
    내가 1일때는 조건이 많다.(게으르게 데리고 와야 하고, 데리고 오는 이름을 지정해 준다.)
     */

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")    // fetch: 가지고 오다, 불러오다. One 이 게으르게 1개만 가져오고, 어디에 맵핑?
    private List<OrderGroup> orderGroupList;
}
