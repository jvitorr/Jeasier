# Jeasier - Java complete crud generator
#### With support for relationships and enumerations
<img width="70%" alt="admin" src="https://user-images.githubusercontent.com/18018030/33237321-f3ec994e-d24e-11e7-8358-13281c5916ef.png">
<img width="70%" alt="gentelella" src="https://user-images.githubusercontent.com/18018030/33237322-f5560d6a-d24e-11e7-8633-168f8bc57cff.png">

<h2>1. Introduction<h2/>
<h5>It is a library for the spring ecosystem, for spring mvc or spring boot projects, in its first version we can automatically generate a crud application with controllers, services, repositories, pagination and views with thymeleaf generated based on its model layer. If your project is new the library implements two free templates: AdminLte and Gentelella</h5>


<h2>2. Configuration<h2/>
<h5>To start, just install the jar in the application's classpath. The default of the library is the property 'autoConfiguration = true', which means that the application will create all the packages at the level before its model for classes.
</h5>
<h5>Example : </h5>
<h5>com.jeasier.model, the application will create the other packages from com.jeasier</h5>
<h5>Default for resources</h5>
<ul>
<li>staticos : src/main/resources/static</li>
<li>templates : src/main/resources/templates</li>
</ul>
<h5>If your project has a different structure, simply access the file easyjava.properties and change the following variables: autoConfiguration = false</h5>
<ul>
  <pre lang="java">
<li>autoConfiguration=false</li>
<li>resourcePath=resource path</li>
<li>staticPath=the path to static</li>
<li>templatePath=the template path</li>
<li>referencePackage=your reference package</li>
  </pre>

</ul>

<h2>3.Execution</h2>
<h3>3.1 For projects with existing layouts: </h3>
<h4>3.1.1 Generating crud from a class: </h4>
<pre lang="java">

JeasyAplication app = new JeasyAplication();
app.generateCrud(SuaClasse.class,"seu layout decorator");
  </pre>

<h4>3.1.2 Generating crud from a package:</h4>

  
   <pre lang="java">
 
JeasyAplication app = new JeasyAplication();
app.generateCrud("seu pacote","seu layout decorator");

  </pre>
  

<h3>3.2 For projects with new ones:  </h3>
<h4>3.2.1 Generating crud from a class: </h4>

  <pre lang="java">
 
JeasyAplication app = new JeasyAplication();
app.generateCrud(SuaClasse.class,new Gentelella());

  </pre>

<h4>3.2.2 Generating crud from a package:</h4>
<pre lang="java">

JeasyAplication app = new JeasyAplication();
app.generateCrud("seu pacote",new Gentelella());
  </pre>

<h2>4. Customization </h2>
<h5>To customize the packages, messages, creation path, and messages simply change the easyjava.properties file. </h5>


<h2>5. Access</h2>
<ul>
<li>/entidadeLowerCase/new - criação</li>
<li>/entidadeLowerCase/list – listagem</li>
</ul>

<h2>5. Suported classes</h2>
<ul>
<li>All primitive types</li>
<li>All wrapper classes</li>
<li>Date</li>
</ul>

<h2>6.My contacts</h2>
<h5>https://www.linkedin.com/in/jo%C3%A3o-v%C3%ADtor-feitosa-232b47125/</h5>
<h5>joaoftnunes1@gmail.com/</h5>
