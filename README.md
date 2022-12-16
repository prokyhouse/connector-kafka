# Connector [Kafka](https://kafka.apache.org) for [MidPoint](https://github.com/Evolveum/midpoint)

## Авторы

<img src="https://habrastorage.org/webt/xn/wq/r_/xnwqr_c12neoliwun446oljbewq.png" alt="Кирилл Прокофьев" align="left" width="150"/>

**Кирилл Прокофьев**  
*Студент ФИТ НГУ 2023 | Compuer Science and System design*  

E-mail:  k.prokofev1@g.nsu.ru

Telegram: [@proko23](https://t.me/proko23)

<br><img src="https://sun4-15.userapi.com/s/v1/ig2/FjWJi_Zm2Q3KZD-iwuhTudUT7xb8osoibdgorp-PauS_Lc7JRbU65f7EXz6pXSFoQrDC3AsqxShkPUdbiTom3Ois.jpg?size=200x200&quality=96&crop=305,10,500,500&ava=1" alt="Матвей Кузнецов" align="left" width="150"/>
**Матвей Кузнецов**  
*Студент ФИТ НГУ 2023 | 
Compuer Science and System design*  

E-mail: m.kuznetsov4@g.nsu.ru 

Telegram: [@m.kuznetsov4](https://t.me/m.kuznetsov4)

<br><img src="https://sun4.userapi.com/sun4-12/s/v1/ig2/i10oXz67ZpR_FwDdgV4VcyRdAo5SzAEvghvaZWqystOE2slhdKgs3DnCFAeoRajYBpUPTf-8Rj3uc2WkfqYAwxjK.jpg?size=200x200&quality=96&crop=0,0,1440,1440&ava=1" alt="Маргарита Лобова" align="left" width="150"/>
**Маргарита Лобова**  
*Студент ФИТ НГУ 2023 | 
Compuer Science and System design*  

E-mail: m.lobova@g.nsu.ru 

Telegram: [@MargaritaLobova](https://t.me/MargaritaLobova)

<br><img src="https://sun4.userapi.com/sun4-16/s/v1/ig2/zrrEwwWolKkL3wqfahbjXYGuklp1aqI0TlbsjYTwHViTKHFCoaBDsnXfcgUWDFYu2FC8erBQMBU_jkgzGQv27o5T.jpg?size=200x200&quality=95&crop=272,108,1088,1088&ava=1" alt="Артём Пленкин" align="left" width="150"/>

**Артём Плёнкин**  
*Студент ФИТ НГУ 2023 | 
Compuer Science and System design*  

E-mail: a.plenkin@g.nsu.ru 

Telegram: [@artemplenkin](https://t.me/artemplenkin)

<br><img src="https://sun4.userapi.com/sun4-11/s/v1/ig2/TQn7aN2mTN4021KcKwtL-Hu6-F8fcUM_qKC0dm-Vv-Le0hB-ictrBQNe68ktGFYeTsSEjd7wj9zf3-RAiZwWLtqd.jpg?size=200x200&quality=95&crop=221,438,1151,1151&ava=1" alt="Ксения Лященко" align="left" width="150"/>
**Ксения Лященко**  
*Студент ФИТ НГУ 2023 | 
Compuer Science and System design*  

E-mail: k.lyashchenko@g.nsu.ru

Telegram: [@Ksuuuuuuuuuusha](https://t.me/Ksuuuuuuuuuusha)



## Сборка проекта
1. собираем проект - `mvn clean package`
2. копируем `connector-kafka-1.0-SNAPSHOT.jar` из папки `target` в папку `midpoint/icf-connectors`
3. открываем терминал и переходим в корень проекта, где лежит `docker-compose.yml`.
4. поднимаем образы: `docker-compose -f docker-compose.yml up -d`

Ждем, пока поднимется мидпоинт
Дальше в нем переходим во вкладку resources и нажимаем import new resource
Нажимаем `choose file` и выбираем `kafka-connector-resource.xml`, который лежит в папке `midpoint/pio/post-initial-objects`
И импортируем.


Чтобы остановить контейнеры пишем в терминале: `docker-compose -f docker-compose.yml down -v`
