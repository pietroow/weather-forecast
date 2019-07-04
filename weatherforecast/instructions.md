##Requisitos:
* Java 8
* Maven
* Git
* Angular CLI 7+
* PostgreSQL


## Configuração e execução:

###Back-end

1. Fazer o clone do repositório:
`git clone https://github.com/pietroow/weather-forecast.git`

2. Após o projeto estar clonado trocar a branch para a develop:
`$ git checkout develop`

3. A configuração para conexão com o banco de dados vem por default:
    * `spring.flyway.user=postgres `
    * `spring.flyway.password=admin `
    * `spring.datasource.username=postgres `
    * `spring.datasource.password=admin `

    ######obs: Caso o usúario e senha forem diferentes do default, trocar as configurações do flyway e do banco no arquivo `resources/application.properties`

4. Para buildar o projeto é necessário ter o Maven instalado na maquina.
    entre na pasta '/weatherforecast' e digite no terminal `$ mvn clean install`
5. Após buildado, será gerado um arquivo na pasta /target com o nome **weatherforecast-beta**
6. Para executar o projeto, lembre de ter configurado o usuario e senha do banco de dados no arquivo **application.properties**
7. A lista de cidades válidas da API do OpenWeather se encontra num arquivo .json na pasta **/resources**.
Ao executar o projeto esse json já será carregado no banco de dados e caso venha existir alguma EXCLUSÃO de cidade no banco
será feito o upload do arquivo .json novamente no database local. Este comportamento pode ser alterado no método
`commandLineRunner` que se encontra no classe: `WeatherforecastApplication`.
8. O projeto subirá na porta 8190. Caso deseje alterar, faça o mesmo processo do passo 3 alterando a configuração `server.port`.
9. Por fim, para executarmos o projeto devemos entrar na pasta **/target** e digitar no terminal `java -jar weatherforecast-beta.jar`


###Front-end

* Agora que já estamos com a API rodando e pronto para ser consumida é hora de iniciarmos o nosso front-end.

1. Dentro da pasta **weatherforecast - ui** digitar o comando `npm install` para que seja feito o download das 
dependencias da aplicação.

    ###### É importante que nenhuma outra aplicação esteja rodando na porta 4200.

2. Finalizado o processo de instalação das dependencias digite no terminal qualquer um dos comandos:
* `npm start` 
* `ng s -o`.

    * Caso execute via `npm start` acesse este link [localhost](http://localhost:4200/)
    * Caso execute `ng s -o` já será aberto automaticamente uma nova aba do navegador com a página da aplicação.


## Sobre o projeto:

###Funcionamento:
* O campo de busca mostra apenas as cidades válidas na API do OpenWeather.
* É possível cadastrar as cidades do OpenWeather no banco de dados local, para que futuramente seja mais fácil de encontrar
as cidades já cadastradas.

###Modelagem:
* O projeto foi baseado na arquitetura REST.
* O design foi feito seguindo alguns princípios do DDD.


