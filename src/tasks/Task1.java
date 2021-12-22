package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

    // !!! Редактируйте этот метод !!!
    private List<Person> findOrderedPersons(List<Integer> personIds) {
        Set<Person> persons = PersonService.findPersons(personIds);
        Map<Integer, Person> orderedPersons = new LinkedHashMap<>();
        for (int id : personIds) {
            orderedPersons.put(id, null);
        }
        for (Person person : persons) {
            orderedPersons.put(person.getId(), person);
        }
        return new ArrayList<>(orderedPersons.values());
    }
//  Не выглядит элегантно, но O(n)

    @Override
    public boolean check() {
        List<Integer> ids = List.of(1, 2, 3);

        return findOrderedPersons(ids).stream()
                .map(Person::getId)
                .collect(Collectors.toList())
                .equals(ids);
    }

}
