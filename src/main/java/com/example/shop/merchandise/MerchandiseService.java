package com.example.shop.merchandise;

import com.example.shop.category.Category;
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
                    .category(categoryService.findCategory(gender, clothType, season))
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
                    .category(categoryService.findCategory(gender, clothType, season))
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

    public Page<Merchandise> getMerchandiseList(String sortList, String sortGender, String sortType,
                                                String sortSeason, String kw, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        Category category = categoryService.findCategory(sortGender, sortType, sortSeason);

        if (sortList.equals("NEW")){
            sorts.add(Sort.Order.desc("id"));
        } else if(sortList.equals("OLD")){
            sorts.add(Sort.Order.asc("id"));
        } else if(sortList.equals("CHEAP")){
            sorts.add(Sort.Order.asc("price"));
        } else if(sortList.equals("EXPENSIVE")){
            sorts.add(Sort.Order.desc("price"));
        } else if(sortList.equals("LIKE")){
            sorts.add(Sort.Order.asc("like"));
        }

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        if(kw.trim().isEmpty() && category != null){
            return merchandiseRepository.findByCategory(category, pageable);
        } else if (kw.trim().isEmpty() && category == null) {
            return merchandiseRepository.findAll(pageable);
        }

        if(category != null) {
            return merchandiseRepository.findDistinctByCategoryAndMerchandiseNameContainsOrSeller_usernameContainsOrReviewList_reviewContainsOrReviewList_Reviewer_usernameContains
                    (category, kw, kw, kw, kw, pageable);
        } else {
            return merchandiseRepository.findDistinctByMerchandiseNameContainsOrSeller_usernameContainsOrReviewList_reviewContainsOrReviewList_Reviewer_usernameContains
                    (kw, kw, kw, kw, pageable);
        }

    }
}
