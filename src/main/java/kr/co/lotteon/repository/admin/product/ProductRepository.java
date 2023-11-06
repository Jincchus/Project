package kr.co.lotteon.repository.admin.product;

import kr.co.lotteon.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
}
