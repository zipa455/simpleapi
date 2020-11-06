[![](https://travis-ci.com/zipa455/simpleapi.svg?branch=master)](https://travis-ci.com/github/zipa455/simpleapi) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=zipa455_simpleapi&metric=alert_status)](https://sonarcloud.io/dashboard?id=zipa455_simpleapi) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=zipa455_simpleapi&metric=bugs)](https://sonarcloud.io/dashboard?id=zipa455_simpleapi) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=zipa455_simpleapi&metric=code_smells)](https://sonarcloud.io/dashboard?id=zipa455_simpleapi)  [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=zipa455_simpleapi&metric=coverage)](https://sonarcloud.io/dashboard?id=zipa455_simpleapi)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/dashboard?id=zipa455_simpleapi) 

# Технологии разработки программного обеспечения
* Лабораторная работа №1: создание микросервиса на Spring Boot с базой данных
* Шакиров А.А. - МБД2031
* Знакомство с проектированием многослойной архитектуры Web-API (веб-приложений, микро-сервисов)

## Клонирование исходного кода в Git

* ```cd \<project dir\>```
* ```git clone https://github.com/zipa455/simpleapi .```

## Сборка приложения в Maven

Для сборки в терминале выполнить команду:

* ```mvn clean install``` или ```mvnw clean install```, если у вас Windows


Перед этим можно запустить и инициализировать базу данных
Запустить базу данных можно так:
 ```
 docker run --name postgres-docker -e POSTGRES_PASSWORD=root -p 5432:5432 postgres
```
Инициализировать так: 
```
docker cp ./src/main/resources/init.sql postgres-docker:/docker-entrypoint-initdb.d/init.sql
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
* (POST)	localhost:8080/api/v1/subject/setType/{id}
* (DELETE)	localhost:8080/api/v1/subject/del
* (GET)		localhost:8080/api/v1/type/ById
* (GET)		localhost:8080/api/v1/type/all
* (POST)	localhost:8080/api/v1/type/add
* (DELETE)	localhost:8080/api/v1/type/del

и

* (GET)		localhost:8080/api/v1/status

##Лабораторная работа №3: CI/CD и деплой приложения в Heroku
Целью лабораторной работы является знакомство с CI/CD и его реализацией на примере Travis CI и Heroku.

[Приложение размещено здесь](https://zipa455-simpleapi.herokuapp.com/)

[subject all](https://zipa455-simpleapi.herokuapp.com/api/v1/subject/all)

[type all](https://zipa455-simpleapi.herokuapp.com/api/v1/type/all)

[Status](https://zipa455-simpleapi.herokuapp.com/api/v1/status)