package com.example.study.ifs;

import com.example.study.model.network.Header;

// 인터페이스도 CRUD 이다. 별 것 아니다... 전부다 CRUD 이다.
// 사용자의 request 에 맞춰 response 가 이루어 지는데...
public interface CrudInterface<Req, Res> {

    // CRUD

    // request 에 의해 생성된다.
    Header<Res> create(Header<Req> request);

    // request 를 통한 변경사항은 없다.
    Header<Res> read(Long id);

    // request 의 변경 요구가 있다.
    Header<Res> update(Header<Req> request);

    // 그냥 삭제???
    Header delete(Long id);

}

