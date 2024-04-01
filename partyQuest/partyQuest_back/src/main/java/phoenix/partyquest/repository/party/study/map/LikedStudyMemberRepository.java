package phoenix.partyquest.repository.party.study.map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;

import java.util.List;

public interface LikedStudyMemberRepository extends JpaRepository<LikedStudyMemberMap, Long> {

    @Query("select lsm from LikedStudyMemberMap lsm where lsm.study.id = :studyId")
    public List<LikedStudyMemberMap> findByStudyId(@Param("studyId") Long studyId);
}
