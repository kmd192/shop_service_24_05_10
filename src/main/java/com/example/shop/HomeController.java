package com.example.shop;

import com.example.shop.category.CategoryService;
import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MerchandiseService merchandiseService;

    private final CategoryService categoryService;

    @GetMapping("/")
    public String root(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(@RequestParam(required = false, defaultValue = "") String kw, @RequestParam(required = false, defaultValue = "NEW") String sortList,
                               @RequestParam(required = false) String sortGender, @RequestParam(required = false) String sortType,
                               @RequestParam(required = false) String sortSeason, Model model, @RequestParam(value = "page", defaultValue = "0") int page){

        Page<Merchandise> paging = merchandiseService.getMerchandiseList(sortList, sortGender, sortType,
                sortSeason, kw, page);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("sortList", sortList);
        model.addAttribute("sortGender", sortGender);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortSeason", sortSeason);

        return ResponseEntity.ok("요청수신 정상처리되었음.");
    }

}