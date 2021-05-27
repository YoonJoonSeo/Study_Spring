package com.example.study.repository;

import com.example.study.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 인터페이스이므로, type 과 title 로 검색하는 메소드를 적어 주면 된다.
    // 구현은 저절로 되어 메소드가 추가 된다.
    Optional<Category> findByType(String type);
    Optional<Category> findByTitle(String Title);
}
