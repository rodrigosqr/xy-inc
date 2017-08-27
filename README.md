# xy-inc
Desafio Back-End PL

## Tecnolgias utilizadas

    Spring Boot
    Hibernate
    Java 8
    Rest
    H2 Database

## 1. Requisitos e Configurações

Para executar o projeto é necessária a instalação das seguintes ferramentas:

    1. JDK 1.8
    2. Maven (versão 3.3.9 - testada)

## Instruções para execução

1 - Clone o projeto https://github.com/rodrigosqr/xy-inc.git

2 - Entre na pasta xy-inc através de um terminal e execute os comandos abaixo:
```sh
$ mvn clean package
$ java -jar target/xy-inc.jar
```

## Usando
Listar todos os Poi
```sh
GET http://localhost:9091/poi
```

Salvar um Poi
```sh
POST http://localhost:9091/poi

{
	"name" : "Lanchonete",
	"coordinateX" : 27,
	"coordinateY" : 12

}
```

Buscar por proximidade
```sh
GET http://localhost:9091/poi/proximity/20/10/10
```



