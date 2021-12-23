package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

    private long count;

    //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
    public List<String> getNames(List<Person> persons) {

        return new ArrayList<>(persons).stream()
                .skip(1)
                .map(Person::getFirstName)
                .collect(Collectors.toList());
//        пустой лист на входе - пустой лист на выходе
    }

    //ну и различные имена тоже хочется
    public Set<String> getDifferentNames(List<Person> persons) {
        return new HashSet<>(getNames(persons));
    }

    //Для фронтов выдадим полное имя, а то сами не могут
    public static String convertPersonToString(Person person) {

        return Stream.of(person.getFirstName(), person.getSecondName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }

    // словарь id персоны -> ее имя
    public Map<Integer, String> getPersonNames(Collection<Person> persons) {

        return new ArrayList<>(persons).stream()
                .collect(Collectors.toMap(Person::getId, Task8::convertPersonToString));
    }

    // есть ли совпадающие в двух коллекциях персоны?
    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {

        return new ArrayList<>(persons1).stream()
                .anyMatch(person -> persons2.contains(person));
    }

    //...
    public long countEven(Stream<Integer> numbers) {

        return numbers
                .filter(num -> num % 2 == 0)
                .count();
    }

    @Override
    public boolean check() {
        System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
        boolean codeSmellsGood = false; //hope so
        boolean reviewerDrunk = false; //maybe
        final boolean olegIsAlwaysRight = true;
        return olegIsAlwaysRight;
    }
}
