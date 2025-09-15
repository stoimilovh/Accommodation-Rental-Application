package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDTO(Long id, String name, String surname, Long country) {

    public static DisplayHostDTO from(Host host) {
        return new DisplayHostDTO(host.getId(),
                host.getName(),
                host.getSurname(),
                host.getCountry().getId()
        );
    }

    public static List<DisplayHostDTO> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDTO::from).collect(Collectors.toList());
    }
}