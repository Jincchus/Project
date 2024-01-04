package phoenix.partyquest.repository.party.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phoenix.partyquest.domain.party.location.PartyLocation;

import java.util.Optional;

public interface PartyLocationRepository extends JpaRepository<PartyLocation,Long> {
    @Query("select pl from PartyLocation pl where pl.locationName=:name")
    public Optional<PartyLocation> findPartyLocationByName(@Param("name") String name);
}
