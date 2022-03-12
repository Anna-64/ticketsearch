package ru.netology.rerository;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Ticket;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class TicketRepository {
    private Ticket[] items = new Ticket[0];

    public void save(Ticket item) { //сохраняем
        int length = items.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Ticket[] findAll() { //возвращает массив продуктов
        return items;
    }

    public Ticket findById(int id) { //находит продукт по id
        for (Ticket item : items) {
            if (item.getId() == id) {
                return item;//product
            }
        }
        return null;
    }

    public Ticket[] removeById(int id) { // удаляет по id
        int length = items.length - 1;
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
        return tmp;
    }
}

