package phoenix.partyquest.service.category;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.partyquest.domain.category.MajorCate;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MajorAPIService {

    private String majorName;
    private List<MiddleAPIService> middleCates;

    public MajorAPIService(MajorCate majorCate){
        this.majorName = majorCate.getName()+" "+majorCate.getId(); //TODO: 필드를 따로 빼서 아이디를 따로 내려보내자
        this.middleCates = majorCate.getMiddleCates().stream()
                .map(MiddleAPIService::new).collect(Collectors.toList());
    }
}
