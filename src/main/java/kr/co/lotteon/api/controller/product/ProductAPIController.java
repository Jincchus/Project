package kr.co.lotteon.api.controller.product;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.dto.product.ProductDTO;
import kr.co.lotteon.request.product.ProductOrderItemRequest;
import kr.co.lotteon.response.admin.product.Cate1DictAPIResponse;
import kr.co.lotteon.response.product.ProductCartResponse;
import kr.co.lotteon.response.product.ProductOrderResponse;
import kr.co.lotteon.service.admin.product.CateAPIService;
import kr.co.lotteon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductAPIController {
    //카테고리 1차,2차 값 가져오기
    private final CateAPIService cateAPIService;
    private final ProductService productService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/fullCate")
    public List<Cate1DictAPIResponse> test() {
        return cateAPIService.getCateDictResponse();
    }

    /**
     * product 이미지를 요청 받으면 이미지를 준다.
     */
    @GetMapping("/images/{storedName}")
    public ResponseEntity<Resource> getProductImage(@PathVariable("storedName") String storedName) throws IOException {
        String imgPathStr = String.format("%s%s", fileDir, storedName);
        FileSystemResource imgResource = new FileSystemResource(imgPathStr);
        HttpHeaders httpHeaders = new HttpHeaders();

        //content-type 등록
        //Path imgPath = Paths.get(imgPathStr);
        //String contentType = Files.probeContentType(imgPath);
        httpHeaders.add("Content-Type", "image/jpeg");

        return new ResponseEntity<Resource>(imgResource, httpHeaders, HttpStatus.OK);
    }
//  /product/cart에서 넘어오는 products를 받음
//    @PostMapping("/cart_to_order")
////    public List<ProductDTO> cartToOrder(@RequestBody ProductOrderItemRequest productOrderItemRequest) {
////    ajax로 내보내는 정보를 product/order에서 받기 위해서 product/order 주소로 보내주기 위해서 String으로 매서드를 변경해주고, return에 주소를 추가할 예정
//    public String cartToOrder(@RequestBody ProductOrderItemRequest productOrderItemRequest, RedirectAttributes rttr) {
//        log.info("[CART TO ORDER] order request : {}", Arrays.toString(productOrderItemRequest.getProducts().stream().toArray()));
//        List<ProductDTO> products = productOrderItemRequest.getProducts();
//        log.info("cart_to_order of  ProductAPIController :"+products.toString());
//        rttr.addAttribute("products",products);
//        //noti: redirectAttribute를 통해서 데이터를 내보내야함
//        return "redirect:/product/order";
//    }
//  /product/cart에서 넘어오는 products를 받음
    @PostMapping("/selectCartDelete")
    public List<ProductDTO> cartToOrder(@RequestBody ProductOrderItemRequest productOrderItemRequest) {
//    ajax로 내보내는 정보를 product/order에서 받기 위해서 product/order 주소로 보내주기 위해서 String으로 매서드를 변경해주고, return에 주소를 추가할 예정
        log.info("[CART TO ORDER] order request : {}", Arrays.toString(productOrderItemRequest.getProducts().stream().toArray()));
        List<ProductDTO> products = productOrderItemRequest.getProducts();
        log.info("cart_to_order of  ProductAPIController :"+products.toString());
        //noti: redirectAttribute를 통해서 데이터를 내보내야함
        return products;
    }
}
