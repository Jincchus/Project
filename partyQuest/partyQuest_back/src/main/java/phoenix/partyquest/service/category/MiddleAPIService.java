package phoenix.partyquest.service.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.partyquest.domain.category.MiddleCate;
import phoenix.partyquest.domain.category.SmallCate;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MiddleAPIService {
    private String middleName; //middleName +pk 값 맨 뒤에 붙여서 pk를 같이 실어서 보낸다.
    private List<SmallAPIService> smallCates;

    public MiddleAPIService(MiddleCate middleCate){
        this.middleName = middleCate.getName()+" "+middleCate.getId();
        this.smallCates = middleCate.getSmallCates().stream().map(SmallAPIService::new).collect(Collectors.toList());
    }
}
