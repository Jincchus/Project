package kr.co.lotteon.request.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartMultiDeleteRequest {
    private List<Integer> cartNos = new ArrayList<>();
}
