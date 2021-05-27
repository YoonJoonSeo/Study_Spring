package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    // CRUD

    @Test
    public void create() {

        Item item = new Item();

        item.setStatus("UNREGISTERED");
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북입니다");
        item.setPrice(BigDecimal.valueOf(900000));
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
//        item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);

        // NotNull 이면 error 가 나지 않는다. delete 와는 다르다.
        Assert.assertNotNull(newItem);
    }

    @Test
    public void read() {
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);
        Assert.assertTrue(item.isPresent());

        item.ifPresent(i -> System.out.println(i));
    }

    @Test
    public void update() {

        Optional<Item> updateItem = itemRepository.findById(1L);

        Assert.assertTrue(updateItem.isPresent());

        System.out.println("룰루랄라");
        updateItem.ifPresent(thisItem -> {
            thisItem.setName("노트북");
//            thisItem.setPrice(120000);
//            thisItem.setContent("애플컴퓨터");
            itemRepository.save(thisItem);
        });
    }
    public void delete() {
        Optional<Item> item = itemRepository.findById(1L);

        Assert.assertTrue(item.isPresent());

        item.ifPresent(i -> itemRepository.delete(i));

        Optional<Item> deletedItem = itemRepository.findById(1l);

        Assert.assertFalse(deletedItem.isPresent());
    }

}
