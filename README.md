# AlfaBank TestTask

### Тестовое задание на позицию Junior Java Developer в компанию Альфа-Банк

Задание:    
Создать сервис, который обращается к сервису курсов валют, и отдает gif в ответ:
если курс по отношению к рублю за сегодня стал выше вчерашнего, то отдаем рандомную [отсюда](https://giphy.com/search/rich),
если ниже - [отсюда](https://giphy.com/search/broke)


### Запуск

Gradle:

    gradlew bootRun

Docker:

    docker build . -t alfabank-testtask
    docker run -p 8080:8080 -t alfabank-testtask

Корректный вывод должен выглядеть следующим образом:

![cmdCorrecOutput](https://github.com/ahsel21/alfabank-testtask/blob/master/src/main/resources/screens/cmdCorrectOutput.png)

### Использование

В адресной строке браузера выполнить запрос:

    localhost:8080

Корректный ответ браузера должен выглядеть следующим образом:

![homePage](https://github.com/ahsel21/alfabank-testtask/blob/master/src/main/resources/screens/homePage.png)

Нужно ввести код валюты (Например "USD") и нажать кнопку "Enter", корректный ответ должен выглядеть следующим образом:

![USD](https://github.com/ahsel21/alfabank-testtask/blob/master/src/main/resources/screens/USD.png)

### Контакты

* Telegram - @ahsel21
* Email - kiptenko74@yandex.ru

