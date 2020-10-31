# Технологии разработки программного обеспечения
* Лабораторная работа №2: создание кластера Kubernetes и деплой приложения
* Шакиров А.А. - МБД2031
* Целью лабораторной работы является знакомство с кластерной архитектурой на примере Kubernetes, а также деплоем приложения в кластер.

Содержимое файла [kubernetes.yaml](Kubernetes/kubernetes.yaml)
```yaml
apiVersion: v1
kind: Service
metadata:
  name: postgres
spec:
  ports:
  - port: 5432  
  selector:
    app: postgres

---
apiVersion: v1
kind: ConfigMap
metadata:
  name: postgres-config
  labels:
    app: postgres
data:
  POSTGRES_DB: simpledb
  POSTGRES_USER: user
  POSTGRES_PASSWORD: pass
  POSTGRES_HOST: postgres
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: postgres
spec:
  replicas: 1
  selector:
    matchLabels:
      app: postgres
  strategy:
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
    type: RollingUpdate
  template: 
    metadata: 
      labels:
        app: postgres
    spec:
      containers:
      - name: postgres
        image: postgres
        envFrom:
        - configMapRef:
            name: postgres-config
        ports:
        - containerPort: 5432
        volumeMounts:
        - mountPath: /docker-entrypoint-initdb.d
          name: initdb
          readOnly: true
      volumes:
      - name: initdb
        configMap:
          name: postgres-initdb
---
apiVersion: v1
kind: Service
metadata:
  name: simpleapi 
spec:
  type: NodePort
  ports:
  - nodePort: 31317
    port: 8080
    protocol: TCP
    targetPort: 8080
  selector:
    app: simpleapi
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: simpleapi
spec:
  replicas: 10
  selector:
    matchLabels:
      app: simpleapi
  strategy:
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
    type: RollingUpdate
  template: 
    metadata: 
      labels:
        app: simpleapi
    spec:
      containers:
      - name: simpleapi
        image: simpleapi:latest    
        imagePullPolicy: Never    
        ports:
        - containerPort: 8080

```

Содержимое файла [postgresql-configmap.yaml](Kubernetes/postgresql-configmap.yaml)
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: postgres-initdb
data:
  01_shema_and_data.sql: |
    DROP TABLE IF EXISTS subject CASCADE;
    DROP TABLE IF EXISTS type CASCADE;

    create table type
    (
        id serial not null
            constraint type_pk
                primary key,
        name varchar
    );

    alter table type owner to current_user ;

    create unique index type_id_uindex
        on type (id);

    create table subject
    (
      id serial not null
        constraint city_pk
          primary key
        constraint subject_subject_id_fk
          references subject
            on update cascade on delete cascade,
      name varchar,
      populating integer default 0 not null,
      parent integer,
      type integer
        constraint subject_type_id_fk
          references type
            on update cascade on delete set null
    );

    alter table subject owner to current_user;

    create unique index city_id_uindex
      on subject (id);





    INSERT INTO subject( name, populating, parent, type) VALUES ('Russia',99999999,null,null);
    INSERT INTO subject( name, populating, parent, type) VALUES ('Moscow',999999,1,null);
    INSERT INTO subject( name, populating, parent, type) VALUES ('Moscow region',99999,1,null);
    INSERT INTO subject( name, populating, parent, type) VALUES ('Khimki',9999,3,null);
    INSERT INTO subject( name, populating, parent, type) VALUES ('Mayakowskogo',99,4,null);
    INSERT INTO subject( name, populating, parent, type) VALUES ('9k1',9,5,null);

```


Вывод команды ```kubectl get po```
![kubectl get po](https://github.com/zipa455/simpleapi/blob/main/screens/Kube1.PNG)

Обзор подов
![Обзор подов](https://github.com/zipa455/simpleapi/blob/main/screens/Kube2.PNG)

Видеообзор работы кластера: [тыц]: https://drive.google.com/file/d/1HcU1IbQBYC5eQSOKDxyRh8SarGJkdzWr/view?usp=sharing
