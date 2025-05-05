package mk.ukim.finki.emt.lab.listeners;


import mk.ukim.finki.emt.lab.events.HostChangedEvent;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {

    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostChanged(HostChangedEvent event) {
        this.hostService.refreshMaterializedView();
    }
}