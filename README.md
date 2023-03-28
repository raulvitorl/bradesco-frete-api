![alt text](https://assets.b9.com.br/wp-content/uploads/2018/11/Bradesco-novo-logo1-1280x677.jpg "Bradesco")
# BRADESCO FRETE API
Aplicação para calculo de frete com base na regiao do cep.

## Testando a aplicação
Abrir o diretorio raiz do projeto, executar o comando:

```
docker build .
```
Ao concluir a build da imagem, execute:
```
docker image ls
```
Procure pelo IMAGE_ID da imagem mais a data de criação mais recente (a que acabou de ser gerada) e então execute:
```
docker run -p 8080:8080 IMAGE_ID
```

Acessar a interface do swagger:

http://localhost:8080/swagger-ui.html#/

Cobertura de testes: 
![alt text](https://drive.google.com/uc?export=view&id=10FnHrQvoqeSuA6Z7MyN3adGsgjB5RZym "Jacoco Cobertura")
Apenas classes onde houve necessidade de implementação lógica e das regras solicitadas foram testadas.
