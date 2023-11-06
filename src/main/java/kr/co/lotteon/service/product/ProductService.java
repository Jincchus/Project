package kr.co.lotteon.service.product;

import kr.co.lotteon.dto.product.ProductDTO;
import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductCartEntity;
import kr.co.lotteon.entity.product.ProductEntity;
import kr.co.lotteon.entity.product.ProductOrderEntity;
import kr.co.lotteon.entity.product.ProductOrderItemEntity;
import kr.co.lotteon.repository.admin.product.ProductRepository;
import kr.co.lotteon.repository.admin.product.presentation.ProductQueryRepository;
import kr.co.lotteon.repository.admin.product.presentation.ProductSearchCond;
import kr.co.lotteon.repository.member.MemberRepository;
import kr.co.lotteon.repository.product.*;
import kr.co.lotteon.repository.product.presentation.ProductQueryRepository2;
import kr.co.lotteon.repository.product.presentation.ProductSearchCond2;
import kr.co.lotteon.request.product.ProductCartRequest;
import kr.co.lotteon.response.admin.product.ProductAdminListResponse;
import kr.co.lotteon.request.product.ProductOrderRequest;
import kr.co.lotteon.response.product.ProductCartResponse;
import kr.co.lotteon.response.product.ProductListResponse;
import kr.co.lotteon.response.product.ProductOrderItemResponse;
import kr.co.lotteon.response.product.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductQueryRepository productQueryRepository;
    private final ProductQueryRepository productQueryRepository2;
    private final ProductListRepository productListRepository;
    private final ProductViewRepository productViewRepository;
    private final ProductCartRepository productCartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final ProductOrderProgressRepository orderProgressRepository;
    private final ProductOrderItemRepository orderItemRepository;

    /* Product List */
    public List<ProductListResponse> findProductsByCate2(Integer prodCate2Id){


        List<ProductListResponse> productList = productListRepository.findByCate2(prodCate2Id).stream()
                .map(ProductListResponse::new)
                .collect(Collectors.toList());

        log.info("productList : " + productList);
        return productList;
    }

    /* Product View */
    public ProductViewResponse findView(Integer prodNo){
        ProductEntity productEntity = productViewRepository.findById(prodNo).orElseThrow();
        return new ProductViewResponse(productEntity);
    }

    /* Product Cart */
    @Transactional
    public void insertCart(ProductCartRequest productCartRequest) throws IOException {
        //TODO: prodNO에 해당하는 cart를 findMember가 이미 가지고 있으면
        //TODO: distinct로 member-cart join된 결과 덩치 줄이기(최적화)
        //TODO: dirtychecking, EntityManager , dto와  entity의 필드명은 웬만하면 같게,
        log.info("[PRODUCT SERVICE] insertCart called, uid = {}", productCartRequest.getUid());
        ProductEntity product = productRepository.findById(productCartRequest.getProdNo()).orElseThrow();
        Member findMember = memberRepository.findMemberByIdWithCarts(productCartRequest.getUid()).orElseThrow();
        //prodNo에 해당하는 cart가 있는지 확인
        //prodNo에 해당하는 cart가 있으면 업데이트 해준다.
        List<ProductCartEntity> carts = findMember.getCarts();
        log.info("[CART SERVICE INSERTCART CALLED] user {}'s cart size = {}", findMember.getUid(), carts.size());

        int checkProdNo = productCartRequest.getProdNo();
        for (ProductCartEntity cart: carts) {
            ProductEntity curProdNo = cart.getProduct();
            if (curProdNo.getProdNo() == checkProdNo) {
                cart.setCount(cart.getCount()+productCartRequest.getCount());
                cart.setPrice(cart.getPrice());
                cart.setPoint(cart.getPoint());
                cart.setTotal(cart.getTotal()+productCartRequest.getTotal());
                return; // 업데이트 완료
            }
        }
        //prodNo에 해당하는 cart가 없으면 새로 만들어준다.
        ProductCartEntity newCart = productCartRequest.toEntity(product, findMember);
        findMember.addCart(newCart);
    }

    public List<ProductCartResponse> findCart(String uid){
        List<ProductCartResponse> productCartList = new ArrayList<>();
        productCartList = productCartRepository.findCartById(uid).stream()
                .map(ProductCartResponse::new)
                .collect(Collectors.toList());

        log.info("productCartList : " + productCartList);
        return productCartList;
    }

    public void insertOrderItem(){

    }

    public Page<ProductAdminListResponse> getPagedProductsWithConds(ProductSearchCond searchCond, Pageable pageable) {
        return productQueryRepository.searchWithPageAndCond(searchCond, pageable);
    }
    //cart에 들어있는 상품을 order로 보내는 repository
    public ProductCartResponse productOrderList(int cartNo){
        return productCartRepository.findCartByCartId(cartNo);
    }

    @Transactional
    public List<ProductOrderItemResponse> productOrderProgress(ProductOrderRequest orderRequest,String[]cartNo){
        log.info("proService : "+orderRequest.toString());
        List<ProductOrderItemResponse> orderItems = new ArrayList<>();

        //ordNo 받기
        ProductOrderEntity ordNo = orderProgressRepository.save(modelMapper.map(orderRequest, ProductOrderEntity.class));

        //회원정보 가져오기
        Member findMember = memberRepository.findById(orderRequest.getOrdUid()).orElseThrow();
        log.info("findMember : "+findMember);
        //point변동사항 입력
        findMember.setPoint(findMember.getPoint() +ordNo.getSavePoint()-ordNo.getUsedPoint());
        //불려온 맴버의 원래 포인트 + 적립 포인트 - 사용한 포인트
        memberRepository.save(findMember);

        for(String no : cartNo){

            //cart정보 받아오기
            ProductCartResponse cartResponse = productCartRepository.findCartByCartId(Integer.parseInt(no));
            //제품정보 받아오기
            ProductEntity product = productRepository.findById(cartResponse.getProdNo()).orElseThrow();
            //cart정보를 토대로 OrderItemEntity 가져오기
            ProductOrderItemEntity orderItem = cartResponse.toOrderItemEntity(ordNo);

            //complete에 내보낼 물건의 정보 저장
             ProductOrderItemResponse productOrderItemResponse  =  new ProductOrderItemResponse(cartResponse);
             log.info("productOrderItemResponse :"+productOrderItemResponse);
            orderItems.add(productOrderItemResponse);

             //orderItem 테이블에 정보 입력
            orderItemRepository.save(orderItem);

            //카트에 들어있는 상품 제거
            productCartRepository.deleteById(Integer.parseInt(no));
        }

        return  orderItems;
    }


//      public Page<ProductListResponse> getPagedProductsWithConds(ProductSearchCond2 searchCond2, Pageable pageable){
//        return ProductQueryRepository2.searchWithPageAndCond(searchCond2, pageable);
//        }

    public void deleteCart(int cartNo){
        productCartRepository.deleteById(cartNo);
    }
}
