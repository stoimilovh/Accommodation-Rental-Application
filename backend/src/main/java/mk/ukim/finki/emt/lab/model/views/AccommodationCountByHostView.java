package mk.ukim.finki.emt.lab.model.views;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT host_id, accommodation_count FROM accommodation_counts_by_host")
@Immutable
public class  AccommodationCountByHostView {
    @Id
    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "accommodation_count")
    private Integer accommodationCount;
}


