package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.views.AccommodationCountByHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccommodationCountByHostRepository extends JpaRepository<AccommodationCountByHostView, Long> {
    AccommodationCountByHostView findByHostId(Long hostId);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW accommodation_counts_by_host", nativeQuery = true)
    void refreshMaterializedView();

}