package com.example.shop.merchandise;

import com.example.shop.category.CategoryService;
import com.example.shop.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;

    private final CategoryService categoryService;

    public Merchandise createMerchandise(String merchandiseName, long price, String size, String size2,
                                         String image, String gender, String clothType, String season,
                                         SiteUser seller) {
        if (size2.equals(" ")) {
            Merchandise merchandise = Merchandise.builder()
                    .merchandiseName(merchandiseName)
                    .price(price)
                    .size(size)
                    .image(image)
                    .category(categoryService.createCategory(gender, clothType, season))
                    .seller(seller)
                    .build();

            merchandiseRepository.save(merchandise);
            return merchandise;

        } else if (size.equals(" ")) {
            Merchandise merchandise = Merchandise.builder()
                    .merchandiseName(merchandiseName)
                    .price(price)
                    .size(size2)
                    .image(image)
                    .category(categoryService.createCategory(gender, clothType, season))
                    .seller(seller)
                    .build();

            merchandiseRepository.save(merchandise);
            return merchandise;
        }
        return null;
    }

    public void changeMerchandiseInfo(long id, String merchandiseName, long price,
                                      String size, String image){
        merchandiseRepository.save(merchandiseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no such data"))
                .changeMerchandiseInfoEntity(merchandiseName, price, size, image));
    }

    public Page<Merchandise> getMerchandiseList(String kw, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        if(kw == null || kw.trim().length() == 0){
            return merchandiseRepository.findAll(pageable);
        }

        return merchandiseRepository.findDistinctByMerchandiseNameContainsOrSeller_usernameContainsOrReviewList_reviewContainsOrReviewList_Reviewer_usernameContains
                (kw, kw, kw, kw, pageable);

    }
}
