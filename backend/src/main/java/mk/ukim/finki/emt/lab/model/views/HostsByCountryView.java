package mk.ukim.finki.emt.lab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT country_id, host_count FROM hosts_by_country")
@Immutable
public class HostsByCountryView {

    @Id
    @Column(name = "country_id")
    private String country;

    @Column(name = "host_count")
    private Integer hostCount;
}
