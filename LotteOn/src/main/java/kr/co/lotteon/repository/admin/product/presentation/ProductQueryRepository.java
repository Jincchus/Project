package kr.co.lotteon.repository.admin.product.presentation;

import kr.co.lotteon.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//LEARN: customIMPL은 제외하고 인터페이스만 보자
public interface ProductQueryRepository extends JpaRepository<ProductEntity,Integer>,ProductQueryRepositoryCustom {
}
