package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends StudyApplicationTests {

    // 싱글톤패턴
    @Autowired
    private CategoryRepository categoryRepository;

    // CRUD
    @Test
    public void create() {

        /*
         1. 데이터 입력 받기
         2. 인스턴스 생성
         3. 입력값 set 하기
         4. Repository 에  save 하기
         5. Assert 로 테스트 하기
               - Assert.assertNotNull(a);
               - Assert.assertEquals(a.getB, b)
         */


        // 1.
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        // 2.
        Category category = new Category();

        // 3.
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        // 4.
        Category newCategory = categoryRepository.save(category);

        // 5.
        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(), type);
        Assert.assertEquals(newCategory.getTitle(), title);

    }


    @Test
    public void read () {
         /*
         1. 검색조건으로 있는지 없는지 체크할 것 *****************
          a. findById() 는 기본 메소드임
          b. 나머지는 CategoryRepository 에서 생성을 해 주어야
          */

        // 아이디로 검색
//        Optional<Category> optionalCategory = categoryRepository.findById(1L);
        // 타입으로 검색: 대소문자 구분됨
        String type = "COMPUTER";

        Optional<Category> optionalCategory = categoryRepository.findByType(type);
        // 타이틀로 검색
//        Optional<Category> optionalCategory = categoryRepository.findByTitle("컴퓨터");


        // 2. 찾았으면 optional. ifPresent(람다식) -> Assert 사용 검색조건과 비교 -> 구현
        optionalCategory.ifPresent(c -> {

            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
            System.out.println(c.getCreatedAt());
            System.out.println(c.getCreatedBy());
        });

    }

    @Test
    public void update() {
        // 1. 검색할 내용
        String type = "COMPUTER";

        // 2. optional 로 찾기
        Optional<Category> category = categoryRepository.findByType(type);

        Assert.assertTrue(category.isPresent());
        // -> Assert.assertTrue() 는 조건에 맞지 않으면 예외를 발생시켜 프로그램을 중단시킨다.
        // 실제 프로그램에서는 빼야 할 듯 하다...

        // 3. ifPresent 로 구현하기(내부에서, assertEquals를 비교)
        category.ifPresent(c ->{

            Assert.assertEquals(type, c.getType());

            String createdBy = "난 나야";

            c.setCreatedBy(createdBy);

            // save 는 업데이트 혹은 생성한 것을 저장하면서 객체를 리턴한다.
            Category updatedCategory = categoryRepository.save(c);

            System.out.println(updatedCategory);
        });

    }
    @Test
    public void delete() {

        // 1. 검색하기
        String type = "COMPUTER";
        Optional<Category> category = categoryRepository.findByType(type);
        System.out.println(category);
        // 2. ifPresent
        category.ifPresent(c -> {

            categoryRepository.delete(c);
        });

        // delete 새로운 객체를 생성해서 있는지 없는지 생성해야 함
        Optional<Category> deletedCategory = categoryRepository.findByTitle(type);

//        Assert.assertTrue(deletedCategory.isPresent());
    }

}