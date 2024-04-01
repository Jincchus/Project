package kr.co.lotteon.repository.product;

import kr.co.lotteon.entity.product.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderProgressRepository extends JpaRepository<ProductOrderEntity, Integer> {




}
