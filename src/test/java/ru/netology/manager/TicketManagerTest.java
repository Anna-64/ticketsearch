package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.rerository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private Ticket first = new Ticket(1, 1000, "VKO", "MMK", 180);
    private Ticket second = new Ticket(2, 6000, "MMK", "GSV", 360);
    private Ticket third = new Ticket(3, 3000, "KUF", "AER", 90);
    private Ticket fourth = new Ticket(4, 4000, "SGC", "VKO", 240);
    TicketManager manager = new TicketManager(repository);

    @Test
    public void shouldSort() {
        Ticket[] expected = new Ticket[]{first, third, fourth, second};
        Ticket[] actual = new Ticket[]{third, first, second, fourth};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndNoCoincidences() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("KUF", "MMK");


        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindCoincidences() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{fourth};
        Ticket[] actual = manager.searchBy("SGC", "VKO");


        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemove() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{first, third, fourth};
        Ticket[] actual = manager.removeByIdTicket(2);


        assertArrayEquals(expected, actual);
    }
}