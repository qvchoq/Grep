# Консольная утилита Grep
___
## Описание
___
Утилита Grep представляет собой вывод на консоль указанного (в аргументе командной строки) текстового файла с фильтрацией.
###Фильтры:
1. `[word]` - Задаёт слово для поиска (на консоль вывоядтся только содержащие его строки)
2. `[-r]` - Вместо слова задаёт регулярное выражение для поиска (на консоль выводятся только строки, содержащие данное выражение)
3. `[-v]` - Инвертирует условие фильтрации (выводятся только то, что ему **НЕ** соответсвует)
4. `[-i]` - Игнорировать регистр слов
___
## Инструкция
___

 ### `java -jar grep.jar [-i] [-r] [-v] word inputFileName.txt`
