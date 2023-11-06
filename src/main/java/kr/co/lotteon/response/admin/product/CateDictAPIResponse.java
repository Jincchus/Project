package kr.co.lotteon.response.admin.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {"패션뷰티 1":[{"남자상의":pk}]}와 같이 최상단의 키의 맨 마지막에는 cate1의 pk를 달아준다.
 * cate1과 cate2를 조립하여 최종적으로 presentation layer에 반환할떄 사용한다.
 * author : 이동한
 */
@Getter
@Setter
@NoArgsConstructor
public class CateDictAPIResponse {
    private Map<String, List<Cate2DictAPIResponse>> data = new HashMap<>();

    public void addCate2(String cate1NameAndPk,Cate2DictAPIResponse cate2) {
        List<Cate2DictAPIResponse> targetList = data.getOrDefault(cate1NameAndPk, new ArrayList<>());
        targetList.add(cate2);
    }
}
