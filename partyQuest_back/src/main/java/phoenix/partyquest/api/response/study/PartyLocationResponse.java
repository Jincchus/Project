package phoenix.partyquest.api.response.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.party.location.PartyLocation;

@Getter @NoArgsConstructor
public class PartyLocationResponse {
    private Long id;
    private String locationName;

    public PartyLocationResponse(PartyLocation partyLocation) {
        this.id = partyLocation.getId();
        this.locationName = partyLocation.getLocationName();
    }
}
