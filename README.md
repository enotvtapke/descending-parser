## Отчёт по лабораторной работе №2
Нисходящий рекурсивный парсер описаний массивов в Kotlin.
Пример: "var x: Array<Int>;"

| Terminal |   Description   |
|:--------:|:---------------:|
|    v     |      "var"      |
|    i     | Java identifier |
|    :     |       ":"       |
|    a     |     "Array"     |
|    <     |       "<"       |
|    ,     |       ","       |
|    >     |       ">"       |
|    ;     |       ";"       |
|    e     |       ""        |


| NonTerminal | First | Follow |                                 Description                                 |
|:-----------:|:-----:|:------:|:---------------------------------------------------------------------------:|
|      S      |   v   |   $    |                            Начальный нетерминал                             |
|      T      |  ; e  |   $    | Нетерминал, обзначающий наличие или отсутствие терминала ";" в конце строки |
|      E      |   i   |  , >   |               Идентификатор с generic параметрами или без них               |
|     E'      |  < e  |  , >   |                          Список generic параметров                          |
|      F      |   i   |   \>   |                   Внутренность списка generic параметров                    |
|     F'      |  , e  |   \>   |                  Либо продолжение списка F, либо его конец                  |

#### Правила грамматики
* S &rarr; vi:i\<F\>T
* E &rarr; iE'
* E' &rarr; \<F\>
* E' &rarr; e
* F &rarr; EF'
* F' &rarr; ,F
* F' &rarr; e
* T &rarr; ;
* T &rarr; e
