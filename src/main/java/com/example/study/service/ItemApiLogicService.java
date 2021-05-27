package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        // 1. request 로 부터 데이터를 받는다.

        ItemApiRequest body = request.getData();

        // 2. 받은 데이터로 user 생성(builder 이용)
        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId())) // 이부분이 중요함!!!!!!!
                .build();

        Item newItem = itemRepository.save(item);

        return response(newItem);

    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터가 없습니다."));


        /*
        Optional<Item> optional = itemRepository.findById(id);

        return optional.map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터가 없습니다."));

        */
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        ItemApiRequest body = request.getData();
        return itemRepository.findById(body.getId())
                .map(item -> item.setName(body.getName())
                                    .setStatus(body.getStatus())
                                    .setTitle(body.getTitle())
                                    .setContent(body.getContent())
                                    .setPrice(body.getPrice())
                                    .setBrandName(body.getBrandName())
                                    .setRegisteredAt(body.getRegisteredAt())
                                    .setUnregisteredAt(body.getUnregisteredAt()))
                .map(newItem -> itemRepository.save(newItem))
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터가 없습니다."));
    }

    @Override
    public Header delete(Long id) {
        return itemRepository.findById(id)
                .map(item -> {
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터가 없습니다"));
    }

    public Header<ItemApiResponse> response(Item item) {
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .status(item.getStatus())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();


        return Header.OK(body);
    }
}
