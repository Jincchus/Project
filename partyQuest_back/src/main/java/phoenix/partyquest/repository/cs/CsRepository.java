package phoenix.partyquest.repository.cs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import phoenix.partyquest.domain.cs.Board;

@Repository
public interface CsRepository extends JpaRepository<Board,Long> {
    @Query("select b from Board b left join fetch b.boardCate bbc join fetch b.boardMenu bbm join fetch b.writer bw where bbc.id = :cateId")
    public Page<Board> findByCate(Long cateId, Pageable pageable);
}
