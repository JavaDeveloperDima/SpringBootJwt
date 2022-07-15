SpringBootJwt - приложение ,позволяющие производить регистрацию и аутентификацю ,а так же отправку сообщений на сервер.
При успешном прохождении регистрации и аутентификации будет возвращенна Map<String,String> = {"jwt-token","token""}.
Для проверки работоспособности программы используем cURL команды :
1) Команда регистрации : curl -X POST http://localhost:1997/api/registration -H "Content-Type: application/json" -d '{"username":"dima","password":"10","email":"dima@mail.ru"}'
2) Команда аутентификации : curl -X POST http://localhost:1997/api/login -H "Content-Type: application/json" -d '{"username":"dima","password":"10"}'
3) Команда отправки сообщений аутентифицированному пользователю curl --location --request POST 'http://localhost:1997/auth/sendMessage' --header 'Authorization: Bearer token' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "username": "dima",
   "massage": "history 10"
   }'
 вместо token вставить активный ключ(действие ключа 1 час с момента создания) 
 в ответе,сервер распарсит "massage" в случае совпадения с "history 10" отдаст 10 последних сообщений из базы данных
 в ином случае , добавит сообщение в базу данных
 

Добавлены фйлы docker-compose и Dockerfile для запуска в Docker командой (docker-compose up)
Но не решена проблема с инициализацией базы данных во время запуска контейнера, в связи с этим работа с приложением в Docker 
временно невозможна 