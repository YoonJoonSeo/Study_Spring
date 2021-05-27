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
@ToString(exclude = {"partnerList"})
@EntityListeners(AuditingEntityListener.class) // 엔티티의 변화를 듣고 로그를 남기는 감시하기
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql 의 경우
    private Long id;

    private String type;

    private String title;

    // 공통부분 ---> 감시자 annotation 붙어 있음
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy   // LoginUserAuditorAware 클래스의 getCurrentAuditor() 의 리턴값인 AdminServer 의 감시를 받는다.
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy // LoginUserAuditorAware 클래스의 getCurrentAuditor() 의 리턴값인 AdminServer 의 감시를 받는다.
    private String updatedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Partner> partnerList;

}
