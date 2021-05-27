package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


// Test Class 에서 사용하는 @ 은
// @Autowired, @Test + @Transactional

public class UserRepositoryTest extends StudyApplicationTests {

    /*
    1. DI(Dependency Injection) -> test 할 클래스 주입?
        @Autowired 필요
        싱글톤패턴
    2. CRUD 작성 -> 메소드에는 @Test annotation 필요
     */


    // 싱글톤패턴 디자인
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){

        /*
         1. 데이터 입력 받기
         2. 인스턴스 생성
         3. 입력값 set 하기
         4. Repository 에  save 하기
         5. Assert 로 테스트 하기
               - Assert.assertNotNull(a);
               - Assert.assertEqual(a.getB, b)
         */

        String account = "Test911";
        String password = "Test911";
        String status = "REGISTERED";
        String email = "test911@gmail.com";
        String phoneNumber = "010-1111-9111";
        LocalDateTime registeredAt = LocalDateTime.now();
//        LocalDateTime createdAt = LocalDateTime.now();
//        String createdBy = "AdminServer";



        /*
        1. User 의 인스턴스를 생성해야 한다.
        2. userRepository 는 하나이지만, User는 아주 많다.
         */

        User user = new User();

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);

        Assert.assertEquals(account, newUser.getAccount());
        Assert.assertEquals(newUser.getPassword(), password);
    }


    @Test
    @Transactional   // 테스트할 때만 error를 막기 위해서 넣어줘야 함, LAZY 때문???
    public void read() {

//        User user = userRepository.findByPhoneNumber("010-1111-2222");
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-5555");
        System.out.println(user);
        Assert.assertNotNull(user);

        user.getOrderGroupList()
            .forEach(orderGroup -> {
            System.out.println("-------------- 주문 묶음 ---------------");
            System.out.println("수령인 :  " + orderGroup.getRevName());
            System.out.println("수령지 :  " + orderGroup.getRevAddress());
            System.out.println("총금액 :  " + orderGroup.getTotalPrice());
            System.out.println("총수량 :  " + orderGroup.getTotalQuantity());
            System.out.println();
            System.out.println("-------------- 주문 상세 ---------------");

            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름 :  " + orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품 :  " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호: " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 :  " + orderDetail.getStatus());
                System.out.println("도착예정일자 :  " + orderDetail.getArrivalDate());
            });
        });


//        Optional<User> user = userRepository.findByAccount("TestUser02");

        /*
         Optional 을 쓰면
         없는 id가 입력되고, 아래와 같이 출력하면, "Optional.empty" 라고 출력 된다.
         System.out.println(user);

         Optional 을 쓰면, ifPresent() 메소드를 쓸 수 있다.
         ifPresent 문 + lambda 식!!!!!
         */

        /*
        user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                String itemName = detail.getItem().getName();
                String userAccount = detail.getUser().getAccount();
                System.out.println(userAccount + " 님께서 " + itemName + "을 구매하셨습니다.");
            });
        });
        */
    }
    @Test
    public void update(){

        User user = userRepository.findByPhoneNumber("010-1111-7777");

        Assert.assertNotNull(user);

//        user.setEmail("test10test10@gmail.com");

        user.setCreatedBy("Carpenter");
//        user.setUpdatedBy("Carpenter");

        User modifiedUser = userRepository.save(user);

        /*
        <Optional 을 썼을 때의 코드>

        Optional<User> user = userRepository.findFirstId(1L);


        Assert.assertTrue(user.isPresent()); // 어차피 ifPresent 로 체크할 건데?

        user.ifPresent(updateUser -> {
            // 생성되어 버린다.
//            updateUser.setId(5L);
            updateUser.setAccount("updatedUser01");
            updateUser.setPhoneNumber("02-763-8188");
            updateUser.setEmail("updatedmail@naver.com");

            User newUser = userRepository.save(updateUser);
        });
        */
    }

    @Test
    public void delete(){

        Optional<User> user = userRepository.findById(5L);

        Assert.assertTrue(user.isPresent());

        user.ifPresent(deleteUser -> {
            userRepository.delete(deleteUser);
        });

        Optional<User> deletedUser = userRepository.findById(5L);

        Assert.assertFalse(deletedUser.isPresent());
    }



}
