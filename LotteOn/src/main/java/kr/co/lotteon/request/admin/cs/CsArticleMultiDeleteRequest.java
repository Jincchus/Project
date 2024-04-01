package kr.co.lotteon.request.admin.cs;

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
public class CsArticleMultiDeleteRequest {
    private List<Integer> articleIds = new ArrayList<>();

}
