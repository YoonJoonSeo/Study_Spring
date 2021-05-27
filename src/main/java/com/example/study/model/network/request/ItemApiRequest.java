package com.example.study.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemApiRequest {

    private Long id;

    private String status;   // 상태

    private String name;     // 상품명

    private String title;    // 상품 타이틀

    private String content;  // 상품 내용

    private BigDecimal price; // 상품가격

    private String brandName;  // 브랜드

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private Long partnerId;    // 공급 파트너 id

}
