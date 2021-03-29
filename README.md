<h1> Requisitos AC1 </h1>
Desenvolva uma aplicação Web Rest API com Spring Boot para Cadastrar  Eventos:
A entidade EVENT possui:
<ul>
<li>id</li>
<li>name</li>
<li>description</li>
<li>place</li>
<li>start date</li>
<li>end date</li>
<li>start time</li>
<li>end time</li>
<li>email contact</li>
</ul>
<h4>OBS: Como combinado via mensagem os atributos (start date/end date/start time/end time) serão trocados por (startDateTime/endDateTime).</h4>

Construir um CRUD Rest para gerenciar eventos seguindo o modelo apresentado até o momento com três profiles: TEST, DEV e PROD.

<h2>Pontuação por Tarefa concluída:</h2>
<ul>
<li>Cadastro  - 1 Ponto.</li>
<li>Alteração -  1 Ponto.</li>
<li>Remoção - 1 Ponto.</li>
<li>Listagem Paginada - 1 Ponto.</li>
<li>Pesquisa Por Id - 1 Ponto.</li>
<li>Pesquisas Diversas Paginadas (filtros): Nome, Por Local do Evento, Data de Início e Descrição  - 2 pontos.</li>
<li>Data de Início = Listar eventos que começam depois da data de início passada.</li>
<li>Publicação no Heroku: 1 Ponto usando o PostgreSQL.</li>
<li>Arquitetura em Camadas Usando DTOs - 1 ponto.</li>
<li>Git Hub PRIVADO: 1 Ponto - Fazer pelo menos um commit para cada tarefa acima. </li>
</ul>
