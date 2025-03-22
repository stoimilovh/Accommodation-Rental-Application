package mk.ukim.finki.emt.lab.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import mk.ukim.finki.emt.lab.model.Country;

public class HostDTO {

    private String name;
    private String surname;
    private Long country;

    public HostDTO() {
    }

    public HostDTO(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
