package by.gsu.epamlab;

public class Purchase implements Comparable<Purchase> {
    private final static String PRODUCT_NAME = "Coffee";
    private final static int PRICE = 782;                           //bunks
    private int numberOfUnits;
    private int discountPercent;
    private WeekDay weekDay;

    public Purchase() {

    }

    public Purchase(int numberOfUnits, int discountPercent, WeekDay weekDay) {
        this.numberOfUnits = numberOfUnits;
        this.discountPercent = discountPercent;
        this.weekDay = weekDay;
    }


    public String showConstant() {
        return String.format("Name: %s\nPrice: %s", PRODUCT_NAME, convert(PRICE));
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    private static String convert(int a) {
        return String.format("%d.%02d", a / 100, a % 100);
    }


    public int getCost() {
        return (int) Math.round(Double.parseDouble(convert
                (PRICE * numberOfUnits * (100 - discountPercent) / 100)));
    }

    @Override
    public String toString() {
        return numberOfUnits + ";" + discountPercent + ";"
                + weekDay + ";" + convert(getCost()*100);
    }

    @Override
    public int compareTo(Purchase purchase) {
        return Integer.compare(numberOfUnits, purchase.numberOfUnits);
    }


    /* Создайте пакет by.gsu.epamlab и определите класс Purchase, внутри которого
представляет собой оптовую покупку того же продукта по той же цене в течение
неделя. Класс Purchase должен реализовывать интерфейс Comparable, параметризованный
этот класс.
Поля класса:
● название продукта,
● цена (в белорусских рублях),
● количество купленных единиц,
● процент скидки,
● день недели (создать перечисление WeekDay).
Конструкторы:
● конструктор без аргументов,
● параметризованный конструктор.
Методы:
● геттеры / сеттеры;
● getCost () - возвращает стоимость покупки:
цена * число * (100 - процент) / 100, округленное до 1.00 BYN;
● toString () - возвращает строковое представление покупки в формате csv:
каждое непостоянное поле и стоимость покупки, разделенные знаком ";"
условное обозначение);
● compareTo (Purchase Purchase) - сравнивает количество купленных единиц
покупает и возвращает отрицательное целое число, ноль или положительное целое число, как это
покупка меньше, равна или больше указанной покупки.
Файл src \ in.txt состоит из 11 строк с правильными данными.
Первая строка содержит число PURCHASES_NUMBER от 0 до 10. Далее
В 10 строках содержится информация о 10 действительных покупках. Значения в строке
разделенные пробелами. Значение дня недели задается числами от 0 до 6, где
0 - воскресенье, ..., 6 - суббота.
Определите класс Runner в пакете по умолчанию, где:
1. Создайте массив для покупок PURCHASES_NUMBER.
2. Инициализировать этот массив данными файла.
3. Выведите содержимое массива в консоль в следующем формате:
константы класса
покупка [0]
...
купить [PURCHASES_NUMBER - 1]
4. Рассчитайте среднюю стоимость всех покупок (3 цифры после точки), итоговую
Стоимость всех покупок в понедельник, в день максимальной стоимости покупки. Выход
их к консоли.
5. Отсортируйте массив по номеру поля в порядке возрастания методом sort ().
класса Arrays.

6. Выведите содержимое массива в консоль в указанном выше формате.
7. Найдите покупку с номером 5 с помощью метода binarySearch ().
класса Arrays и выведите его.
     */
}
