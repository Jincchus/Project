package phoenix.partyquest.service.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.partyquest.domain.category.SmallCate;

@Getter@Setter
@NoArgsConstructor
public class SmallAPIService {

    private long id;
    private String smallName;

    public SmallAPIService(SmallCate smallCate){
        this.id = smallCate.getId();
        this.smallName=smallCate.getName();
    }
}
