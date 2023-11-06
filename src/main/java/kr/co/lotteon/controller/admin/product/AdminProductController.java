package kr.co.lotteon.controller.admin.product;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.request.admin.product.ProductCreateRequest;
import kr.co.lotteon.request.admin.product.ProductSearchFieldRequest;
import kr.co.lotteon.response.admin.product.PageInfoResponse;
import kr.co.lotteon.response.admin.product.ProductAdminListResponse;
import kr.co.lotteon.service.admin.product.ProductAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class AdminProductController {
    private final ProductAdminService productAdminService;

    @GetMapping("/register")
    public String productRegisterForm(@ModelAttribute("productInfo") ProductCreateRequest dto) {
        return "admin/product/register";
    }

    @PostMapping("/register")
    public String productRegister(@ModelAttribute("productInfo") ProductCreateRequest dto, HttpServletRequest request) throws IOException {
        dto.setIp(request.getRemoteAddr());
        productAdminService.registerProduct(dto);
        return "redirect:/admin/index";
    }

    /**
     * TODO: paging size 조절 하기 클라이언트에서 처리한다. => 상태유지는 어떻게 할 것인가
     */
    @GetMapping("/list")
    public String products(Model model, @ModelAttribute("searchCondField") ProductSearchFieldRequest searchCondField, @PageableDefault(sort = "prodNo", direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        log.info("[ADMIN PRODUCT LIST] searchCond : {}", searchCondField);
        Page<ProductAdminListResponse> results = productAdminService.getPagedProductsWithConds(searchCondField.toSearchCond(), pageable);
        List<ProductAdminListResponse> products = results.getContent();

        searchCondField.setPageSize(pageable.getPageSize());
        model.addAttribute("products", products);
        log.info("[ADMIN PRODUCT LIST] page info : {}", new PageInfoResponse(results.getTotalElements(), pageable));
        model.addAttribute("pageInfo", new PageInfoResponse(results.getTotalElements(), pageable));
        return "admin/product/list";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Integer productId) {
        productAdminService.deleteProduct(productId);
        return "redirect:/admin/product/list";
    }
}
