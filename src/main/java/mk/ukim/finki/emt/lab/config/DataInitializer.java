package mk.ukim.finki.emt.lab.config;

import mk.ukim.finki.emt.lab.model.domain.*;
import mk.ukim.finki.emt.lab.repository.*;
import mk.ukim.finki.emt.lab.service.application.TemporaryReservationApplicationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRepository accommodationRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TemporaryReservationRepository temporaryReservationRepository;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, AccommodationRepository accommodationRepository, ReviewRepository reviewRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, TemporaryReservationRepository temporaryReservationRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.temporaryReservationRepository = temporaryReservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User ss = null;
        User hs = null;
        Accommodation acc1 = null;
        Accommodation acc2 = null;
        Accommodation acc3 = null;

        if(userRepository.count() == 0) {
            ss = new User("sstoimilov", passwordEncoder.encode("sstoimilov"), "Simon", "Stoimilov", Role.ROLE_HOST);
            hs = new User("hstoimilov", passwordEncoder.encode("hstoimilov"), "Hristijan", "Stoimilov", Role.ROLE_USER);

            List<User> users = List.of(ss, hs);
            this.userRepository.saveAll(users);

        }
        if (countryRepository.count() == 0) {
            Country mk = countryRepository.save(new Country("North Macedonia", "Europe"));
            Country at = countryRepository.save(new Country("Austria", "Europe"));
            Country sr = countryRepository.save(new Country("Serbia", "Europe"));
            Country gr = countryRepository.save(new Country("Greece", "Europe"));

            Host host1 = hostRepository.save(new Host("Hristijan", "Stoimilov", mk));
            Host host2 = hostRepository.save(new Host("Branko", "Milenkov", gr));
            Host host3 = hostRepository.save(new Host("Nikola", "Serafimov", sr));


            acc1 = accommodationRepository.save(new Accommodation("Skopje Apartment", Category.APARTMENT, host1, 2, true));
            acc2 = accommodationRepository.save(new Accommodation("Vienna Central Hotel", Category.HOTEL, host2, 4, false));
            acc3 = accommodationRepository.save(new Accommodation("Views", Category.HOUSE, host3, 5, true));
            reviewRepository.save(new Review("Very cozy and clean!", 5, acc1));
            reviewRepository.save(new Review("Great location, would visit again.", 4, acc1));
            reviewRepository.save(new Review("Excellent service and amenities.", 5, acc2));
            reviewRepository.save(new Review("Room was a bit noisy, but ok.", 3, acc3));

        }
        if (temporaryReservationRepository.count() == 0) {
            TemporaryReservation reservation1 = new TemporaryReservation(hs);
            reservation1.getAccommodations().add(acc1);

            TemporaryReservation reservation2 = new TemporaryReservation(hs);
            reservation2.getAccommodations().add(acc3);

            temporaryReservationRepository.save(reservation1);
            temporaryReservationRepository.save(reservation2);
        }
    }
}