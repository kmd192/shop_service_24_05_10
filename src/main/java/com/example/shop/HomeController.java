package com.example.shop;

import com.example.shop.category.CategoryService;
import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public String home(String kw, @RequestParam(defaultValue = "NEW") String sortList, @RequestParam(defaultValue = "NOGENDER") String sortGender,
                       @RequestParam(defaultValue = "NOTYPE") String sortType, @RequestParam(defaultValue = "NOSEASON") String sortSeason,
                       Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Merchandise> paging = merchandiseService.getMerchandiseList(kw, page);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("sortList", sortList);
        model.addAttribute("sortGender", sortGender);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortSeason", sortSeason);

        return "home";
    }

}