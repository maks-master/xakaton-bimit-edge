# xakaton-bimit-edge

## Подготовка проекта

Установить postgres
 - Завести пользователя: xakaton
 - Завести базу данных: xakaton
 
Установить брокер (например MQTT брокер Mosquitto)


## Запуск проекта

Минимальный запуск
```
docker run -e BROKER=tcp://path_to_broker:1883 -e DATABASE=//IP_ADRESS:5432/xakaton  docker.pkg.github.com/maks-master/xakaton-bimit-edge/docker-xakaton-edge:work
```

Все параметры
```
docker run --name docker-xakaton-edge -e BROKER=tcp://path_to_broker:1883 -e DATABASE=//IP_ADRESS:5432/xakaton --rm -d -v ./logs:/usr/local/tomcat/logs  docker.pkg.github.com/maks-master/xakaton-bimit-edge/docker-xakaton-edge:work
```
