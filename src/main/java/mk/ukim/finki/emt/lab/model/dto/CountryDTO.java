package mk.ukim.finki.emt.lab.model.dto;


public class CountryDTO {
    private String name;
    private String continent;

    public CountryDTO() {}

    public CountryDTO(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
