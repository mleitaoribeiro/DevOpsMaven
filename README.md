# **Projeto de Gestão de Finanças Pessoais - DevOps**

-----

## **1. Breve descrição**

O objetivo desta tarefa é implementar uma *pipeline* no Jenkins para o projeto de Gestão Finanças Pessoais, no qual devem ser aplicadas as seguintes funcionalidades de DevOps:

**Infraestrutura como código**

  -  Deve ser possível reproduzir uma solução e uma alternativa num sistema com Vagrant e um clone do repositório, ou seja, os scripts e o código do repositório.

**Git / Bitbucket**

  - Devem ser criadas tarefas e *Issues* para todos os membros da equipa.
  - Devem ser usados *branches* para desenvolver funcionalidades específicas da solução.

**Vagrant**

Deve ser usado o Vagrant para virtualizar todas as partes da solução:

  - Uma *Virtual Machine* (VM) com Jenkins e Ansible e, eventualmente, Docker;
  - Uma VM para instalar e configurar a aplicação *Web*;
  - Uma VM para instalar e configurar a base de dados da aplicação.

**Ferramentas de compilação**

- Deve ser usado o Maven e o Gradle como alternativa.

**Pipeline no Jenkins**

Devem ser criadas as seguintes etapas (*stages*) no pipeline:

-  **Checkout**: Para fazer o *checkout* do código do repositório;

-  **Assemble**: Para compilar e produzir os artefactos gerados pela aplicação (Nota: Aqui os testes não deverão ser executados);

-  **Test**:  Para executar os testes (Unitários e de Integração) e publicar no Jenkins os resultados dos testes, assim como a sua cobertura;

-  **Javadoc**: Para gerar e publicar o Javadoc no Jenkins;

-  **Archive**: Para arquivar no Jenkins os ficheiros gerados durante o Assemble - o ficheiro *.war*;

-  **Docker**: Para gerar duas imagens Docker (app + db) e publicar as mesmas no Docker Hub;

-  **Ansible**: Para fazer o *deploy* e configurar a aplicação e a base de dados. A VM da base de dados deve usar uma "DBMS" que não seja H2 (por exemplo, o PostgreSQL).

O relatório está dividido em 2 partes:

  - Uma secção dedicada à descrição da implementação dos requisitos, onde foi incluída uma descrição das etapas usadas para alcançar os mesmos.
  - Uma secção dedicada à análise e implementação da alternativa.

Na solução principal deve ser criado o projeto de gestão de finanças pessoais usando o Maven e deve ser feito o *deploy* da aplicação e da base de dados diretamente nas VM.

Na alternativa, o projeto deve ser criado usando o Gradle e o *deploy* da aplicação e da base de dados deverá ser feito com o Docker, ou seja, eles devem ser executados dentro de contentores.

> **Nota**: Ambas as soluções devem criar as imagens do Docker, mas apenas a alternativa precisa de fazer o *deploy* e executar a aplicação e a base de dados usando contentores.

### **1.1 Pré-requisitos**

* [Configuração de um editor](https://git-scm.com/book/en/v2/Customizing-Git-Git-Configuration). É necessário possuir um pacote de linha de comandos git (por exemplo, o [Cmder](https://cmder.net/)) e um IDE que suporta o Git (por exemplo, o [IntelliJ IDEA](https://www.jetbrains.com/idea/));

* Instalar o [Maven](https://maven.apache.org/) (necessário para a solução principal) e o [Gradle](https://gradle.org/) (necessário para a solução alternativa);

* Instalar a [VirtualBox](https://www.virtualbox.org/);

* Instalar o [Vagrant](https://www.vagrantup.com/);

* Instalar o [Docker Desktop](https://docs.docker.com/docker-for-windows/install/) (para Mac ou Windows 10 Pro, Enterprise, ou Education) ou o [Docker ToolBox](https://docs.docker.com/toolbox/toolbox_install_windows/) (para outros Sistemas Operativos);

* Instalar o [PostgreSQL](https://www.postgresql.org/)

> **Atenção:** Deve ser feito um *fork* ao repositório e aplicar as soluções tecnológicas ao repositório *forked*.

Os repositórios usados para implementar esta tarefa estão disponíveis nos seguintes *links*:

* [Repositório Maven](https://bitbucket.org/martalribeiro/devops_g2_maven)
* [Repositório Gradle](https://bitbucket.org/martalribeiro/devops_g2_gradle)


### **1.2 Descrição técnica das ferramentas usadas**

#### **1.2.1 Git - Sistema de Controlo de Versões**

 O **[Git](https://git-scm.com)** é o sistema de controlo de versões mais usado do mundo, desenvolvido em 2005 por [Linus Torvalds](https://pt.wikipedia.org/wiki/Linus_Torvalds). É um sistema *open-source*, com manutenção ativa e com um excelente suporte da comunidade.
O Git permite acompanhar as mudanças feitas no código base, mantendo um histórico das alterações. Além disso, também regista quem efetuou essas mudanças e permite a restauração do código removido ou modificado.
Esta ferramenta foi utilizada através da linha de comandos e com o suporte de um IDE (Integrated Development Environment), nomeadamente o IntelliJ IDEA.

#### **1.2.2 Maven and Gradle - Build Tools**

As *build tools* são programas que automatizam o processo de criação de uma aplicação executável a partir de um *source code*. *Building* incorpora a compilação e *packaging* do código num formato utilizável ou executável.

[**Maven**](https://maven.apache.org/)

O Apache Maven é uma ferramenta de automação de ***build*** e um gestor de dependências, primariamente usado em aplicações Java. Utiliza um ficheiro XML para a sua configuração, tal como faziam os seus antecessores, como o Apache Ant. No entanto, o Maven fá-lo de forma muito mais automatizada pois existem convenções e ***goals*** pré-definidos que facilitam a construção do ficheiro de configuração.

O ficheiro de configuração do Maven, que contém as instruções de ***build*** e gestão é, por convenção, chamado ***pom.xml*** e possui uma estrutura de construção muito rígida, dando pouca flexibilidade ao utilizador na construção dos ***goals***. No entanto, pelo mesmo motivo, torna-se mais fácil construir rapidamente esta estrutura porque existem convenções definidas e a maior parte do trabalho é feito pelos *plugins* do Maven. Existem uma grande variedade de *plugins* disponíveis que podem ser utilizados e configurados.

Uma das grandes vantagens do Maven e que o torna tão popular é o facto de ser muito mais fácil de manter ao longo do tempo, especialmente em projetos de grandes dimensões. Por outro lado, por vezes os ficheiros de configuração podem se tornar muito longos e de difícil legibilidade para quem não está familiarizado com o projeto.


**[Gradle](https://gradle.org/)**

O Gradle é uma ferramenta de criação *open source* projetada para automatizar o processo de criação de software, usando a linha de comando com um IDE como suporte.
Foi desenhada para ser flexível o suficiente para criar quase qualquer tipo de software.

O Gradle surge como uma combinação dos seus antecessores (Ant e Maven). A principal diferença é o facto de não usar ficheiros XML para a sua configuração. O Gradle adota uma linguagem *domain-specific language(DSL)* baseada em Groovy ou Kotlin. Como resultado, os *scripts* de configuração do Gradle tendem a ser muito mais curtos e claros do que aqueles escritos para Ant ou Maven.
O DSL foi então concebido para facilitar todo o processo de desenvolvimento, em todos o ciclo de vida desde a analise até ao teste.


#### **1.2.3 Vagrant - Virtualização**

O [Vagrant](https://www.vagrantup.com/intro/index.html) é uma ferramenta usada para construir várias máquinas virtuais. Esta foca-se na automação, servindo como gestor de uma ou mais máquinas através de um ficheiro - o [Vagrantfile](https://www.vagrantup.com/docs/vagrantfile/). Uma vez criado e preparado este ficheiro, podemos correr o comando *vagrant up*, despoletando a instalação e configuração das máquinas. Isto significa que outros utilizadores podem replicar o mesmo ambiente a partir do mesmo ficheiro de configuração, independentemente do sistema operativo.

*Virtualização* é um processo que permite aos computadores emular outros, conhecidos como máquinas virtuais. As
máquinas virtuais podem ser referidas por *guest* (ou convidado) e a máquina física que as executa é referido como *host* (ou anfitrião).



#### **1.2.4 Docker - *Containerization***
O [Docker](https://docs.docker.com/) é uma tecnologia *open source* que possibilita o empacotamento de uma aplicação ou ambiente inteiro dentro de um ***container***. A grande vantagem está na facilidade do ***deploy***, pois uma vez configurado o ambiente correto, este mantêm-se sempre o mesmo e não é necessário de estar sempre a configurá-lo, encurtando drasticamente o tempo despendido nesta fase. Não só, mas também permite que qualquer pessoa com o Docker instalado corra esse mesmo ***container*** e tenha assim acesso a todo o ambiente já devidamente configurado.

**Dockerfile** - está no início de todo e qualquer ***container*** e consiste , basicamente, num ficheiro de texto que descreve a configuração do ***container***. Em suma, reúne todas as instruções e comandos necessários para montar uma ***image***.

**Docker Image** - após a criação de uma *dockerfile* deve então ser invocado o ***docker build*** que vai criar uma imagem à luz das especificações presentes na dockerfile. Estas são ficheiros unicamente de leitura e como tal **imutáveis**. 

**Docker Container** -  é idêntico a uma instância de uma ***docker image*** mas que dá ao utilizador a possibilidade de poder manipular e fazer alterações a esta "cópia". Estes *containers* são **unidades compactas e portáteis** capazes de iniciar aplicações de forma **simples e rápida**.

#### **1.2.5 Jenkins - Pipelines de Integração contínua/Entrega contínua (CI/CD)**

O [Jenkins](https://www.jenkins.io/) é um programa de automação *open source* baseado na linguagem Java e com *plugins* desenvolvidos especificamente para fins de **integração contínua e entrega contínua**. O Jenkins é um sistema CI/CD de uso simplificado que facilita a integração de mudanças em projetos por parte dos *developers*. Faz com que seja fácil obter um *build* automatizado, aumentando assim a produtividade da equipa de desenvolvimento.

Para implementar CI/CD, são criados ***pipelines*** que permitem a entrega de novas funcionalidades de forma mais rápida e eficiente através de automação. As **pipelines** são modelos de CI/CD definidos pelo utilizador, que contéem uma lista de *stages* por ordem de execução, que são necessárias para o *build* da aplicação. 

 **Jenkinsfile** - é um ficheiro de texto que pode ser colocado num repositório. Nestes *Jenkinsfiles* são definidos os diferentes *stages* que, por sua vez, podem ter diversos *steps*. Estes *stages* são blocos de código presentes no *Jenkinsfile* e que podem ter especificados diferentes comandos e tarefas que se pretende que o Jenkins execute.

### **1.2.6 Ansible - Configuration Management**
 O [Ansible](https://www.ansible.com) é uma ferramenta *open source* de automação e provisionamento usada para gerir múltiplos servidores remotos em simultâneo. Com Ansible é possível automatizar tarefas, tais como a instalação e atualização de *packages*, configuração e *deploy* de aplicações e muitas outras tarefas administrativas. Com a automatização destas tarefas é possível aumentar níveis de desempenho, velocidade e produtividade que não seriam alcançáveis de forma manual.

O Ansible usa ***SSH*** para se conectar com os *hosts* e executar as ***tasks*** que são definidas num ***playbook***. Geralmente, é necessário que tanto a máquina de controlo como as *hosts* tenham por base um sistema Linux ou um sistema operacional baseado em Unix e que tenham instalado *[Phyton](https://www.python.org/downloads/)*.

Uma característica relevante do Ansible é o princípio da **idempotência**. Esta característica permite que após cada execução de provisionamento o sistema atinja ou mantenha o estado desejado, mesmo após várias execuções.

Importa referir que o Ansible é *agentlesss*, logo, o processo de automação é mais eficiente e mais seguro.

Em termos de linguagem, o Ansible usa uma linguagem bastante simples - *[YAML](https://pt.wikipedia.org/wiki/YAML)* e, em termos de estrutura, divide-se em:

* **Inventory:** ficheiro onde são declarados os *hosts* a serem gerenciados pelo Ansible;

* **Modules:** controlam os recursos (serviços, pacotes, arquivos etc) dos *hosts* remotos. Os módulos são declarativos e são, também, idempotentes;

* **Tasks:** tarefas que serão executadas nos *hosts*. As *tasks* contém *modules*, que efetivamente vão realizar o trabalho de automatização;

* **Playbooks:** conjunto de *tasks* ordenadas que serão executadas em cada *host*.

### **1.2.7 PostgreSQL - Base de Dados**

O [PostgreSQL](https://www.postgresql.org/) é um sistema de gestão de base de dados relacional. Este sistema foi originalmente desenhado para correr em plataformas do tipo UNIX. No entanto, mais tarde foi adaptado e desenhado para que pudesse ser utilizado em várias plataformas como Windows e Mac OS.

Actualmente, o PostgreSQL é um *software open source* que pode ser utilizado livremente por qualquer utilizador. Uma das principais vantagens do PostgreSQL é que requere muito pouco esforço de manutenção devido à sua grande estabilidade. Desta forma, uma aplicação baseada neste sistema tem custos muito reduzidos em comparação com outros sistemas de gestão de base de dados.


## **2. Análise, Design e Implementação**

### **2.1. Maven**

Em relação ao Maven foi necessário realizar algumas alterações face ao ficheiro inicial de configurações do projeto. Primeiramente, foi preciso adaptar o projeto para construir um *war file* durante a execução do *goal package*.

Para este efeito foi adicionado ao *pom.xml*:

```sh
<packaging>war</packaging>
```

O nome do projeto foi alterado passando para **devOpsProject** e, como consequência, o *.war* denomina-se **devOpsProject-1.0-SNAPSHOT**.

De seguida, foi necessário instruir o Spring Boot a transferir as bibliotecas do
Tomcat para o *.war*  ser expandido. Foi então necessário adicionar a seguinte dependência ao *pom.xml*:

```sh
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-tomcat</artifactId>
	<scope>provided</scope>
</dependency>
```

Adicionalmente, foi necessário adicionar também funcionar os  seguintes *profiles*:

```sh
<profiles>
        <profile>
            <id>tomcat</id>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                    <scope>provided</scope>
                </dependency>
            </dependencies>
        </profile>
        <profile>
            <id>jetty</id>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-jetty</artifactId>
                    <scope>provided</scope>
                </dependency>
            </dependencies>
        </profile>
        <profile>
            <id>undertow</id>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-undertow</artifactId>
                    <scope>provided</scope>
                </dependency>
            </dependencies>
        </profile>
</profiles>
```

Foi preciso também adicionar o *plugin* referente ao Spring Boot Framework:

```sh
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```

O último passo consistiu criar um *servlet* para que a aplicação web corra dentro de um container Tomcat. Desta forma, foi construída a classe *Servlet Initializer* com a seguinte implementação:

```sh
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
```

```sh
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjectApplication.class);
    }
}
```
Por último, ao ficheiro *application.properties* foi adicionado o *path*:

```sh
server.servlet.context-path=/devOpsProject-1.0-SNAPSHOT
```

**Javadoc** - Para ser possivel gerar javadoc na *pipeline* do Jenkins e fazer o *report*, foi adicionado ao *pom.xml* o seguinte *plugin*:

```sh
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-javadoc-plugin</artifactId>
	<version>3.2.0</version>
	<configuration>
	   <stylesheetfile>${basedir}/src/main/javadoc/stylesheet.css</stylesheetfile>
	   <show>public</show>
	</configuration>
</plugin>
```

Seguidamente, foram utilizados os seguintes *plugins* para fazer o relatório de resultados de testes:

```sh
<plugin>
	<artifactId>maven-surefire-plugin</artifactId>
	<version>2.22.2</version>
	<configuration>             
	  <properties>
	     <property>
		<name>listener</name>
		<value>org.sonar.java.jacoco.JUnitListener</value>
	     </property>
	   </properties>
	</configuration>
</plugin>
```
E, para verificar a cobertura dos testes foi necessário adicionar o seguinte *plugin*:

```sh
<plugin>
	<groupId>org.jacoco</groupId>
	<artifactId>jacoco-maven-plugin</artifactId>
	<version>0.8.5</version>
	<executions>
	  <execution>
	    <id>default-prepare-agent</id>
	    <goals>
	       <goal>prepare-agent</goal>
	    </goals>
	  </execution>
	  <execution>
	    <id>default-report</id>
	    <goals>
	       <goal>report</goal>
	    </goals>
	  </execution>
	</executions>
</plugin>
```


### **2.2. Migração para PostgreSQL**

Um dos objectivos do projeto era realizar a migração de um servidor de base de dados H2 para um outro servidor de base de dados. Foi escolhida a opção do PostgreSQL para esta migração devido a todas as vantagens já referidas anteriormente.

Para cumprir este objectivo foi primeiro necessário adicionar a dependência do PostgreSQL ao ficheiro **pom.xml** do Maven.

````
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.14</version>
</dependency>
````


De seguida, foi necessário configurar a ligação do servidor de base de dados à aplicação web desenvolvida. Para isso, foi necessário aceder ao ficheiro *application.properties* localizado na pasta **resources**, na secção **main** do **source**.

````
spring.datasource.url=jdbc:postgresql://192.168.33.12:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.generate-ddl-auto=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.hibernate.show-sql=true
spring.datasource.initialization-mode=always
````


Para aceder à base de dados PostgreSQl configurada na **VM host2**, foi necessário definir o *url* de acesso neste ficheiro. O *url* tem obriatoriamente de conter o endereço IP da máquina - 192.168.33.12 e o porto de acesso - 5432, bem como o nome da base de dados à qual queremos ligar. É também necessário definir um nome de utilizador e uma *password* para garantir a ligação. Outra alteração importante foi configurar o **hibernate** para o modo **create-drop** pois, embora fosse desejável definir o modo **update** para que os dados não fossem perdidos, a forma como a configuração da aplicação e dos testes foi feita no projeto original, obrigou a esta opção. Para além do referido, foi necessário colocar algumas configurações adicionais de forma a garantir uma conecção adequada.

Outra ferramenta utilizada no projeto foi o ***pgAdmin***, uma plataforma *open source* de desenvolvimento e administração de base de dados PostgreSQL. Com o ***pgAdmin*** é possível fazer o acesso à base de dados configurada de uma forma mais prática e fácil, pois este dispõe de uma *user interface*. Desta forma, após configuração da **VM host2** e *deploy* da base de dados, é possível abrir o ***pgAdmin*** e aceder aos dados presentes na base de dados.


![](https://i.imgur.com/WXAM1BV.png "pgAdmin")


### **2.3 Vagrant**
No *vagrantifle* criaram-se e configuraram-se três máquinas virtuais, para se executar várias funcionalidades:

* **host2** - para suportar a base de dados, em postgreSQL;
* **host1** - para executar a aplicação de gestão de finanças pessoais;
* **ansible** - para executar o *playbook* do Ansible e dar suporte ao Jenkins.

Tendo por base o *vagrantfile* disponível nos documentos da unidade curricular de DevOps, foram feitas algumas alterações ao ficheiro:

Na máquina **host2**, foi instalado o PostgreSQL bem como as dependências necessárias para correr a base de dados. Adicionalmente, alterou-se o porto para o *default* do postgreSQL - 5432. As configurações da VM do *host2* são as seguintes:

```
config.vm.define "host2" do |host2|
  host2.vm.box = "envimation/ubuntu-xenial"
  host2.vm.hostname = "host2"
  host2.vm.network "private_network", ip: "192.168.33.12"

  # We want to access H2 console from the host using port 8082
  # We want to connet to the H2 server using port 5432	
  host2.vm.network "forwarded_port", guest: 8082, host: 8082
  host2.vm.network "forwarded_port", guest: 5432, host: 5432

  host2.vm.provision "shell", inline: <<-SHELL
    sudo apt update
    sudo apt install postgresql postgresql-contrib -y
    sudo apt install libpq-dev -y
    sudo apt install python-psycopg2 -y
    sudo apt install nano -y
  SHELL
end    
```

Relativamente à VM do **host1**, foi adiconada a seguinte configuração que, apenas difere da orginal, pela alocção de mais memória:

```
config.vm.define "host1" do |host1|
  host1.vm.box = "envimation/ubuntu-xenial"
  host1.vm.hostname = "host1"
  host1.vm.network "private_network", ip: "192.168.33.11"

  # We set more ram memmory for this VM
  host1.vm.provider "virtualbox" do |v|
    v.memory = 2048
  end

  # We want to access tomcat from the host using port 8080
  host1.vm.network "forwarded_port", guest: 8080, host: 8080
end
```  

Na última VM, **ansible**, da mesma forma que na máquina *host1*, aumentou-se a memória da VM e foi instalado o ansible, bem como, as dependências necessárias para que o *software* corresse adequadamente. 

Foi preciso também instalar o Docker para ser possível concretizar as *stages* relativas a esta ferramente na *pipeline* do Jenkins. Adicionalmente, para correr o servidor do Jenkins foi preciso instalar o pacote jdk. A configuração desta VM está de acordo com a seguinte informação:

```
config.vm.define "ansible" do |ansible|
  ansible.vm.box = "envimation/ubuntu-xenial"
  ansible.vm.hostname = "ansible"
  ansible.vm.network "private_network", ip: "192.168.33.10"

  # We set more ram memmory for this VM
  ansible.vm.provider "virtualbox" do |v|
    v.memory = 2048
  end

  # For some Windows and for running ansible "inside" jenkins
  ansible.vm.synced_folder ".", "/vagrant", mount_options: ["dmode=777,fmode=777"]
  # It seems that ansible has security issues with the previous command. Use instead:
  ansible.vm.synced_folder ".", "/vagrant", mount_options: ["dmode=775,fmode=600"]

  # For acessing jenkins in 8081
  ansible.vm.network "forwarded_port", guest: 8081, host: 8081

  ansible.vm.provision "shell", inline: <<-SHELL
    sudo apt-get install -y --no-install-recommends apt-utils
    sudo apt-get install software-properties-common --yes
    sudo apt-add-repository --yes --u ppa:ansible/ansible
    sudo apt-get install ansible --yes
    sudo apt install git -y
    
  # For jenkins
    sudo apt-get install wget
    sudo wget http://mirrors.jenkins.io/war-stable/latest/jenkins.war
    sudo apt-get install openjdk-8-jdk-headless -y
    sudo java -jar jenkins.war --httpPort=8081
  SHELL
end
```

> De salientar que foi definido o porto 8081 no comando de execução do Jenkins para evitar a colisão de portos.

#### **2.3.1 Vagrant Up**

No terminal, no diretório *\devops_g2_maven\scripts* executou-se o seguinte comando para iniciar todas as VM:

```sh
$vagrant up
```

Com as máquinas criadas com sucesso, usou-se o comando *ssh* para entrar dentro da VM's.

#### **2.3.2 Host2: Postgress - Autorizar Acesso Remoto**

Antes de ser executado o *playbook* foi necessário fazer alguma configuração manual no *host2* onde está instalada a base de dados **PostgreSQL**. Dado que o **PostgreSql** está configurado para receber apenas conexões locais, de forma a permitir acesso remoto foi necessário alterar os ficheiros **postgresql.conf** e **pg_hba.conf**.

Para entrar dentro do *host2* executou-se o seguinte comando:

```sh
$vagrant ssh host2
```

Depois, fez-se *cd /vagrant* e executou-se o seguinte comando, de forma a alterar a *password* do utilizador *postres* para *postgres*:

```sh
sudo -u postgres psql postgres
```

De seguida, no ficheiro *postgresql.conf*  editou-se a linha *listen_addresses = 'localhost'* para listen_addresses = '*' através do comando seguinte:

```sh
sudo nano /etc/postgresql/9.5/main/postgresql.conf
```
Desta forma, qualquer endereço de IP pode ser aceite.

Por fim, alterou-se o ficheiro *pg_hba.conf*, que contém informação sobre os utilizadores e *hosts* da base de dados, alterou-se o parâmetro *ADDRESS* do endereço *IPv4* para *all* atráves do seguinte comando:

```sh
sudo nano /etc/postgresql/9.5/main/pg_hba.conf
```
No fim,  reiniciou-se o servidor do postgresql através do seguinte comando:

 ```
 sh sudo /etc/init.d/postgresql restart
 ```

> **Atenção**: Esta foi solução alternativa para correr o servidor da base de dados. Idealmente, as tarefas deveriam ser automatizadas com recurso ao *playbook*. No entanto, algumas destas tarefas não estavam a ser bem sucedidas. O *playbook* inicial pode ser encontrado no seguinte *link*: ttps://bitbucket.org/martalribeiro/devops_g2_maven/src/master/scripts/playbookOriginal.yml

### **2.4 Ansible**

#### **2.4.1 Configuração dos hosts**

Antes de executar o *playbook*, é necessário fornecer ao Ansible informação sobre os servidores para realizar a conexão *ssh*. Esta informação deve ser especificada dentro de um ficheiro *Inventory* que, por defeito está localizado em */etc/ansible/hosts*.

Cada servidor necessita de um nome para ser identificado pelo Ansible, neste caso, **host1** e **host2**. Também é necessário adicionar um *host* e um porto (22 para ssh) e, ainda, uma *private key file* usada pelo *ssh*. Neste cenário, no *inventory* está indicado um *cluster* de *hosts* que se chama **[otherservers]** sobre o qual o *playbook* é ser aplicado.

Este ficheiro encontra-se dentro da pasta */devops_g2_maven/scripts* como designação de *hosts* e contém a seguinte informação:

```sh
host1 ansible_ssh_host=192.168.33.11 ansible_ssh_port=22 ansible_ssh_user=vagrant ansible_ssh_private_key_file=/vagrant/.vagrant/machines/host1/virtualbox/private_key
host2 ansible_ssh_host=192.168.33.12 ansible_ssh_port=22 ansible_ssh_user=vagrant ansible_ssh_private_key_file=/vagrant/.vagrant/machines/host2/virtualbox/private_key
```

Depois, no ficheiro de configuração *ansible.cfg*, que pode ser criado no diretório do projeto junto do *playbook*, dentro da secção **[defaults]** está a indicação do ficheiro *inventory* que lista os *hosts* e, adicionalmente, o utilizador *default* para ser consumido pelo *ansible_playbook* (caso contrário o Ansible vai recorrer ao utilizador atual). O ficheiro tem a seguinte informação:

```sh
[defaults]
inventory = /vagrant/hosts
remote_user = vagrant
```

#### **2.4.2 Criação do Playbook**
Primeiramente, criaram-se *tasks* para o grupo *otherservers*, que agrega o *host1* e o *host2*, onde vão ser instalados os *packages* em comum para os dois *hosts*, recorrendo-se ao módulo *apt*

```sh
- hosts: otherservers
  become: yes
  tasks:
    - name: update apt cache
      apt: update_cache=yes
    - name: remove apache
      apt: name=apache2 state=absent
    - name: install jdk
      apt: name=openjdk-8-jdk-headless state=present
```

De seguida, criou-se a *play* para o *host2*, onde apenas se verifica se o servidor PostgreSQL está a correr, uma vez que a configuração foi feita através do Vagrant. 

> De referir que tanto a ordem das *tasks* como a dos *plays* importam no *playbook* e, por isso, a *play* da base de dados deve ser configurada em primeiro lugar.


```sh
- hosts: host2
  become: yes
  tasks:
    - name: Ensure the PostgreSQL service is running
      service: name=postgresql state=started enabled=yes
```      

> **Nota**: Com o 'become: yes' ativa-se a escalamento de privilégios de utilizadores para *sudo*.

Por fim, criou-se a *play* para o *host1* que contém as *tasks* necessárias para fazer o *deploy* da aplicação e que consistem em:

 * Instalar os *packages* necessários para configurar o host e fazer o *deploy* da aplicação;
 * Fazer um clone do repositório para a VM do host1;
 * Dar permissão de execução para fazer o *maven build* da aplicação;
 * Dar permissão de execução do artefacto gerado pelo *build*;
 * Executar o ficheiro .war para lançar a aplicação.

```sh
- hosts: host1
  become: yes
  tasks:
    - name: install git
      apt: name=git state=present
    - name: install nodejs
      shell: apt-get install nodejs -y
    - name: install npm
      shell: apt-get install npm -y
    - name: create symbolic link
      file:
        src: /usr/bin/nodejs
        dest: /usr/bin/node
        state: link
    - name: clean existent repository
      shell: rm -r -f /home/vagrant/devops_g2_maven
    - name: clone repository
      git:
        repo: https://martalribeiro@bitbucket.org/martalribeiro/devops_g2_maven.git
        dest: /home/vagrant/devops_g2_maven
    - name: give permission to execute maven wrapper
      file:
        path: /home/vagrant/devops_g2_maven/personalFinanceManagement/mvnw
        mode: 0755
    - name: maven build
      shell:
        cmd: ./mvnw package -Dmaven.test.skip=true
        chdir: /home/vagrant/devops_g2_maven/personalFinanceManagement
    - name: give permission to execute war file
      file:
        path: /home/vagrant/devops_g2_maven/personalFinanceManagement/target/devOpsProject-1.0-SNAPSHOT.war
        mode: 0755
    - name: deploy the war file
      shell: java -jar /home/vagrant/devops_g2_maven/personalFinanceManagement/target/devOpsProject-1.0-SNAPSHOT.war
      async: 2592000
      poll: 0
```
 > Para correr o *playbook*, após as configurações dos *hosts*, pode se entrar na VM do Ansible, e no diretório */vagrant* executar o seguinte comando:

```sh
$ansible-playbook playbook1.yml
```

### **2.5 Docker**
#### **2.5.1 Dockerfile**
Foram criados dois arquivos de configuração:
* Docker db - publicar a imagem da base de dados;
* Docker web - publicar a imagem da aplicação web.

##### Docker Db
 O *dockerfile* relativo à DB dispõe a seguinte informação:
 
```sh
FROM ubuntu

ENV TZ=Europe/Minsk
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apt-get update -y && \
  apt install postgresql postgresql-contrib -y && \
  apt install libpq-dev -y && \
  apt-get install python3-pip -y && \
  pip3 install psycopg2 && \
  apt install nano -y

EXPOSE 8082
EXPOSE 5432
```
Na próxima secção, explica-se, de forma breve, as instruções deste ficheiro:

O ***FROM*** inicializa um novo estágio de construção e define a imagem base, neste caso, ubuntu, para as instruções subsequentes.

O comando ***RUN*** serve para executar os comandos dentro do *container* a partir da imagem base, neste caso, actualizou-se o sistema instalando os *packages* necessários para a base de dados.

Para expor a porta que o *Web Server* vai utilizar, usou-se o parâmetro ***EXPOSE*** com os números de porta - 8082 e 5432 - informando ao Docker as portas de rede que estão à escuta.

#### Docker Web
 O *dockerfile* relativo à Web dispõe a seguinte informação:
 
```sh
FROM ubuntu 
 
RUN apt-get update -y

RUN apt-get install -f

RUN apt-get install git -y

RUN mkdir -p /tmp/build

WORKDIR /tmp/build/

RUN git clone https://martalribeiro@bitbucket.org/martalribeiro/devops_g2_maven.git

WORKDIR /tmp/build/devops_g2_maven/personalFinanceManagement

RUN chmod +x ./mvnw

RUN ./mvnw package -Dmaven.test.skip=true

WORKDIR /tmp/build/devops_g2_maven/personalFinanceManagement/target

RUN cp devOpsProject-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080
```

Tal como no Docker DB, o ponto de partida da imagem criada com o *dockerfile* será a do ubuntu.
Através do *apt-get update* foram actualizados os repositórios do Ubuntu e foram igualmente executados outros comados ***RUN*** para a instalação do software necessário para a aplicação correr. De seguida, foi feito o clone do repositório remoto, seguido do *build* para que fosse gerado o *war* da aplicação e, posteriormente, expandido pelo Tomcat. 


![alt text](https://i.imgur.com/icu5Eh8.png "DockerHub")


### **2.6 Jenkinsfile**
O objectivo primordial desta tarefa consistiu na criação de uma *pipeline* em **Jenkins** que fosse capaz:

* Gerar um *war* da aplicação;
* Gerar e publicar *javadoc*;
* Executar testes (tanto os unitários, como os de integração) e disponibilizar esta documentação no próprio Jenkins;
* Fazer *build* de duas imagens **Docker** e publicá-las no *Docker Hub*;
* Fazer o *deploy* da aplicação com o **Ansible**.

#### **2.6.1 Stages do Jenkinsfile:**

#### **Stage 1 - Checkout**

```sh
stage('Checkout') {
  steps { 
      echo 'Checking out...'
      git url: 'https://martalribeiro@bitbucket.org/martalribeiro/devops_g2_maven.git/'
  }
}
```

Este primeiro *stage* teve como objetivo aceder ao repositório deste projeto, bem como o conteúdo do mesmo. Para isso, foi providenciado o *url* do repositório do Bitbucket a aceder, e uma mensagem para a consola, que permitiu compreender que este *stage* se encontra em execução.  

#### **Stage 2 - Assemble**

```sh
stage('Assemble') {
  steps {
      dir('personalFinanceManagement') {
          echo 'Building...'
          sh 'chmod +x mvnw'
          sh './mvnw package -Dmaven.test.skip=true '
      }
  }
}
```

Este *stage* teve como objetivo compilar o código do projeto para verificar que não existiam erros que impedissem o *build*. Para isso, em primeiro lugar, deu-se permissões de execução ao comando que executa o *maven wrapper*.

Tendo permissão para execução, foi possível então utilizar o comando "package" que vai permitir compilar e produzir o ficheiro de arquivo para o código e os testes. Uma vez que neste *stage* apenas se pretende o resultado da compilação, excluiram-se os testes, adicionando o argumento *-Dmaven.test.skip=true* ao comando.

#### **Stage 3 - Test**

```sh
stage('Test') {
  steps {
    dir('personalFinanceManagement') {
        echo 'Testing...'
        sh 'chmod +x mvnw'
        sh './mvnw surefire-report:report '
        archiveArtifacts 'target/site/*.html'
    }
    dir('personalFinanceManagement') {
        echo 'Generating code coverage...'
        sh 'chmod +x mvnw'
        sh './mvnw verify'
        archiveArtifacts 'target/site/jacoco/index.html'
    }
  }
}
```

Uma vez compilado o código da aplicação, foi necesário executar os testes unitários e de integração, obtendo-se o relatório dos mesmos. Neste *stage* verificou-se também a cobertura dos testes, produziu-se um documento que permite consultar o resultado deste stage.

Em primeiro lugar, deu-se permissões de execução ao *maven wrapper* tal como na *stage 2*, e depois produziu-se o relatório com os resultados destes testes. Definiu-se também que os resultados a publicar estavam presentes em *target/site/*.html*.

Em segundo lugar, após nova permisão de execução, usou-se o comando *verify* para obter a cobertura dos testes da aplicação. Definiu-se também que os resultados a publicar estavam presentes em *target/site/jacoco/index.html*.

#### **Stage 4 - Javadoc**

```sh
stage ('Javadoc'){
  steps {
    dir('personalFinanceManagement') {
        echo 'Generating javadocs...'
        sh 'chmod +x mvnw'
        sh './mvnw javadoc:javadoc'
        publishHTML ([
                    reportName: 'Javadoc',
                    reportDir: 'target/site/apidocs/',
                    reportFiles: 'index.html',
                    keepAll: true,
                    alwaysLinkToLastBuild: false,
                    allowMissing: true
        ])
    }
  }
}
```

O quarto *stage* teve como finalidade gerar o *Javadoc* para o código do projeto e disponibilizar o mesmo no Jenkins. Para tal, mais uma vez, deu-se permissões de execução ao *wrapper* e para gerar o *Javadoc*, foi utilizado o comando *javadoc:javadoc*. De seguida, foi executada a função *publishHTML*, onde se definiu as diferentes especificações do ficheiro a ser publicado, bem como diferentes definições para este ficheiro:

- **keepAll:true** : Colocado como *true* para arquivar todas os *builds* efetuadas com sucesso;
- **alwaysLinkToLastBuild: false** : Levou a que o último *build* só seja incluído no relatório, caso tenha sido efetuado com sucesso;
- **allowMissing:true** : Colocado como *true* para permitir que o *build* não falhe, caso o relatório não esteja presente.

#### **Stage 5 - Archiving**

```sh
stage('Archiving') {
  steps {
    dir('personalFinanceManagement') {
        sh 'dir'
        echo 'Archiving...'
        archiveArtifacts 'target/*.war'
    }
  }
}
```

Este *stage* foi necessário para arquivar os todos ficheiros gerados no Jenkins, estanto os documentos no diretório: */target*. Primeiro estableceu-se o acesso a esta pasta no Jenkinsfile e, depois, foram arquivados todos os ficheiros desta pasta com a extensão ".war" através da função *archiveArtifacts*.

#### **Stage 6 - Docker Image**

```sh
stage('Docker Image') {
    steps {
      dir ('scripts/web') {
        script {
            webAppImage = docker.build("raquelquerido/devops_g2_maven:web${env.BUILD_ID}")
        }
      }
      dir ('scripts/db') {
        script {
            dbImage = docker.build("raquelquerido/devops_g2_maven:db${env.BUILD_ID}")
        }
      }
  }
}
```

Este *stage* teve como objetivo criar duas imagens Docker com o conteúdo da aplicação e da base de dados. Para tal, foi necessário obter as imagens respectivas. Desta forma, é necessário utilizar o comando *docker.build*. Definiu-se a variável *webAppImage* para a imagem *Docker* da aplicação e *dbImage* para a imagem *Docker* da base de dados.

#### **Stage 7 - Publish Image**

```sh
stage('Publish Image') {
  steps {
    script{
        sh 'docker login -u="raquelquerido" -p="devops1313"'
        webAppImage.push()
        dbImage.push()
    }
  }
}
```

Após construção das duas imagens *Docker*, fez-se a publicação no *Docker Hub*. Para tal, foi necessário fornecer as credenciais de *login* do *Docker Hub* e, depois, definir o comando *.push()* para fazer o upload de ambas as imagens.

#### **Stage 8 - Ansible Deploy**

```sh
stage('Ansible Deploy') {
    steps {
      dir('scripts') {
          ansiblePlaybook (
              become: true,
              disableHostKeyChecking: true,
              inventory: 'hosts',
              playbook: 'playbook1.yml'
        )
      }
    }
  }
}
```

Por fim, a *stage Ansible Deploy* tem como funcionalidade fazer o *deploy* e a configuração da aplicação web. Para tal, acedeu-se ao diretório *scripts* e especificou-se os seguintes comandos:

- **become: true** :  Permite ao utilizador ter o escalamento de previlégios;
- **disableHostKeyChecking: true** : Permite a não verificação da chave pública do *host*;
- **inventory: 'hosts'** : Aponta para o ficheiro *inventory*;
- **playbook: 'playbook1.yml'** : Define o *playbook* a ser executado pelo Ansible.


#### **2.6.2 Configuração no jenkins server - job**

O Jenkins foi instalado na VM Ansible através do *vagrantfile* e foi disponibilizado no endereço http://localhost:8081.
Não foi necessário configurar as credenciais de acesso ao respositório uma vez que este é público. Inicialmente, apenas foram instalados os *plugins standard* do Jenkins.

Desta forma, foi possível proceder à criação de um novo *job* no Jenkins, designado ***devops_g2_maven***, no qual se acedeu à opção *pipeline* e foi escolhida a opção *pipeline script from SCM* (Git). Colocou-se o *url* do respositório remoto e, por fim, no *Script Path* colocou-se o diretório onde estava localizado o *Jenkinsfile*.


#### **2.6.3 Execução do build a partir do Jenkisnfile**

Após criação do job, é necessário aceder à página inicial do projeto e selecionar a funcionalidade ***Build Now*** na barra de tarefas do lado esquerdo da página. Desta forma, o build foi iniciado e acedeu-se à secção ***Console Output*** para poder verificar se ocorreram erros.

Numa primeira tentaitva, o *build* falhou na ***stage Checkout*** devido a um erro no acesso ao repositório remoto.

![](https://i.imgur.com/4eiW5BF.png "Erro pipeline Git")

Quando foi consultado o ***Console Output***, foi verificada a seguinte mensagem de erro:

````
Cloning repository https://martalribeiro@bitbucket.org/martalribeiro/devops_g2_maven.git/
 > git init /root/.jenkins/workspace/devops_g2_maven # timeout=10
ERROR: Error cloning remote repo 'origin'
...
Caused by: hudson.plugins.git.GitException: Error performing git command: git init /root/.jenkins/workspace/devops_g2_maven
...
Caused by: java.io.IOException: Cannot run program "git" (in directory "/root/.jenkins/workspace/devops_g2_maven"): error=2, No such file or directory
````

Esta mensagem ocorreu porque o Git não está instalado na *VM ansible* onde o Jenkins está a correr. Desta forma, adicionou-se o seguinte comando no vagrantfile para quando a VM for gerada, instalar o Git.

````
$ sudo apt install git -y
````

Após esta alteração a ***stage*** foi concluída com sucesso.

Numa segunda tentativa, o *build* falhou na ***stage Javadoc*** devido a um erro na publicação dos ficheiros HTML.

![](https://i.imgur.com/nBYN9uW.png "Erro pipeline HTML")

Quando foi consultado o ***Console Output***, foi verificada a seguinte mensagem de erro:

````
java.lang.NoSuchMethodError: No such DSL method 'publishHTML' found among steps
````

Esta mensagem ocorreu porque o comando *publishHTML* não estava a ser reconhecido. Desta forma, instalou-se o *plugin HTML publisher* na secção ***Gerir Plugins***.

![](https://i.imgur.com/UijqU0H.png "Plugin HTML")

Após esta alteração a ***stage*** foi concluída com sucesso.

Numa terceira tentativa, o *build* falhou na ***stage Docker Image*** devido a um erro no comando *docker.build*.

![](https://i.imgur.com/qlvelL4.png "Erro pipeline Docker")

Quando foi consultado o ***Console Output***, foi verificada a seguinte mensagem de erro:

````
groovy.lang.MissingPropertyException: No such property: docker for class: groovy.lang.Binding
````

Esta mensagem ocorreu porque o comando *docker* não estava a ser reconhecido. Desta forma, instalou-se o *plugin Docker Pipeline* na secção ***Gerir Plugins***.

![](https://i.imgur.com/5p1AtRb.png "Plugin Docker")

Adicionaram-se também os seguintes comandos no *vagrantfile* para quando a VM for gerada, instalar a ferramenta *Docker* na ***VM ansible*** onde o Jenkins está a ser executado.

````
# For docker
      sudo apt-get update
      sudo apt-get install apt-transport-https -y
      sudo apt-get install ca-certificates -y
      sudo apt-get install curl -y
      sudo apt-get install gnupg-agent -y
      sudo apt-get install software-properties-common -y
      curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
      sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
      sudo apt-get update
      sudo apt-get install docker-ce docker-ce-cli containerd.io -y
      sudo apt-get update
````

Após esta alteração a ***stage*** foi concluída com sucesso. Para aceder às imagem *Docker* apenas é necessário executar os seguintes comandos, com o Docker em execução:

````
$ docker pull raquelquerido/devops_g2_maven:web{build}
$ docker pull raquelquerido/devops_g2_maven:web{build}
````

Numa quarta tentativa, o *build* falhou na ***stage Ansible Deploy*** devido a um erro na execução do *playbook* do Ansible.

![](https://i.imgur.com/91R2tJE.png "Erro pipeline Assemble")

Quando foi consultado o ***Console Output***, foi verificada a seguinte mensagem de erro:

````
java.lang.NoSuchMethodError: No such DSL method 'ansiblePlaybook' found among steps
````

Esta mensagem ocorreu porque o comando *ansiblePlaybook* não estava a ser reconhecido. Desta forma, instalou-se o *plugin* Ansible na secção ***Gerir Plugins***.

![](https://i.imgur.com/2xGXuoY.png "Plugin Ansible")

Após esta alteração a **stage** foi concluída com sucesso.

> **Nota**: Nesta stage, apenas é feito o *deploy* da aplicação web. O *deploy* da base de dados foi já realizada aquando da execução do *vagrantfile*.


Após todas as alterações e correções necessárias, a pipeline correu com sucesso.

![](https://i.imgur.com/3RjLLSn.png "Pipeline com sucesso")

Assim, é possivel verificar o deploy da aplicação na máquina *host*, acedendo a um dos seguintes endereços:

````
http://localhost:8080/
http://192.168.33.11:8080/
````

É então possível navegar pela a aplicação através dos vários endereços *url* disponíveis. Da mesma forma, estando concluído o *deploy* da base de dados, esta passa a estar disponível no endereço **192.168.33.12**, no porto **5432**. Através do *pgAdmin* pode-se configurar a ligação de acesso ao servidor e assim aceder ao conteúdo da base de dados.


## **3. Análise, Design e Implementação da alternativa**

Para a alternativa, o projeto foi criado usando o Gradle e o *deploy* da aplicação e da base de dados deverá ser feito com o Docker.

### **3.1 Gradle**

Para converter de Maven para Gradle, executou-se o seguinte comando no diretório que contém o ficheiro *pom.xml*:

```sh
$gradle init
```

O comando converteu a compilação do Maven para uma compilação Gradle, gerando um ficheiro settings.gradle e build.gradle com as dependências e configurações que estavam definidas no *pom.xml*.

Depois, executando o comando *gradle build*, foi possível verificar que a aplicação está a compilar:  

```sh
$gradle build
```

Estando a compilar e, uma vez que a task necessária para correr os testes não é criada automaticamente com o *gradle init*, a mesma foi adicionada ao *build.gradle*:

```sh
 test {
    useJUnitPlatform()
}
```
Ao ser feito o *build* os testes foram executados, pelo que tudo funcionou corretamente e a compilação foi efetuada com sucesso.

Depois de executar a aplicação Spring com o Gradle não foi possível abrir a base de dados, uma vez que a mesma estava a ser criada em memória. Para abrir a base de dados H2 foi necessário passar a mesma para um ficheiro, adicionando a seguinte configuração ao *application.properties*:

```sh
spring.datasource.url=jdbc:h2:file:./data; DB_CLOSE_ON_EXIT=FALSE; AUTO_RECONNECT=TRUE; AUTO_SERVER=TRUE   
```

Com a aplicação a correr, foi possível abrir a base de dados no *browser*, com o seguinte *url*:

```sh
localhost:8080/h2-console   
```

Quando o *build* foi feito, foi possível verificar que a *task jar* foi executadae e que gerou um ficheiro *.jar* com a aplicação. No entanto, este tipo de ficheiros apenas contém bibliotecas, recursos e ficheiros acessórios, como ficheiros de propriedades.

Por essa razão, foi necessário adicionar o seguinte *plugin* ao *build.gradle*:

```sh
plugins {
      ...
    id 'war'
}
```

Este *plugin* criou uma *task war* que será executada com o *build* e criou um ficheiro *.war* com a aplicação. Este ficheiro contém a aplicação *web* que pode ser implementada em qualquer contentor *servlet/jsp*. Este tipo de ficheiro contém *jsp, html, javascript* e outros ficheiros necessários para o desenvolvimento de aplicações *web* e foi necessário, posteriormente, para correr a aplicação num contentor Docker.

### **3.2 Docker**

Ao contrário do projeto com maven, que fez o *deploy* para as VMs, o objetivo da alternativa foi fazer o *deploy* com o **Docker**, colocando a aplicação a correr em *containers*.

#### **3.2.1 Criação das Dockerfiles**
Primeiramente, foi necessário criar as Dockerfiles, que foram adaptadas das utilizadas para o ***Class Assignment 4*** para o caso da aplicação *web* e da base de dados.

A Dockefile relativa à ***web*** possui a seguinte informação:

```sh
FROM tomcat

RUN apt-get update -y
RUN apt-get install -f
RUN apt-get install git -y

RUN mkdir -p /tmp/build
WORKDIR /tmp/build/
RUN git clone https://raquelquerido@bitbucket.org/martalribeiro/devops_g2_gradle.git
WORKDIR /tmp/build/devops_g2_gradle/

RUN chmod +x ./gradlew
RUN ./gradlew clean build
RUN cp build/libs/devops_g2_gradle-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080
```

As únicas alterações foram:
- colocar no **git clone** o link para o repositório correcto;
- no **working directory** entrar na root do repositório clonado;
- mudar a ***path*** de cópia do ficheiro ***war*** para a path correspondente do projeto.

A Dockefile relativa à **base de dados** não sofreu alterações e possui a seguinte informação:

```sh
FROM ubuntu

RUN apt-get update -y && \
  apt-get install -y openjdk-8-jdk-headless && \
  apt-get install unzip -y && \
  apt-get install wget -y

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app/
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar

EXPOSE 8082
EXPOSE 9092

CMD java -cp ./h2-1.4.200.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists
```

A Dockefile relativa ao **ansible** consistiu apenas em comandos para instalação do Ansible. O objectivo seria correr o *playbook* neste *container* e possui a seguinte informação:

```sh
FROM ubuntu

RUN apt-get update -y
RUN apt-get install iputils-ping -y
RUN apt-get install python3 --yes
RUN apt-get install software-properties-common --yes
RUN apt-get install ansible --yes
RUN apt-add-repository --remove ppa:ansible/ansible
RUN apt-get update

EXPOSE 8081
```

Também o ficheiro **docker-compose.yml** foi adaptado do **Class Assignment 4** em que apenas foi adicionado o service relativo ao **ansible** com os portos "8081:8081";

```sh
version: '3'
services:
  web:
    build: web
    ports:
      - "8080:8080"
    networks:
      default:
        ipv4_address: 192.168.33.10
    depends_on:
      - "db"
  db:
    build: db
    ports:
      - "8082:8082"
      - "9092:9092"
    volumes:
      - ./data:/usr/src/data
    networks:
      default:
        ipv4_address: 192.168.33.11

  ansible:
    build: ansible
    ports:
      - "8081:8081"
networks:
  default:
    ipam:
      driver: default
      config:
        - subnet: 192.168.33.0/24
```


#### Alterações necessárias

Para que a nossa aplicação conseguisse utilizar o **tomcat** foi, nesta fase, necessário adicionar ao *build.gradle* os seguintes **plugins**:

```sh
 plugins {
    ...
    id 'org.springframework.boot' version '2.3.1.RELEASE'
    id 'io.spring.dependency-management' version '1.0.9.RELEASE'
 }
```
Assim como a seguinte **dependência:**
```sh
  dependencies {
    ...
    providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
 }
```
  
Foi também modificada a seguinte propriedade no *application.properties* para que a base de dados passasse a correr no servidor:
```sh
#spring.datasource.url=jdbc:h2:file:./data;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;AUTO_SERVER=TRUE
spring.datasource.url=jdbc:h2:tcp://192.168.33.11:9092/./jpadb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
```

Por fim foi criada a classe ***ServletInitializer*** que extende a *WebApplicationInitializer* e corre a *StringApplication* a partir do ficheiro war ao qual fizemos deploy no container da web.

![alt text](https://imgur.com/RVf7pDr.png)

#### **3.2.2 Docker-compose build & docker-compose up**

Uma vez construidas as **dockerfiles**, o **docker-compose.yml** e feitas as mudanças necessárias corremos o comando:

```sh
$ docker-compose build
```
Este vai ler o **docker-compose.yml** e procurar todos os serviços que contêm um *build* e correr um **docker build** para cada um deles. Neste caso, foram encontrados 3 serviços: *web*, *db* e *ansible*, fazendo assim 3 *docker builds*.

De seguida, iniciou-se os containers correspondentes e correu-se o comando:

```sh
$ docker-compose up
```

#### **3.2.3 Verificação no browser**

Após alguns minutos, conseguiu-se visualizar o **Spring Boot** a ser inicializado, significando que os links para aceder à *web* e à base de dados já estavam acessiveis pelo browser.

![alt text](https://imgur.com/EHvwG1f.png)

Para saber o **IP** da *VM* onde está a correr o docker, consultou-se o Docker Toolbox ou então correndo o comando:

```sh
$ docker-machine default ip
```

Utilizando este IP como base, conseguiu-se assim aceder ao componente ***web*** e realizar pedidos ao backend.

![alt text](https://imgur.com/BCcw3r0.png)

Pode-se também aceder à **base de dados** e visualizar todos os elementos que estão armazenados na mesma.

![alt text](https://imgur.com/iYdwPua.png)

### **3.3 Jenkinsfile**

Relativamente ao Jenkinsfile utilizado na alternativa, salientam-se aqui as diferenças nos diversos **Stages** relativamente ao projeto principal:

**Assemble**

Em vez dos comandos:
```
sh 'chmod +x mvnw'
sh './mvnw package -Dmaven.test.skip=true '
```
utilizaram-se os mesmos adaptados para o Gradle:
```
sh 'chmod +x gradlew'
sh './gradlew build -x test'
```

**Test**

No caso da alternativa, não se colocou a questão relativa à cobertura de testes, tendo sido empregues os seguintes *steps*:
```
echo 'Running tests...'
sh 'chmod +x gradlew'
sh './gradlew test'
sh 'touch build/test-results/test/*.xml'
junit 'build/test-results/test/*.xml'
```

**Javadoc**

Relativamente à versão Maven, foram apenas alterados os seguintes comandos:
```
sh 'chmod +x gradlew'
sh './gradlew javadoc'
```
e o diretório: ***'build/docs/javadoc/'***.

**Archiving**

No Stage Archiving apenas se alterou o diretório em *archiveArtifacts* de *'target/*.war'* para *'build/libs/'*.

**Docker Image**

Neste *stage* foi utilizado o outro repositório do *Docker Hub*, nomeadamente: **raquelquerido/devops_g2_gradle**.
Além disso, adicionou-se um *step* para o Ansible:
```
dir ('docker/ansible') {
  script {
      ansibleImage = docker.build("raquelquerido/devops_g2_gradle:ansible")
}
```

**Publish Image**

No Jenkinsfile, esta *stage* manteve-se igual à versão utilizada no Maven.

Por fim, na alternativa não se implementou o *stage* relativo ao **Ansible**, dado não ter sido elaborado um playbook.


## **4. Atribuição de tarefas**

A distribuição inicial das tarefas foi feita de forma aleatória por todos os elementos do grupo (conforme a tabela abaixo) e de acordo com as soluções que seriam necessárias implementar. Para isso, foram criadas *issues*, no Bitbucket, que correspondiam a essa distribuição. 

Contudo, todos os elementos do grupo contribuíram para a realização e implementação de todas as tarefas. Essa contribuição foi essencial para a conclusão das tarefas e para que todos tivessem uma melhor perceção e conhecimento de cada implementação.  

|     Tecnologia      |     Solução Principal              |     Solução Alternativa              |
|---------------------|------------------------------------|--------------------------------------|
|     Maven/Gradle    |     Marta Ribeiro                  |     João Cardoso e Marta Pinheiro    |
|     Vagrant         |     Alexandre Oliveira             |                -                     |
|     Docker          |     Elsa Almeida                   |     Raquel Santos                    |
|     Ansible         |     Marta Cardoso                  |     Diana Dias                       |
|     Jenkins         |     Marta Ribeiro e Gabriel Moço   |     Marta Pinheiro                   |


## **5. Referências**
* https://git-scm.com/book/pt-br/
* https://www.jenkins.io/doc/book/pipeline/
* https://www.jenkins.io/doc/book/pipeline/syntax/
* https://guides.gradle.org/executing-gradle-builds-on-jenkins/
* https://www.jenkins.io/doc/pipeline/tour/tests-and-artifacts/
* https://docs.gradle.org/current/userguide/what_is_gradle.html
* https://www.postgresql.org/about/
* https://www.postgresqltutorial.com/what-is-postgresql/
* https://askubuntu.com/questions/909277/avoiding-user-interaction-with-tzdata-when-installing-certbot-in-a-docker-contai
* https://docs.ansible.com/ansible/latest/user_guide/playbooks_async.html
* https://docs.ansible.com/ansible/latest/modules/deploy_helper_module.html
* https://docs.docker.com/engine/install/ubuntu/
* https://stormpath.com/blog/tutorial-spring-boot-war-files
* https://tableplus.com/blog/2018/10/how-to-start-stop-restart-postgresql-server.html
* https://www.cylindric.net/git/force-lf
* https://blog.apcelent.com/using-ansible-to-set-up-postgresql.html
* https://maven.apache.org/plugins/maven-war-plugin/usage.html
* https://jdbc.postgresql.org/download.html
* https://www.digitalocean.com/community/tutorials/how-to-install-and-use-postgresql-on-ubuntu-18-04-pt
* https://github.com/jackdb/pg-app-dev-vm/blob/master/Vagrant-setup/bootstrap.sh
* https://mvnrepository.com/artifact/org.postgresql/postgresql/42.2.14
* https://stackoverflow.com/questions/23287462/spring-boot-fails-to-load-datasource-using-postgresql-driver
* https://gist.github.com/Rocketeer007/f492f8d5e33ecb8b459a8aa6e5e3580e´
* https://dzone.com/articles/reporting-code-coverage-using-maven-and-jacoco-plu
* https://maven.apache.org/plugins/maven-javadoc-plugin/usage.html
* https://gradle.org/
