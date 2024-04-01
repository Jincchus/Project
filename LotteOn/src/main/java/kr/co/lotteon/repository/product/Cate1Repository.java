package kr.co.lotteon.repository.product;


import kr.co.lotteon.entity.product.ProductCate1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cate1Repository extends JpaRepository<ProductCate1Entity, Integer> {
    @Query("select distinct c1 from ProductCate1Entity c1 join fetch c1.cate2s")
    public List<ProductCate1Entity> getCate1WithChildrenCate2();
}
