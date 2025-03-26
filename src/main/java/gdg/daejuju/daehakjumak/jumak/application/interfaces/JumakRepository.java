package gdg.daejuju.daehakjumak.jumak.application.interfaces;

import gdg.daejuju.daehakjumak.jumak.domain.Jumak;

public interface JumakRepository {
    Long save(Jumak jumak);

    Jumak findById(Long id);

    void updateJumakName(Long id, String jumakName);
}