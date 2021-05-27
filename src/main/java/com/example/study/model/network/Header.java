package com.example.study.model.network;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> {

    /*
    < 변수 설정 >
    1. api 통신시간
    2. api 응답 코드: OK or ERROR
    3. api description
     */

    // 1. api 통신시간
    private LocalDateTime transactionTime;

    // 2. api 응답 코드
    private String resultCode;

    // 3. api 부가 설명
    private String description;

    private T data; // <T> 라고 쓰지 않는다??? 왜일까

    /*
    메소드 부분을 만들어 볼까? 객체 자체를 리턴하는 메소드!!!!
    1. 그냥 데이터 없는 호출 OK //
    2. 데이터 있는 데이터 호출 OK
    3. 설명을 가지고 있고, 데이터 없는 호출 ERROR
    */

    public static <T> Header<T> OK() {

        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    public static <T> Header<T> OK(T data) {

        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    public static <T> Header<T> ERROR(String description) {

        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }


}
