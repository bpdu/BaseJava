## Lesson 1 Review ##

### Review 1 ###
- ru.bpdu.basejava.model.Resume[] arr можно назвать resumes или allResume 
- для реализации методов удобней использовать for, чем while или do-while
- в clear() достаточно обнулить index = 0; один раз 
- строки можно объединить
  storage[index] = r;
  index++;
- index хранит количество резюме в массиве. Назови переменную size или count/countResumes 
- в get сразу возвращай return storage[i] или return null без промежуточной переменной
- непосредственное удаление в delete можно реализовать так: взять последнее резюме и перенести его на место удаляемого

### Review 2 ###

delete()

- int notDeletedCount = size;
  используй size, зачем создавать промежуточную переменную?
- метод сможет удалить резюме в последней ячейке массива?
