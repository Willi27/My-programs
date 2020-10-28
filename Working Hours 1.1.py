#!/usr/bin/env python
# coding: utf-8

# ### Working hours

# In[ ]:


def Working_Hours():

    workers = {}                                            # Список рабочик(Фамилия/количество отработаных часов)
    time = {}                                               # Техническая переменная(Хранение промежуточных значений)
    registeredNames = set('')
    
    days = {1: 'Понедельник', 2: 'Вторник', 3: 'Среда', 4: 'Четверг', 5: 'Пятница', 6: 'Суббота', 7: 'Воскресенье'}

    def morning():
        while True:
            nameBefore = input('Доброе утро. Ваше имя:')        # Регистрация рабочего в начале дня

            if nameBefore == "":
                return None

            elif nameBefore in registeredNames:
                print('Вы уже были зарегистрированы сегодня')


            else:
                registeredNames.add(nameBefore)
                morningTime = int(input('Текущее время:'))      # Время начала работы
# -------------------------------------------------------------------------------------------------------------
            if nameBefore not in workers:                       # Если фамилии нет в списке - добавляем
                workers[nameBefore] = 0                         # Начальное количество рабочих часов нового рабочего
                time[nameBefore] = morningTime
            else:
                time[nameBefore] = morningTime

        registeredNames.clear()            # После окончания цикла(и заполнения списка) - список должен быть отчищен
        print(registeredNames, 'CLEAR')    #  НЕ РАБОТАЕТ!


    def evening():
        count = len(registeredNames)                  # Счетчик количества вечерних запросов имени(равен количесту утренних запросов)
        while True:
            nameAfter = input('Добрый вечер. Ваше имя:')                # Регистрация рабочего в конце дня
            if nameAfter in workers and nameAfter in registeredNames:    # Проверяем зарегистрировался ли рабочий утром
                eveningTime = int(input('Текущее время:'))                # Время окончания работы
                workers[nameAfter] += (eveningTime - time[nameAfter])
                count -= 1
                
                registeredNames.remove(nameAfter)                             # Удаляем зарегистрированное имя из множества чтобы исключить повторную обработку
                print(f'{nameAfter}: {eveningTime - time[nameAfter]} ч.')     # Кжедневный отчет
                time[nameAfter] = 0                             # Техническая переменная(ежедневное обнуление счетчика)

                if count == 0:
                    return None
            else:
                print('Ваше имя не найдено')

    for i in range(5):                                      # 5 рабочих дней
        print()
        print(f'{days[i+1]}')
        morning()
        evening()
    
    print()                                                 # Еженедельный отчет
    print('Еженедельный отчет:')
    for j in workers:
        print(f'{j}: {workers[j]} ч.')
    
if __name__ == '__main__':                                  # Запуск программы
    Working_Hours()

