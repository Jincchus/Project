package phoenix.partyquest.repository.party.study.map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phoenix.partyquest.domain.party.study.StudyMember;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface StudyMemberRepository extends JpaRepository<StudyMember,Long> {

}
