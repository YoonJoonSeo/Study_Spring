package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController     // rest 에 따라 controll 할 것이다.
@RequestMapping("/api/user")   // 요청 주소는 /api/user 이다.
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    UserApiLogicService userApiLogicService;

    // CRUD 를 mapping 한다.

    @Override
    @PostMapping("")  //   /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userApiLogicService.create(request);
    }

    @Override               //  /api/user/{id}
    @GetMapping("{id}")  // read 하려면 아뒤가 있어야 하니깐!!!, 주소는 무조건 "" 안에 있어야 한다.
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("read id : {}", id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")       // api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")   // /api/user/{id} 주소는 무조건 "" 안에 있어야 한다.
    public Header delete(@PathVariable(name = "id") Long id) {

        return userApiLogicService.delete(id);
    }

}


