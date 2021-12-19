package tasks;

import common.Area;
import common.Person;
import common.Task;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 implements Task {

    private Set<String> getPersonDescriptions(Collection<Person> persons,
                                              Map<Integer, Set<Integer>> personAreaIds,
                                              Collection<Area> areas) {
/*
10 минут чтобы написать этот вариант и 3 дня, чтобы сделать это через stream
        Set<String> result = new HashSet<>();
        for (Person person : persons) {
            Set<Integer> areaIds = personAreaIds.get(person.getId());
            for (int id : areaIds) {
                for (Area area : areas) {
                    if (area.getId() == id) {
                        result.add(person.getFirstName() + " - " + area.getName());
                    }
                }
            }
        }
        return result;
*/

        return new HashSet<>(persons).stream()
                .flatMap(person -> personAreaIds.get(person.getId()).stream()
                        .flatMap(areaId -> areas.stream() //здесь по моей логике должен быть просто map, поскольку для каждого занчения areId мэппится одно значение string, но без flatmap не работает
                                .filter(area -> area.getId().equals(areaId))
                                .map(area -> person.getFirstName() + " - " + area.getName())))
                .collect(Collectors.toSet());

    }

    @Override
    public boolean check() {
        List<Person> persons = List.of(
                new Person(1, "Oleg", Instant.now()),
                new Person(2, "Vasya", Instant.now())
        );
        Map<Integer, Set<Integer>> personAreaIds = Map.of(1, Set.of(1, 2), 2, Set.of(2, 3));
        List<Area> areas = List.of(new Area(1, "Moscow"), new Area(2, "Spb"), new Area(3, "Ivanovo"));
        return getPersonDescriptions(persons, personAreaIds, areas)
                .equals(Set.of("Oleg - Moscow", "Oleg - Spb", "Vasya - Spb", "Vasya - Ivanovo"));
    }
}
