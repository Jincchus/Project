package kr.co.lotteon.repository.product;

import kr.co.lotteon.entity.product.ProductCartEntity;
import kr.co.lotteon.response.product.ProductCartResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCartEntity,Integer> {
    @Query("select c from ProductCartEntity c " +
            "join fetch c.uid u " +
            "join fetch c.product p " +
            "where u.uid = :uid order by c.cartNo DESC")
    List<ProductCartEntity> findCartById(@Param("uid") String uid);

    @Query("select c from  ProductCartEntity c  " +
            "join fetch  c.product p  " +
            "where c.cartNo = :cartNo")
     ProductCartResponse findCartByCartId(@Param("cartNo") int cartNo);

}
