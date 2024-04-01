package phoenix.partyquest.domain.party.location;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class PartyLocation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String locationName;
}
