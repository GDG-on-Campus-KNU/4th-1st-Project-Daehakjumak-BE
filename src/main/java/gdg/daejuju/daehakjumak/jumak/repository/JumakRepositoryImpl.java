package gdg.daejuju.daehakjumak.jumak.repository;

import gdg.daejuju.daehakjumak.common.domain.exception.ErrorCode;
import gdg.daejuju.daehakjumak.common.domain.exception.NotFoundException;
import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.jumak.repository.entity.JumakEntity;
import gdg.daejuju.daehakjumak.jumak.repository.jpa.JpaJumakRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JumakRepositoryImpl implements JumakRepository {

    private final JpaJumakRepository jpaJumakRepository;

    @Override
    public Long save(Jumak jumak) {
        JumakEntity savedJumakEntity = jpaJumakRepository.save(new JumakEntity(jumak));
        return savedJumakEntity.getId();
    }

    @Override
    public Jumak findById(Long id) {
        return jpaJumakRepository.findById(id).map(JumakEntity::toJumak).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public void updateJumakName(Long id, String jumakName) {
        jpaJumakRepository.updateJumakName(id,jumakName);
    }

    @Override
    public boolean isAcessibleByUser(Long jumakId, String userId) {
        return jpaJumakRepository.existsByIdAndUserId(jumakId, userId);
    }
}
