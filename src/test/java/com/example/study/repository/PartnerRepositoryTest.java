package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Partner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class PartnerRepositoryTest extends StudyApplicationTests {

    // 싱글톤
    @Autowired
    PartnerRepository partnerRepository;

    // CRUD

    @Test
    public void create () {

        String name = "Partner01";
        String status = "Registered";
        String address = "서울시 강남구";
        String callCenter = "070-1111-1111";
        String partnerNumber = "010-1111-1111";
        String businessNumber = "123456780123";
        String ceoName = "홍길동";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
//        Long categoryId = 1L;

        Partner partner  = new Partner();

        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
//        partner.setCategoryId(categoryId);

        // 이 부분이 잘 이해가 안 됨. save 는 객체자체를 리턴하나보다...
        Partner newPartner = partnerRepository.save(partner);

        Assert.assertNotNull(newPartner);

        Assert.assertEquals(newPartner.getName(), name);
    }

    @Test
    public void read () {
        Optional<Partner> partner = partnerRepository.findByName("Partner01");
        Assert.assertNotNull(partner);
    }

    @Test
    public void update () {

        // Optional -> ifPresent -> 람다식
        // Optional 로 지정한 것은 getter, setter 바로 접근할 수 없다.

        Optional<Partner> partner = partnerRepository.findByName("Partner01");

        Assert.assertTrue(partner.isPresent());

        partner.ifPresent(u ->{

            u.setStatus("REGISTERED");

            u.setRegisteredAt(LocalDateTime.now());

            u.setUpdatedAt(LocalDateTime.now());

            Partner updatedPartner = partnerRepository.save(u);

            System.out.println(updatedPartner);
        });



    }


    @Test
    public void delete () {


    }




}
