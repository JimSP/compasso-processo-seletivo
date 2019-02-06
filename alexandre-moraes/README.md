
**curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/cidade --data '{"nome":"São Paulo","estadoEnum":"SP"}'**

Response

Status Code: 201

body
*{ "id": 1, "nome": "São Paulo", "estado": "SP" }*

-----------------------------------------------------------------------------------------------------------------------------------

**curl -X GET -H 'Content-Type: application/json' -i 'http://localhost:8080/cidade?nome=São Paulo'**

Response

Status Code: 200

body
*[{ "id": 1, "nome": "São Paulo", "estado": "SP" }]*

-----------------------------------------------------------------------------------------------------------------------------------

**curl -X GET -H 'Content-Type: application/json' -i 'http://localhost:8080/cidade?estado=SP'**

Response

Status Code: 200

body
*[{ "id": 1, "nome": "São Paulo", "estado": "SP" }]*

-----------------------------------------------------------------------------------------------------------------------------------

**curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/cliente --data '{"id":null,"nomeCompleto":"Zézinho do Teste","sexo":"M","dataNascimento":925527600000,"idade":20,"cidadeOndeMoraId":1}'**

Response

Status Code: 201

body
*{ "id": 2, "nomeCompleto": "Zézinho do Teste", "sexo": "M", "dataNascimento": "1999-05-01T03:00:00.000+0000", "idade": 20, "cidadeOndeMoraId": 1 }*

-----------------------------------------------------------------------------------------------------------------------------------

**curl -X GET -H 'Content-Type: application/json' -i http://localhost:8080/2/cliente**

Response

Status Code: 200

body
*{ "id": 2, "nomeCompleto": "Zézinho do Teste", "sexo": "M", "dataNascimento": "1999-05-01T03:00:00.000+0000", "idade": 20, "cidadeOndeMoraId": 1 }*

-----------------------------------------------------------------------------------------------------------------------------------

**curl -X PATCH -H 'Content-Type: application/json' -i 'http://localhost:8080/3/cliente/nome alterado'**

Response

Status Code: 202

body
*{ "id": 2, "nomeCompleto": "nome alterado", "sexo": "M", "dataNascimento": "1999-05-01T03:00:00.000+0000", "idade": 20, "cidadeOndeMoraId": 1 }*

-----------------------------------------------------------------------------------------------------------------------------------

**curl -X DELETE -H 'Content-Type: application/json' -i http://localhost:8080/3/cliente**

Response

Status Code: 202