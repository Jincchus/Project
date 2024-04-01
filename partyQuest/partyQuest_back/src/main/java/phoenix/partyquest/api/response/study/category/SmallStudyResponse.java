package phoenix.partyquest.api.response.study.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import phoenix.partyquest.domain.party.study.map.SmallStudyMap;

@ToString
@Getter
@NoArgsConstructor
public class SmallStudyResponse {
    private Long smallCateId;
    private String smallCateName;
    private Long studyId;

    public SmallStudyResponse(SmallStudyMap smallStudyMap) {
        this.smallCateId = smallStudyMap.getSmallCate().getId();
        this.smallCateName = smallStudyMap.getSmallCate().getName();
        this.studyId = smallStudyMap.getStudy().getId();
    }
}
