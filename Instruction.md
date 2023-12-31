## Инструкция по использованию программы – розыгрыш игрушек в магазине детских товаров.

### *Основные команды*:

***1. Добавить новую игрушку*** – toyService.addNewToy(new Toy(toyService.searchId("AllToys.txt"), "Собачка", 25, 40.0)); 

ID игрушки указывать нет необходимости, поскольку метод searchId() класса ToyService сам подсчитывает порядковый номер ID в файле со списком всех игрушек «AllToys.txt». Указывается имя игрушки, количество игрушки в типе int и вес игрушки в типе double.
***

***2. Изменение веса игрушки*** - toyService.changesWeightToy(1, 36.6); 

Указывается ID игрушки в типе int, которой нужно изменить вес. После новый вес игрушки в типе double.
***

***3. Розыгрыш игрушек*** - toyService.getRaffle(); 

Данный метод подключает класс «RaffleToy», который проводит розыгрыш игрушки с шансом выпадения на основании веса каждой игрушки. Выигранная игрушка переходит в лист ожидания – файл «Waiting list.txt». В свою очередь, количество игрушек в файле со списком всех игрушек «AllToys.txt» уменьшается на 1. Если количество какой-либо игрушки доходит до нуля, данная игрушка не учувствует в розыгрыше. Если количество всех видов игрушек равняется нулю – выбрасывается созданное исключение «MyNoToysException».
***

***4. Получение призовой игрушки*** - toyService.getToy(); 

Данный метод подключает класс «ToyService», который переносит первую игрушку в листе ожидания – файле «Waiting list.txt» в файл с выданными игрушками – «Received toys.txt». В свою очередь, в список игрушек в файле «Waiting list.txt» сдвигается. Если лист ожидания - файл «Waiting list.txt» пустой, выбрасывается созданное исключение «MyIsEmptyException».
***
***

Код написан при соблюдении принципов объектно-ориентированного программирования, SOLID, с обработкой всех возможных ошибок. Программа запускается и работает корректно, ошибок при выполнении программы нет.

Три основных файла программы:
*   «AllToys.txt» - файл, где хранятся все имеющие игрушки в магазине;
* «Waiting list.txt» - файл (лист ожидания), где хранятся игрушки, которые выиграли в розыгрыше и готовы к выдаче; 
* «Received toys.txt» - итоговый файл, в котором хранятся выданные игрушки.

***
***
Пилипенко Артур Геннадьевич, номер группы — 4595.