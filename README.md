# Documentação - Projeto CinePhrase

## 1. Descrição do Projeto
O projeto CinePhrase é uma aplicação que permite aos usuários obter citações aleatórias de filmes ou séries. Ele utiliza uma API para recuperar informações sobre os filmes e citações, além de um banco de dados para persistir essas citações.

## 2. Arquitetura e Camadas
A aplicação segue uma arquitetura tradicional de 3 camadas:

- **Camada de Apresentação (Controller):** Responsável por receber as requisições HTTP e enviar as respostas ao cliente.
- **Camada de Serviço (Service):** Contém a lógica de negócios. Ela interage com o repositório para acessar dados e transforma esses dados em um formato adequado.
- **Camada de Persistência (Repository):** Interage diretamente com o banco de dados para armazenar e recuperar informações.

## 3. Classes Importantes e Atributos

### Classe `FraseDTO`
**Responsabilidade:** Representa os dados da citação que serão transferidos para o front-end.

**Atributos:**
- `titulo` (String): O título do filme ou série ao qual a citação pertence.
- `frase` (String): O texto da citação do filme ou série.
- `personagem` (String): O nome do personagem que disse a citação.
- `poster` (String): A URL do pôster do filme ou série.

**Exemplo de instância:**
```
new FraseDTO("O Senhor dos Anéis", "Um anel para governá-los", "Sauron", "url_do_poster.jpg");
```
## Classe Frase
Responsabilidade: Representa a citação no banco de dados. Essa classe é mapeada para a tabela frases no banco de dados e contém os atributos que serão persistidos.

Atributos:

- `id` (Long): Identificador único da citação no banco de dados. Este é o atributo chave primária.
- `titulo` (String): O título do filme ou série associado à citação.
- `frase` (String): O texto da citação.
- `personagem` (String): O nome do personagem que proferiu a citação.
- `poster` (String): A URL do pôster que representa o filme ou série.
Exemplo de instância:

```
new Frase(1L, "Star Wars", "Que a força esteja com você", "Luke Skywalker", "url_poster.jpg");
```
## Classe FraseRepository
Responsabilidade: Interagir com o banco de dados para realizar operações de leitura e escrita de citações.

Métodos Importantes:

`getRandomFrase()`: Retorna uma citação aleatória do banco de dados.
```
@Query("SELECT f FROM Frase f ORDER BY FUNCTION('RANDOM') LIMIT 1")
Frase getRandomFrase();
```
## Classe FraseService
Responsabilidade: Contém a lógica de negócios da aplicação, interagindo com o repositório para obter os dados e retorná-los para o controlador em um formato adequado.

Atributos:

- fraseRepository: Instância do repositório FraseRepository, usada para acessar as citações no banco de dados.
Métodos Importantes:

`getFrase()`: Recupera uma citação aleatória, converte-a para o formato FraseDTO e a retorna.
```
public FraseDTO getFrase() {
    Frase frase = fraseRepository.getRandomFrase();
    return new FraseDTO(frase.getTitulo(), frase.getFrase(), frase.getPersonagem(), frase.getPoster());
}
```
## Classe FraseController
Responsabilidade: Controla as requisições HTTP e envia as respostas para o front-end.

Atributos:

- fraseService: Instância do serviço FraseService, usada para obter as citações.
Métodos Importantes:

`getFrase()`: Mapeia a rota GET /series/frases. Quando o front-end faz uma requisição para essa rota, o controlador chama o serviço para obter uma citação aleatória e a retorna em formato JSON.
```
@RestController
@RequestMapping("/series")
public class FraseController {

    private final FraseService fraseService;

    public FraseController(FraseService fraseService) {
        this.fraseService = fraseService;
    }

    @GetMapping("/frases")
    public FraseDTO getFrase() {
        return fraseService.getFrase();
    }
}
```
## 4. Fluxo de Execução
- Requisição: O front-end faz uma requisição HTTP GET para o endpoint /series/frases.
- Controlador: O controlador FraseController mapeia essa requisição e chama o método getFrase() da camada de serviço.
- Serviço: O serviço FraseService obtém uma citação aleatória chamando o repositório.
- Repositório: O repositório FraseRepository acessa o banco de dados e retorna uma citação aleatória.
- Resposta: O serviço converte os dados para o formato FraseDTO e os envia de volta ao controlador, que os retorna ao front-end.

## 5. Considerações Técnicas
- Banco de Dados: Utiliza o banco de dados relacional para armazenar as citações.
- Spring Boot: A aplicação é construída utilizando Spring Boot, com suporte a JPA e controle de versões via Git.
- Segurança: A segurança pode ser gerida através de autenticação e autorização se necessário, utilizando Spring Security.

## 6. Possíveis Melhorias
- Cache de Citações: Para otimizar a performance, poderia ser implementado um cache para as citações mais acessadas.
- Pesquisa de Citações: Implementação de endpoints para buscar citações por título, personagem ou outros filtros.
- Front-End: Melhorar a interface do usuário, exibindo as citações de forma mais interativa, incluindo imagens, vídeos e áudios das cenas.

