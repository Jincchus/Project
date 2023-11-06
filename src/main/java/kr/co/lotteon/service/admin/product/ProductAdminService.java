package kr.co.lotteon.service.admin.product;

import kr.co.lotteon.dto.UploadFileDto;
import kr.co.lotteon.entity.file.UploadFile;
import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductCate1Entity;
import kr.co.lotteon.entity.product.ProductCate2Entity;
import kr.co.lotteon.entity.product.ProductEntity;
import kr.co.lotteon.repository.admin.product.ProductCate1Repository;
import kr.co.lotteon.repository.admin.product.ProductCate2Repository;
import kr.co.lotteon.repository.admin.product.ProductRepository;
import kr.co.lotteon.repository.admin.product.presentation.ProductQueryRepository;
import kr.co.lotteon.repository.admin.product.presentation.ProductSearchCond;
import kr.co.lotteon.repository.member.MemberRepository;
import kr.co.lotteon.request.admin.product.ProductCreateRequest;
import kr.co.lotteon.response.admin.product.ProductAdminListResponse;
import kr.co.lotteon.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductAdminService {
    private final ProductQueryRepository productQueryRepository;
    private final ProductRepository productRepository;
    private final ProductCate1Repository cate1Repository;
    private final ProductCate2Repository cate2Repository;
    private final MemberRepository memberRepository;
    private final FileService fileService;

    @Transactional
    public ProductEntity registerProduct(ProductCreateRequest dto) throws IOException {
        ProductCate1Entity cate1 = cate1Repository.findById(dto.getProdCate1_id()).orElseThrow();
        ProductCate2Entity cate2 = cate2Repository.findById(dto.getProdCate2_id()).orElseThrow();
        Member seller = memberRepository.findById(dto.getSeller_id()).orElseThrow();


        UploadFileDto thumb1Dto = fileService.storeFile(dto.getThumb1());
        UploadFileDto thumb2Dto = fileService.storeFile(dto.getThumb2());
        UploadFileDto thumb3Dto = fileService.storeFile(dto.getThumb3());
        UploadFileDto detailDto = fileService.storeFile(dto.getDetail());

        UploadFile thumb1 = UploadFile.builder()
                .uploadFileName(thumb1Dto.getOrigFileName())
                .storedFileName(thumb1Dto.getStoredFileName())
                .build();
        UploadFile thumb2 = UploadFile.builder()
                .uploadFileName(thumb2Dto.getOrigFileName())
                .storedFileName(thumb2Dto.getStoredFileName())
                .build();
        UploadFile thumb3 = UploadFile.builder()
                .uploadFileName(thumb3Dto.getOrigFileName())
                .storedFileName(thumb3Dto.getStoredFileName())
                .build();
        UploadFile detail = UploadFile.builder()
                .uploadFileName(detailDto.getOrigFileName())
                .storedFileName(detailDto.getStoredFileName())
                .build();
        ProductEntity.ProductEntityBuilder productBuilder = dto.toEntityBuilder(cate1, cate2, seller);

        ProductEntity newProduct = productBuilder
                .thumb1(thumb1)
                .thumb2(thumb2)
                .thumb3(thumb3)
                .detail(detail)
                .build();
        productRepository.save(newProduct);
        return newProduct;
    }

    public List<ProductAdminListResponse> getProducts() {
        return productRepository.findAll().stream()
                .map(ProductAdminListResponse::new)
                .collect(Collectors.toList());
    }

    public Page<ProductAdminListResponse> getPagedProductsWithConds(ProductSearchCond searchCond, Pageable pageable) {
        return productQueryRepository.searchWithPageAndCond(searchCond, pageable);
    }

    public int deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        return productId;
    }
}
