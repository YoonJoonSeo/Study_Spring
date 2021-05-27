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
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"itemList", "category"})
@EntityListeners(AuditingEntityListener.class)
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    private String address;         // 거래처 주소

    private String callCenter;      // 콜센터 번호

    private String partnerNumber;   // 담당자 번호

    private String businessNumber;  // 사업자 번호

    private String ceoName;

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

//    private Long categoryId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")
    private List<Item> itemList;

    @ManyToOne
    private Category category;

}
