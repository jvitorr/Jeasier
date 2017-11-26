# Jeasier - Java complete crud generator
#### Com suporte relacionamentos e enumarations
<img width="70%" alt="admin" src="https://user-images.githubusercontent.com/18018030/33237321-f3ec994e-d24e-11e7-8358-13281c5916ef.png">
<img width="70%" alt="gentelella" src="https://user-images.githubusercontent.com/18018030/33237322-f5560d6a-d24e-11e7-8633-168f8bc57cff.png">

<h2>1.Introdução<h2/>
<h5>Trata-se de uma biblioteca para o ecossistema spring, para projetos spring mvc ou spring boot, na sua primeira versão podemos gerar automaticamente uma aplicação crud com controllers, services, repositories, paginação e views com thymeleaf gerados baseado na sua camada de modelo. Se seu projeto for novo a biblioteca implementa dois templates free : <a>AdminLte</a> e Gentelella.</h5>


<h2>2.Configuração<h2/>
<h5>Para iniciar basta instalar o projeto no classpath da aplicação.
O default da biblioteca é a propriedade ‘autoConfiguration = true’, significa que a aplicação irá criar todos os pacotes no nível anterior ao seu modelo para classes, </h5>
<h5>Exemplo : </h5>
<h5> com.jeasier.model >> a aplicação irá criar os demais pacotes a partir de com.jeasier</h5>
<h5>Default para resources</h5>
<ul>
<li>staticos : src/main/resources/static</li>
<li>templates : src/main/resources/templates</li>
</ul>
<h5>Caso seu projeto tenha uma estrutura diferente basta acessar o arquivo easyjava.properties e alterar as seguintes variaveis : <h5>
<ul>
  <pre lang="java">
<li>autoConfiguration=false</li>
<li>resourcePath=o caminho dos resources</li>
<li>staticPath=o caminho para statics</li>
<li>templatePath=o caminho para templates</li>
<li>referencePackage=seu pacote de referencia</li>
  </pre>

</ul>

<h2>3. Execução</h2>
<h3>3.1 Para projetos com layouts existentes</h3>
<h4>3.1.1 Geração de crud a partir de uma classe</h4>
<pre lang="java">

JeasyAplication app = new JeasyAplication();
app.generateCrud(SuaClasse.class,"seu layout decorator");
  </pre>

<h4>3.1.2 Geração de crud a partir de uma pacote</h4>

  
   <pre lang="java">
 
JeasyAplication app = new JeasyAplication();
app.generateCrud("seu pacote","seu layout decorator");

  </pre>
  

<h3>3.2 Para projetos com novos : </h3>
<h4>3.2.1 Geração de crud a partir de uma classe</h4>

  <pre lang="java">
 
JeasyAplication app = new JeasyAplication();
app.generateCrud(SuaClasse.class,new Gentelella());

  </pre>

<h4>3.2.1 Geração de crud a partir de uma pacote :</h4>
<pre lang="java">

JeasyAplication app = new JeasyAplication();
app.generateCrud("seu pacote",new Gentelella());
  </pre>

<h2>4. Customização </h2>
<h5>Para customizar os pacotes, mensagens, path de criação, e mensagens basta alterar o arquivo easyjava.properties.</h5>


<h2>5. Acesso</h2>
<ul>
<li>/entidadeLowerCase/new - criação</li>
<li>/entidadeLowerCase/list – listagem</li>
</ul>

<h2>5. Classes suportadas</h2>
<ul>
<li>Todos tipos primitivos</li>
<li>Todas classes wrapper de tipos primtivos</li>
<li>Date</li>
</ul>
