package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.rerository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;


    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void saveTicket(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] searchBy(String from, String to) {
        Ticket[] result = new Ticket[0]; // тут будем хранить подошедшие запросу продукты
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }


    public boolean matches(Ticket ticket, String departureAirport, String arrivalAirport) {
        if (ticket.getArrivalAirport().contains(arrivalAirport) && ticket.getDepartureAirport().contains(departureAirport)) {
            return true;
        } else {
            return false;
        }
    }

    public Ticket[] removeByIdTicket(int id) {
        return repository.removeById(id);
    }
}
