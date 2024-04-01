package phoenix.partyquest.domain.party.project;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.party.Party;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("project")
public class Project extends Party {

}
