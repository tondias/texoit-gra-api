# texoit-gra-api
API RESTful que possibilita a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

## Requisitos
Para execução do projeto é necessário instalação do JDK 8.
O passo-a-passo abaixo foi feito com base no SpringToolSuite4.

## Executação do projeto
1. Faça o download do repositório;
2. Para iniciar a aplicação clique no projeto com o botão direito do mouse, vá até a opção *Run As* e selecione Spring Boot App.
** Na execução do projeto, é criado o banco de dados que é automaticamente populado com base nos dados do arquivo movielist.csv, localizado em *src/main/resources/*.

## EndPoints
Após iniciar a aplicação, acesse: http://localhost:8080/swagger-ui.html#/ para ver as chamadas REST disponíveis.

## Testes
Para executar testes de integração abra a classe ProducerControllerIntegrationTest.java, clique em Run -> Run As -> JUnit Test.
