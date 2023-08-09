<p align="center">
<img src="https://foojay.io/wp-content/uploads/2021/12/quarkus_logo_vertical_rgb_600px_default.png" alt="quarkus logo" height="189px" width="300px">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Quarkus-3.2.2.Final-brightgreen.svg" alt="Quarkus">
  <img src="https://img.shields.io/badge/java-17-brightgreen.svg" alt="Java">
</p>

# MINERADORA-MICROSERVICE-QUARKUS

Projeto de estudo do Quarkus voltado para aprimorar minhas habilidades de desenvolvimento de microserviços. 
Este projeto simula um cenário de uma mineradora que lida com cotações de dólar, propostas de investidores para minério 
de ferro e geração de relatórios.

## Requisitos

Para construir e executar a aplicação, você precisa de:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)
- [Postgres](https://www.postgresql.org/)

## Kafka

Para otimizar o gerenciamento dos seus clusters Kafka, recomendamos a utilização do Conduktor. Essa ferramenta foi 
projetada para simplificar a administração e o monitoramento de clusters Kafka, oferecendo uma experiência especialmente 
vantajosa no contexto de microserviços que se comunicam por meio de mensagens.

link: https://www.conduktor.io/


## Executando a aplicação localmente

Você pode executar a aplicação localmente adaptando o arquivo application.properties e executando o comando abaixo dentro 
da pasta de cada microserviço.

```shell
mvn quarkus:dev
```

## Documentação swagger 

http://localhost:8095/swagger-ui.html


![Captura de tela de 2023-08-09 14-43-50](https://github.com/c-henrique-dev/mineradora-microservice-quarkus/assets/70810148/d27e8bc7-c51d-47d1-8223-3afb041436df)
