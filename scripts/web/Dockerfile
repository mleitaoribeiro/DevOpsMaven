FROM ubuntu

RUN apt-get update -y

RUN apt-get install -f

RUN apt-get install git -y

RUN apt-get install nodejs -y

RUN apt-get install npm -y

RUN apt-get install iputils-ping -y && \

  apt-get install python3 --yes

RUN mkdir -p /tmp/build

WORKDIR /tmp/build/

RUN git clone https://martalribeiro@bitbucket.org/martalribeiro/devops_g2_maven.git

WORKDIR /tmp/build/devops_g2_maven/personalFinanceManagement

RUN chmod +x ./mvnw

RUN ./mvnw package -Dmaven.test.skip=true

WORKDIR /tmp/build/devops_g2_maven/personalFinanceManagement/target

RUN java -jar devOpsProject-1.0-SNAPSHOT.war

EXPOSE 8080
