<h1>Jeasier<h1/>
<h2>1.Introdução<h2/>
<h5>Trata-se de uma biblioteca para o ecossistema spring, para projetos spring mvc ou spring boot, na sua primeira versão podemos gerar automaticamente uma aplicação crud com controllers, services, repositories, paginação e views com thymeleaf gerados baseado na sua camada de modelo. Se seu projeto for novo a biblioteca implementa dois templates free : <a>AdminLte</a> e Gentelella.</h5>
<h2>2.Configuração<h2/>
<h5>Para iniciar basta instalar o jar no classpath da aplicação.
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
<li>autoConfiguration=false</li>
<li>resourcePath=o caminho dos resources</li>
<li>staticPath=o caminho para statics</li>
<li>templatePath=o caminho para templates</li>
<li>referencePackage=seu pacote de referencia</li>
</ul>
<h5>3. Execução</h5>
