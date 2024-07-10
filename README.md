# CheckCreator
Программа для расчёта стоимости покупок и генерации чека. 

## Содержание
- [Использование](#использование)
- [To do](#to-do)

## Использование
Программу можно запустить с помощью команды
```
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx
balanceDebitCard=xxxx
```
Здесь: 
|Аргумент|Значение|
|:---:|:---:|
|id|идентификатор товара|
|quantity| количество товара|
|discountCard=xxxx|название и номер дисконтной карты|
|balanceDebitCard=xxxx|баланс на дебетовой карте|

Типы дисконтных карт приведены в файле discountCards.csv.
Типы продуктов приведены в файле products.csv.

## To do
- [x] Обеспечить функционирование программы.
- [ ] Реализовать паттерн Builder для создания экземпляров класса Product при чтении из products.csv.
- [ ] Реализовать паттерн Factory для генерации экземпляра карты по умолчанию и карты из файла discountCards.csv.
- [ ] Выделить в отдельные классы парсеры и ридеры, отделив их от конструкторов.
- [ ] Заменить Float на BigDecimal в местах, где идёт работа с денежными единицами.
