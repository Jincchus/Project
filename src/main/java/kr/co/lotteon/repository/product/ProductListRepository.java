package kr.co.lotteon.repository.product;

import kr.co.lotteon.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductListRepository extends JpaRepository<ProductEntity,Integer> {
    @Query("select p from ProductEntity p join fetch p.seller s join fetch p.prodCate2 ct where ct.cate2 = :cate2 order" +
            "" +
            " by p.rdate desc ,p.prodNo desc ")
    public List<ProductEntity> findByCate2(@Param("cate2") Integer cate2);
}
