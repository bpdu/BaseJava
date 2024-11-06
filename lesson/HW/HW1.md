### HW1

* Реализуйте методы `save, get, delete, clear, getAll, size` в классе `ru.bpdu.basejava.storage.ArrayStorage`, организовав хранение резюме в массиве 
* Храните все резюме в начале `storage` (без пустот в виде `null`), чтобы не перебирать каждый раз все 10_000 элементов 
* При реализации метода `delete` учитывайте, что после удаления резюме между оставшимися резюме не должно быть пустых ячеек, заполненных null
```
Схема хранения резюме в массиве storage (в элементах от 0 до size-1 отсутствуют null):

r1, r2, r3,..., rn, null, null,..., null
<----- size ----->
<------- storage.length (10000) ------->
```
* Проверьте вашу реализацию с помощью классов `ru.bpdu.basejava.MainArray.main()` и `ru.bpdu.basejava.MainTestArrayStorage.main()`