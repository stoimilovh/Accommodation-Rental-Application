package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateHostDTO(String name, String surname, Long country) {

    public static CreateHostDTO from(Host host) {
        return new CreateHostDTO(
                host.getName(),
                host.getSurname(),
                host.getCountry().getId()
        );
    }

    public static List<CreateHostDTO> from(List<Host> hosts) {
        return hosts.stream().map(CreateHostDTO::from).collect(Collectors.toList());
    }

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
}
