**Theory**  
**Questions**  

1: Что такое лямбда-выражение?  
**Ответ:** Лямбда-выражения, по сути, это анонимный класс или метод. Лямбда-выражение не 
выполняется само по себе. Вместо этого, оно используется для реализации метода, определенного в 
функциональном интерфейсе.  
**Источник:** https://habr.com/ru/post/512730/  

2: Из каких элементов состоит объявление лямбда-выражения?  
**Ответ:**
Лямбда-выражение состоит из следующего:  
а) Разделенный запятыми список формальных параметров, заключенный в круглые скобки.  
Метод `CheckPerson.test` содержит один параметр, `p` который представляет экземпляр `Person` класса.
Примечание. Тип данных параметров в лямбда-выражении можно не указывать. Кроме того, вы можете 
опустить круглые скобки, если имеется только один параметр. Например, следующее лямбда-выражение 
также допустимо:
```java
p -> p.getGender() == Person.Sex.MALE
    && p.getAge() >= 18
    && p.getAge() <= 25
```
б) Жетон стрелки `->`
в) Тело, состоящее из одного выражения или блока операторов. В этом примере используется следующее выражение:
```java
p.getGender() == Person.Sex.MALE
    && p.getAge() >= 18
    && p.getAge() <= 25
```
Если вы укажете одно выражение, среда выполнения Java вычислит выражение и затем вернет его значение. В качестве альтернативы вы можете использовать оператор return:
```java
р -> {
    вернуть p.getGender() == Person.Sex.MALE
        && p.getAge() >= 18
        && p.getAge() <= 25;
}
```
**Источник:** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html  

3: При объявлении лямбда-выражения какие структуры можно опустить?  
**Ответ:** В лямбда-выражении операторы должны заключаться в фигурные скобки ({}). 
Однако вам не нужно заключать вызов метода void в фигурные скобки. Например, следующее допустимое 
лямбда-выражение:
`электронная почта -> System.out.println (электронная почта)  `
Тип данных параметров в лямбда-выражении можно не указывать. Кроме того, вы можете опустить круглые скобки,
если имеется только один параметр.  
**Источник:** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html  

4: Что такое поток и конвейер в контексте лямбда-выражения?  
**Ответ:**  
Поток — это последовательность элементов. В отличие от коллекции, это не структура данных, 
в которой хранятся элементы. Вместо этого поток переносит значения из источника по конвейеру. 
В этом примере создается поток из коллекции `roster` путем вызова метода `stream`.  
Конвейер — это последовательность агрегатных операций.  
**Источник:** https://docs.oracle.com/javase/tutorial/collections/streams/index.html#pipelines  

5: Какие компоненты содержит конвейер?  
**Ответ:** 
а) Источник: это может быть коллекция, массив, функция-генератор или канал ввода-вывода.
б) Ноль или более промежуточных операций . Промежуточная операция, такая как `filter`, создает новый поток.  
в) Поток — это последовательность элементов. В отличие от коллекции, это не структура данных, 
в которой хранятся элементы. Вместо этого поток переносит значения из источника по конвейеру.  
г) Терминальная операция, возвращает результат, не являющийся потоком.  
**Источник:** https://docs.oracle.com/javase/tutorial/collections/streams/index.html#pipelines  

6: Что такое агрегатные операции? Приведите примеры агрегатных операций.  
**Ответ:** Агрегатные операции часто используются в сочетании с лямбда-выражениями, чтобы сделать 
программирование более выразительным, используя меньше строк кода.  
Операции `filter`, `map` и `forEach` являются агрегатными операциями . Агрегированные операции обрабатывают 
элементы из потока, а не напрямую из коллекции (именно поэтому в этом примере вызывается первый метод `stream`).
В следующем примере агрегатные операции используются для печати адресов электронной почты тех участников, 
содержащихся в коллекции `roster`, которые имеют право на выборочное обслуживание:  
```java
roster.stream().filter(
        p -> p.getGender() == Person.Sex.MALE
            && p.getAge() >= 18
            && p.getAge() <= 25)
        .map(p -> p.getEmailAddress())
        .forEach(email -> System.out.println(email));
```
**Источник:** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach9  

7: Какие различия между агрегатными операциями и итераторами?  
Агрегированные операции, такие как `forEach`, похожи на итераторы. 
Однако они имеют несколько принципиальных отличий:
а) Они используют внутреннюю итерацию : агрегатные операции не содержат метода, подобного `next` 
указанию им обработать следующий элемент коллекции. При внутреннем делегировании ваше приложение 
определяет, какую коллекцию оно итерирует, но `JDK` определяет, как итерировать коллекцию. 
С внешней итерацией, ваше приложение определяет и то, какую коллекцию оно повторяет, и то, как 
оно это делает. Однако внешняя итерация может выполнять итерацию только по элементам коллекции 
последовательно. Внутренняя итерация не имеет этого ограничения. Он может легче использовать 
преимущества параллельных вычислений, которые включают разделение проблемы на подзадачи, 
одновременное решение этих проблем и последующее объединение результатов решений подзадач.  

б) Они обрабатывают элементы из потока: агрегатные операции обрабатывают элементы из потока, 
а не непосредственно из коллекции. Следовательно, их также называют потоковыми операциями.  

в) Они поддерживают поведение в качестве параметров : вы можете указать лямбда-выражения в качестве 
параметров для большинства агрегатных операций. Это позволяет настроить поведение конкретной агрегатной операции.  
**Источник:** https://docs.oracle.com/javase/tutorial/collections/streams/index.html#pipelines  

8: Какие имеются ограничения на локальные переменные, которые используются в
лямбда-выражениях?  
**Ответ:** подобно локальным и анонимным классам, лямбда-выражение может обращаться только к 
локальным переменным и параметрам включающего блока, которые являются окончательными или 
фактически окончательными.  
**Источник:** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach9  

9: Что такое целевой тип лямбда-выражения и как компилятор определяет целевой тип?  
**Ответ:**
Тип данных, который ожидает лямбда-выражение, называется целевым типом. Чтобы определить тип
лямбда-выражения, компилятор Java использует целевой тип контекста или ситуации, в которой
было найдено лямбда-выражение.  
**Источник:** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#target-types-and-method-arguments  

10: В каких ситуациях может использоваться лямбда-выражение?  
**Ответ:**  
Лямбда-выражение может использоваться только в ситуациях, когда компилятор Java может определить целевой тип:  
1.	Объявления переменных  
2.	Присваивание  
3.	Возврат значения (return)  
4.	Инициализация массива  
5.	Аргументы метода или конструктора  
6.	В теле лямбда-выражения  
7.	Условные операторы  
8.	Операции преобразования типов  

**Источник:** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html  

11: Могут ли лямбда-выражения ссылаться на другие существующие методы? Если да -
приведите пример.  
**Ответ:** Иногда лямбда-выражение не делает ничего, кроме вызова существующего метода. 
В этих случаях часто удобнее ссылаться на существующий метод по имени. Ссылки на методы позволяют 
это сделать; они представляют собой компактные, легко читаемые лямбда-выражения для методов, 
у которых уже есть имя.  
```java
public class Person {

    // ...
    
    LocalDate birthday;
    
    public int getAge() {
        // ...
    }
    
    public LocalDate getBirthday() {
        return birthday;
    }   

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }
    
    // ...
} 
Arrays.sort(rosterAsArray, Person::compareByAge);
```
**Источник:** https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html  

12: Укажите виды ссылок на методы в контексте лямбда-выражений и приведите
соответствующие пример.  
**Ответ:** Существует четыре вида ссылок на методы:
а) Ссылка на статический метод  
Пример: 
```java
Person::compareByAge
MethodReferencesExamples::appendStrings
```

б) Ссылка на метод экземпляра конкретного объекта  
Пример: 
```java
myComparisonProvider::compareByName
myApp::appendStrings2
``` 

в) Ссылка на метод экземпляра произвольного объекта определенного типа  
Пример:  
```java
String::compareToIgnoreCase
String::concat
```

г) Ссылка на конструктор  
Пример:  
```java
HashSet::new 
```
**Источник:** https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html  

13: Что такое операции сокращения в контексте лямбда-выражений?  
**Ответ:** Это терминальные операции, которые выполняют определенную задачу и возвращают один определенный
элемент. `JDK` содержит множество терминальных операций (таких как `average, sum, min, max, и count`),
которые возвращают одно значение путем объединения содержимого потока. Эти операции называются
операциями сокращения. `JDK` также содержит операции сокращения, которые возвращают коллекцию вместо
одного значения. Многие операции сокращения выполняют определенную задачу, например, нахождение
среднего значения или группирование элементов по категориям.  
**Источник:** https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html  

14: Чем метод `reduce` отличается от метода `collect` в контексте лямбда-выражений?  
**Ответ:** В отличие от `reduce` метода, который всегда создает новое значение при обработке 
элемента, `collect` метод изменяет или видоизменяет существующее значение.  
**Источник:** https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html  

15: Укажите, какие преимущества дает использование класса `Optional`?  
**Ответ:** Главная же цель `Optional` — замена `null`-значений, благодаря чему должна повышаться 
безопасность и читаемость кода.  
**Источник:** https://alexkosarev.name/2019/03/15/optional-in-java/  

16: Выполните вывод каждого элемента коллекции `collect` с помощью метода `forEach ()`,
применяя:  
а) итератор
б) поток  
**Ответ:** 
`collect.forEach(System.out::println); - 1`
`collect.stream().forEach((e) -> System.out.println(e)); - 2`  
**Источник:** https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html#forEach-java.util.function.Consumer-  
      https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#forEach-java.util.function.Consumer-  

17: Выполните вывод каждого элемента `Map collect` с помощью `java 8`.  
**Ответ:** 
```java
Map<Integer,String> collect = new HashMap<>();
        collect.put(1,"ss");
        collect.put(2,"222");
        collect.put(3,"fsfs");
        collect.forEach((K,V) -> System.out.println(K + " " + V));
```

18: Допишите сортировку коллекции `users` по имени с помощью метода `getName()` и вывод
всех элементов коллекции в консоль (класс `User` приводить не обязательно).  
```java
public class Main {
public static void main(String[] args) {
List<User> users = new ArrayList<>();
users.add(new User("Nick", "Boll"));
users.add(new User("Jan", "Nicky"));
users.add(new User("Bot", "Smart"));
...
}
}
```  
**Ответ:** 
```java
List<User> users = new ArrayList<>();
users.add(new User("Nick", "Boll"));
users.add(new User("Jan", "Nicky"));
users.add(new User("Bot", "Smart"));
users.stream()
        .sorted(Comparator.comparing(User::getName))
        .forEach(System.out::println);
``` 

19: Допишите код, чтобы вывести только четные элементы коллекции, используя метод `filter()`.
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
…
}
}
``` 
**Ответ:** 
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
numbers.stream()
        .filter(e -> e % 2 == 0)
        .forEach(System.out::println);
```

20: Допишите код, чтобы вывести количество элементов коллекции, длина которых больше 4,
используя методы `filter()` и `count()`.  
```java
public class Main {
public static void main(String[] args) {
List<String> names = Arrays.asList("John", "Jan", "Tirion", "Marry", "Nikolas");
…
}
}
```
**Ответ:** 
```java
List<String> names = Arrays.asList("John", "Jan", "Tirion", "Marry", "Nikolas");
System.out.println(names.stream()
        .filter(e -> e.length() > 4)
        .count());
```

21: Допишите код, чтобы вывести каждый элемент коллекции, умножив его на 2, используя
метод `map()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 3, 5, 7);
}
}
```  
**Ответ:** 
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 3, 5, 7);
numbers.stream()
        .map(e -> e * 2)
        .forEach(System.out::println);
}
}
``` 


22: Создайте новую коллекцию `ArrayList` и выведите в консоль список четных чисел из
коллекции `numbers` с использованием методов `filter()` и `collect()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenNumbers = ...
…
}
}
``` 
**Ответ:** 
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenNumbers = numbers
        .stream()
        .filter(e -> e % 2 == 0)
        .collect(Collectors.toCollection(ArrayList::new));
  evenNumbers.forEach(System.out::println);
}
}
``` 

23: Создайте новую коллекцию `LinkedList` (имплементация `Queue`) и выведите в консоль НЕ
пустые строки из коллекции `ArrayList` names с использованием методов `filter()` и `collect()`.  
```java
public class Main {
public static void main(String[] args) {
List<String> names = Arrays.asList("Jaime", "Daenerys", "", "Tyrion", "");
Queue<String> queue = ...;
}
}
``` 
**Ответ:**  
```java
public class Main {
public static void main(String[] args) {
List<String> names = Arrays.asList("Jaime", "Daenerys", "", "Tyrion", "");
Queue<String> queue = names
        .stream()
        .filter(e -> !e.isEmpty())
        .collect(Collectors.toCollection(LinkedList::new));
  queue.forEach(System.out::println);
}
}
```

24: Выведите имена домашних животных, объединив их в новую коллекцию `List<String>
petNames` из коллекции их хозяев `humans`, у которых имена домашних животных являются
полями класса (в виде отдельных коллекций), используя метод `flatMap()`.  
```java
public class Main {
public static void main(String[] args) {
List<Human> humans = asList(
new Human("Sam", asList("Buddy", "Lucy")),
new Human("Bob", asList("Frankie", "Rosie")),
new Human("Marta", asList("Simba", "Tilly")));
List<String> petNames = ...
…
}
}
``` 
**Ответ:** 
```java
public class Main {
public static void main(String[] args) {
List<Human> humans = asList(
new Human("Sam", asList("Buddy", "Lucy")),
new Human("Bob", asList("Frankie", "Rosie")),
new Human("Marta", asList("Simba", "Tilly")));
List<String> petNames = humans
        .stream()
        .flatMap(hum -> hum.getPetNames().stream())
        .collect(Collectors.toList());
petNames.forEach(System.out::println);
}
}
``` 

25: Найдите и выведите первое по счету число, которое больше 10, используя методы `filter()` и
`findFirst()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 5, 8, 10, 12, 15);
Optional<Integer> first = ...
…
}
}
``` 
**Ответ:** 
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 5, 8, 10, 12, 15);
Optional<Integer> first = numbers
        .stream()
        .filter(e -> e > 10)
        .findFirst();
System.out.println(first);
}
}
``` 

26: Найдите и выведите первую попавшуюся фразу (с учетом возможного многопоточного
процесса), которая содержит фрагмент "Java", используя методы `filter()` и `findAny()`.  
```java
public class Main {
public static void main(String[] args) {
List<String> strings = Arrays.asList("Java is the best", "Java 8", "Java 9", "Jacoco");
Optional<String> java = …
...
}
}
``` 
**Ответ:** 
```java
public class Main {
public static void main(String[] args) {
List<String> strings = Arrays.asList("Java is the best", "Java 8", "Java 9", "Jacoco");
Optional<String> java = strings.stream()
        .filter(e -> e.contains("Java"))
        .findAny();
System.out.println(java);
}
}
``` 

27: Выведите `boolean`, имеется ли в коллекции хотя бы одно четное значение, используя
метод `anyMatch()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 4, 5, 7);
boolean match = ...
...
}
}
``` 
**Ответ:** 
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 4, 5, 7);
boolean match = numbers.stream()
        .anyMatch(e -> e % 2 == 0);
System.out.println(match);
}
}
``` 

28: Выведите `boolean`, являются ли все числа коллекции положительным, используя метод
`allMatch()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
boolean match = ...
...
}
}
``` 
**Ответ:** 
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
boolean match = numbers.stream()
        .allMatch(e -> e > 0);
System.out.println(match);
}
}
``` 

29: Выведите `boolean`, НЕ являются ли все числа коллекции четными, используя метод
`noneMatch()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
boolean match = ...
...
}
}
``` 
**Ответ:** 
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
boolean match = numbers.stream()
        .noneMatch(e -> e % 2 == 0);
  System.out.println(match);
}
}
``` 

30: Какие из ниже приведенных ламбда-выражений не скомпилируется и почему?  
```
1. (int x, int y) -> x+y
2. (x, y) -> x+y
3. (x, int y) -> x+y
4. (x, final y) -> x+y
5. int x -> x;
6. x -> y -> x + y;
7. x -> (final int y) -> y + x;
8. x -> x -> 5;
```  
**Ответ:**   
3 - Тип переменной указан только у одной из перменных. Либо его стоит указать у первой переменной,
либо не указывать вовсе.  
4 - `final` модификатор указан без типа переменной.  
5 - тип переменной помешает компиляции 
8 - Данный код - некорректный, правильно будет : `x -> 5`;  

31: Скомпилируется ли следующий код и почему?  
```java
for (byte b : "Java".getBytes()) {
foo(() -> b);
}
``` 
**Ответ:** Да, каждую итерацию переменная `b` будет создаваться заново.  
**Источник:** https://docs.oracle.com/javase/specs/jls/se14/html/jls-15.html#jls-LambdaBody  

32: Дана матрица 3х3 используя `Java 8` преобразуйте ее в одномерный массив.  
```java
int[][] matrix = { {1, 2, 3}
, {4, 5, 6}
, {7, 8, 9}};
int[] array = ….
``` 
**Ответ:**  
```java
int[][] matrix = { {1, 2, 3}
, {4, 5, 6}
, {7, 8, 9}};
        int[] array = Arrays.stream(matrix)
        .flatMapToInt(Arrays::stream)
        .toArray();
```  
33: Даны классы: 
```java
class BlogPost {
String title;
String author;
BlogPostType type;
int likes;
}
enum BlogPostType {
NEWS,
REVIEW,
GUIDE
}
List<BlogPost> posts = Arrays.asList( ... );  
```  
Определите:  
а) Все уникальные статьи относящиеся к каждому типу статей.  
б) Для каждого типа статьи определите статью с максимальным количеством лайков.  
в) Все статьи относящиеся к каждому типу статей, список статей должен 
представлять собой строку формата: "Post titles: [title1, title2, …..]".   
**Ответ:** 
```java
List<BlogPost> posts = Arrays.asList(
                new BlogPost("My way to Java", "D.Kursakov", BlogPostType.GUIDE, 957),
                new BlogPost("My way to Java", "D.Kursakov", BlogPostType.GUIDE, 957),
                new BlogPost("My way in the course", "P.Kursakov", BlogPostType.GUIDE, 959),
                new BlogPost("New info about HH", "D.Kursakov", BlogPostType.NEWS, 239),
                new BlogPost("Checking our points", "P.Kursakov", BlogPostType.REVIEW, 385));
Map<BlogPostType, Set<BlogPost>> groupType = posts               // a
                .stream()
                .collect(Collectors.groupingBy(BlogPost::getType,Collectors.toSet()));
Map<BlogPostType, Optional<BlogPost>> postsAndTypeWithMaxLikes = posts    // б
                .stream()
                .collect(Collectors.groupingBy(BlogPost::getType,
                        Collectors.maxBy(Comparator.comparingInt(BlogPost::getLikes))));
Map<BlogPostType, String> postsForEachType = posts
                .stream()
                .collect(Collectors.groupingBy(BlogPost::getType,
                        Collectors.mapping(BlogPost::getTitle, 
                                Collectors.joining(",","Post title [", "]"))));
```


34: Приведите два способа получения последнего элемента в потоке, в чем особенности
вычисления этого значения в потоках.  
**Ответ:**  
а) Использование `API` сокращения: 
```java 
Stream stream = someList.stream();
stream.reduce((first, second) -> second)
  .orElse(null);
```  
Снижение , проще говоря, уменьшает набор элементов в потоке до одного элемента.
В этом случае мы соберем набор элементов, чтобы получить последний элемент потока. 
Стоит иметь в виду, что этот метод будет возвращать только детерминистские результаты 
для последовательного потока.  
б) Использование функции пропуска:  
```java 
long count = valueList.stream().count();
Stream stream = someList.stream();
stream.skip(count - 1).findFirst().get();
``` 
В данном случае мы пропускаем все элементы, пока не дойдем до последнего. 
Используя функцию `skip` у потока. Стоит иметь в виду, что в этом случае, 
мы потребляем поток два раза, так что есть определенные последствия.  
**Источник:** https://javascopes.com/java-stream-last-element-a89718c7/  

35: Дан код, можно ли его как-то отрефакторить? Если да, то сделайте это.  
Подсказка:  
Добавьте в список элемент с автором, которые уже есть в списке и проверьте приложение 
```java
books.add(new Book("Java: A Beginner's Guide", "Herbert Schildt"));
class Book {
private String name;
private String author;
// getters and setters
…
}
List<Book> books = new ArrayList<>();
books.add(new Book("Effective Java", "Joshua Bloch"));
books.add(new Book("Thinking in Java", "Bruce Eckel"));
books.add(new Book("Java: The Complete Reference", "Herbert Schildt"));
Map<String, String> bookMap = books.stream().collect(
Collectors.toMap(Book::getAuthor, Book::getName));
bookMap.forEach((author, book) ->
System.out.println("Author: " + author + " Books: " + book));  
``` 
**Ответ:**   
Если добавить в список еще один элемент:
`books.add(new Book("Java: A Beginner's Guide", "Herbert Schildt"));`
В итоге возникнет ошибка всвязи с дублирующимеся ключами для `map` и в приложении возникнет исключение:  
`IllegalStateException` 
Чтобы исправить это используем другую перегрузку метода toMap:
`Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper,
Function<? super T, ? extends U> valueMapper,
BinaryOperator<U> mergeFunction)`
```java
List<Book> books = new ArrayList<>();
        books.add(new Book("Java: A Beginner's Guide", "Herbert Schildt"));
        books.add(new Book("Effective Java", "Joshua Bloch"));
        books.add(new Book("Thinking in Java", "Bruce Eckel"));
        books.add(new Book("Java: The Complete Reference", "Herbert Schildt"));
        Map<String, String> bookMap = books.stream()
        .collect(Collectors.toMap(Book::getAuthor, Book::getName, (existing, replacement) -> existing + ", " + replacement));
        
    }
    static class Book {
        private String name;
        private String author;
// getters and setters

        public Book(String name, String author) {
            this.name = name;
            this.author = author;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
```
**Источник:** https://www.baeldung.com/java-collectors-tomap  
36: Дан код 
```java
class Employee {
Integer employeeId;
String employeeName;
// getters and setters
}
class Department {
Integer employeeId;
String department;
// getters and setters
}
public class Main {
public static void main(String[] args) {
List<Employee> employees = new ArrayList<>();
List<Department> departments = new ArrayList<>();
populate(employees, departments);
List<Employee> salesEmpoyees = ...
}
}
```
Замените многоточие, чтобы определить сотрудников находящихся в отделе “sales”.   
**Ответ:**  
```java
List<Employee> salesEmployees = employees
                .stream()
                .filter(employee -> departments
                        .stream()
                        .anyMatch(dep -> (dep.getEmployeeId().equals(employee.getEmployeeId())
                                && dep.getDepartment().equals("sales")))).collect(Collectors.toList());
        salesEmployees.forEach(e -> System.out.println(e.getEmployeeName()));
    
```

37: Дан код 
```java
class Tuple<T1, T2> {
private T1 item1;
private T2 item2;
// getters and setters
}
List<String> names = new ArrayList<>(Arrays.asList("John", "Jane", "Jack", "Dennis"));
List<Integer> ages = new ArrayList<>(Arrays.asList(24, 25, 27));
List<Tuple<String, Integer>> namesAndAges = …  
```
Выполните операцию ‘zip’ для коллекций `ages` и `names`.
Zip: операция «zip» немного отличается от стандартной «concat» или «merge». В то время
как операции «concat» или «merge» просто добавят новую коллекцию в конец
существующей коллекции, операция «zip» возьмет элемент из каждой коллекции и
объединит их.
Например, в результате выполнения этого задания должна получиться коллекция:
[John;24, Jane;25, Jack;27]  
**Ответ:** 
```java
List<String> names = new ArrayList<>(Arrays.asList("John", "Jane", "Jack", "Dennis"));
        List<Integer> ages = new ArrayList<>(Arrays.asList(24, 25, 27));
        List<Tuple<String, Integer>> namesAndAges =
                IntStream.range(0, Math.min(names.size(), ages.size()))
                        .mapToObj(e -> new Tuple<>(names.get(e), ages.get(e)))
                        .collect(Collectors.toList());

```

38: Дан код, замените {code} и {type} так, чтобы получить нужные результаты
  ```java
  Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");
 //1. Удалить все дубликаты
  List<String> unique= strings.stream().{code}
  // напечатает unique= [a1, b2, c3]
  System.out.println("unique = " + unique);
  //2. Объединить все элементы в одну строку через разделитель : и обернуть тегами
  <b> ... </b>
  String join = strings.stream().collect({code});
  //3. напечатает <b> a1 : b2 : c3 : a1 </b>
  System.out.println("join = " + join);
  //4. Преобразовать в map, сгруппировав по первому символу строки
  Map<String, List<String>> groups = strings.stream().collect({code});
  // напечатает groups = {a=[a1, a1], b=[b2], c=[c3]}
  System.out.println("groups = " + groups);
  //5. Преобразовать в map, сгруппировав по первому символу строки и в качестве
  значения взять второй символ, если ключ повторяется, то значения объединить
  через “:”
  Map<String, String> groupJoin = strings.stream()
  .collect(Collectors.groupingBy({code}));
  //6. напечатает groupJoin = groupJoin = {a=1:1, b=2, c=3}
  System.out.println("groupJoin = " + groupJoin);
  Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);
  //7. Получить сумму нечетных чисел
  {type} sumOdd = numbers.stream().collect({code});
  //8. напечатает sumEven = 4
  System.out.println("sumOdd = " + sumOdd);
  //9. Вычесть из каждого элемента 1 и получить среднее
  double average = numbers.stream().collect({code});
  //10. напечатает average = 1.5
  System.out.println("average = " + average);
  //11. Прибавить к числам 3 и получить статистику: количество элементов, их сумму,
  макс и мин. значения, а также их среднее.
  {type} statistics = numbers.stream().collect({code});
  //12. напечатает statistics = … {count=4, sum=22, min=4, average=5.500000, max=7}
  System.out.println("statistics = " + statistics);
  //13. Разделить числа на четные и нечетные
  Map<Boolean, List<Integer>> parts = numbers.stream().collect({code});
  //14. напечатает parts = {false=[1, 3], true=[2, 4]}
  System.out.println("parts = " + parts);
  ```  
**Ответ:** 
```java
Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");
        //1. Удалить все дубликаты
        List<String> unique = strings
                .stream()
                .distinct()
                .collect(Collectors.toList());
        // напечатает unique= [a1, b2, c3]
        System.out.println("unique = " + unique);
        //2. Объединить все элементы в одну строку через разделитель : и обернуть тегами<b > ... </b >
        String join = strings.stream()
                .collect(Collectors.joining(":", "<b>", "</b>"));
        // напечатает <b> a1 : b2 : c3 : a1 </b>
        System.out.println("join = " + join);
        //3. Преобразовать в map, сгруппировав по первому символу строки
        Map<String, List<String>> groups = strings
                .stream()
                .collect(Collectors.groupingBy(e -> e.substring(0,1)));
        // напечатает groups = {a=[a1, a1], b=[b2], c=[c3]}
        System.out.println("groups = " + groups);
        //4. Преобразовать в map, сгруппировав по первому символу строки и в качестве
        // значения взять второй символ, если ключ повторяется, то значения объединить
        // через “:”
        Map<String, String> groupJoin = strings
                .stream()
                .collect(Collectors.groupingBy(e -> e.substring(0,1),
                        Collectors.mapping(e -> e.substring(1,2),Collectors.joining(":"))));
        // напечатает groupJoin = groupJoin = {a=1:1, b=2, c=3}
        System.out.println("groupJoin = " + groupJoin);
        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        //1. Получить сумму нечетных чисел
        Integer sumOdd = numbers
                .stream()
                .collect(Collectors.summingInt(e -> e % 2 == 0 ? 0 : e));
        // напечатает sumEven = 4
        System.out.println("sumOdd = " + sumOdd);
        //2. Вычесть из каждого элемента 1 и получить среднее
        double average = numbers
                .stream()
                .collect(Collectors.averagingDouble(e -> e - 1));
        // напечатает average = 1.5
        System.out.println("average = " + average);
        //3. Прибавить к числам 3 и получить статистику: количество элементов, их сумму,макс и мин.значения, а также их среднее.
        IntSummaryStatistics statistics = numbers
                .stream()
                .collect(Collectors.summarizingInt( e -> e + 3));
        // напечатает statistics = … {count=4, sum=22, min=4, average=5.500000, max=7}
        System.out.println("statistics = " + statistics);
        //1. Разделить числа на четные и нечетные
        Map<Boolean, List<Integer>> parts = numbers
                .stream()
                .collect(Collectors.partitioningBy(e -> e % 2 == 0));
        // напечатает parts = {false=[1, 3], true=[2, 4]}
        System.out.println("parts = " + parts);
```  

39: Дан поток, определите количество вхождений каждого из символов, составляющих поток.  
`Stream<String> words = Stream.of("Java", "Magazine", "is", "the", "best");`   
**Ответ:**  
```java
Map<String, Long> letterToCount = 
        words.map(word -> word.split(""))
        .flatMap(Arrays::stream)
        .collect(groupingBy(identity(), counting()));
```  

40: Дан код, как он будет выглядеть если `modem` обернуть в `Optional`?   
```java
boolean isInRange = false;
if (modem != null && modem.getPrice() != null
&& (modem.getPrice() >= 10
&& modem.getPrice() <= 15)) {
isInRange = true;
}
return isInRange; 
```   
**Ответ:** 
```java
Optional.ofNullable(modem)
       .map(Modem::getPrice)
       .filter(e -> e >= 10)
       .filter(e -> e <= 15)
       .isPresent();
```

41: Дан код, замените {code}, чтобы получить первый объект, которые не `null`, если такого нет
вернуть `default` 
```java
private Optional<String> getEmpty() {
return Optional.empty();
}
private Optional<String> getHello() {
return Optional.of("hello");
}
private Optional<String> getBye() {
return Optional.of("bye");
}
String firstNonNull = Stream.of(getEmpty(), getHello(), getBye()).{code};
```  
**Ответ:**  
```java
String firstNonNull = Stream.of(getEmpty(), getHello(), getBye())
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst()
        .orElseGet(() -> "default");
```