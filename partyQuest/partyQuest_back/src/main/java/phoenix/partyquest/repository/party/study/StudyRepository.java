package phoenix.partyquest.repository.party.study;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phoenix.partyquest.domain.party.study.Study;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study,Long> ,StudyRepositoryCustom{
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Study s where s.id = :id")
    public Optional<Study> findStudyWithPessiLockById(@Param("id") Long id);

}
