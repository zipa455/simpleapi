# Технологии разработки программного обеспечения
* Лабораторная работа №1: создание микросервиса на Spring Boot с базой данных
* Шакиров А.А. - МБД2031
* Знакомство с проектированием многослойной архитектуры Web-API (веб-приложений, микро-сервисов)

## Клонирование исходного кода в Git

* cd \<project dir\>
* git clone https://github.com/zipa455/simpleapi .

## Сборка приложения в Maven

Для сборки в терминале выполнить команду:

* ```mvn clean install``` или ```mvnw clean install```, если у вас Windows

Перед этим у вас должна быть запущена и инициализирована база данных
Запустить базу данных можно так:
 ```
 docker run --name postgres-docker -e POSTGRES_PASSWORD=root -p 5432:5432 postgres
```
Инициализировать так: 
```
docker cp ./src/main/resouces/init.sql postgres-docker:/docker-entrypoint-initdb.d/init.sql
docker exec -u postgres postgres-docker psql postgres postgres -f docker-entrypoint-initdb.d/init.sql
```
  



## Cборка docker-образов

В файле  описан способ сборки в Docker двух контейнеров для их совместной работы как одно приложение. Docker-compose будет использовать  для сборки приложения:

Для сборки контейнеров выполнить команду:

		docker-compose build

Для создания приложения выполнить команду:

		docker-compose create
		


## Запуск docker-контейнеров

Для запуска приложения выполнить команду:

		docker-compose up
		


## Работа с приложением

Приложение запускает веб-сервер, доступный локально через сокет localhost:8080.



Веб-сервер имеет следующие конечные точки:

* (GET)		localhost:8080/api/v1/subject/ById
* (GET)		localhost:8080/api/v1/subject/all
* (POST)	localhost:8080/api/v1/subject/add
* (DELETE)	localhost:8080/api/v1/subject/del