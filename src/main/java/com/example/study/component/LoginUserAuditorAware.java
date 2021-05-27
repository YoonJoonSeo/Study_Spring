package com.example.study.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {
// 인식 감시자????? 로그인유저를 인식하여 감시하겠다. 로그인 유저를 인식하여 감시하겠다.
    // 로그인 유저를 인식하여 감시하겠다. 로그인 유저를 인식하여 감시하겠다.
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("AdminServer");
    }
}
