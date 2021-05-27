package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 설정 파일인데....
@EnableJpaAuditing // JpaAuditing 을 사용하도록 해준다. 감시를 활성화시키겠다.
public class JpaConfig {
}
