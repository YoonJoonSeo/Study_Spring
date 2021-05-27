package com.example.study.controller;


import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // return 값을 JSON 으로 반환
@RequestMapping("/api")  //     localhost:8080/api 까지 주소 매
public class GetController {

    //@RequestMapping(method = RequestMethod.GET, path = "/getMethod")     // localhost:8080/api/getMethod
    @GetMapping("/getMethod")
    public String getRequest(){
        return "Hi getMethod";
    }

    @GetMapping("/getParameter")   // localhost:8080/api/getParameter?id=1234&password=abcd
    public String GetParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password = "bbbbb"; // local 변수로 어쩔 수 없이 password를 사용해야 한다면!!!
                                   // 위와 같이 @RequestParam(name = "password") 와 같이 별도로 정의해 주어야 한다.
        System.out.println("ID : " + id);
        System.out.println("Password : " + pwd);

        return id + pwd;
    }


    // localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

//        return "OK"; // String 형태로 반환할 때

        // JSON 형태로 return 할 때
        // {"account" : "", "email" : "", "page" = ""}
        return searchParam;
    }

    // 객체를 리턴하면 저절도 JSON 형태로 바뀐다.
    @GetMapping("/header")
    public Header getHeader() {

        // {"resultCode" : "OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
