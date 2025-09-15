package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.views.HostsByCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HostsByCountryRepository extends JpaRepository<HostsByCountryView, String> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW hosts_by_country", nativeQuery = true)
    void refreshMaterializedView();
}
