package com.example.shop.merchandise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;

    public Merchandise createMerchandise(String merchandiseName, long price,
                                         String size, String image){
        Merchandise merchandise = Merchandise.builder()
                .merchandiseName(merchandiseName)
                .price(price)
                .size(size)
                .image(image)
                .build();

        merchandiseRepository.save(merchandise);

        return merchandise;
    }

    public void changeMerchandiseInfo(long id, String merchandiseName, long price,
                                      String size, String image){
        merchandiseRepository.save(merchandiseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no such data"))
                .changeMerchandiseInfoEntity(merchandiseName, price, size, image));
    }
}
