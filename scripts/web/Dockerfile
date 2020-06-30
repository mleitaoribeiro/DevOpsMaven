FROM tomcat

RUN apt-get update -y

RUN apt-get install -f

RUN apt-get install git -y

RUN apt-get install openjdk-8-jdk-headless -y

RUN mkdir -p /tmp/build

WORKDIR /tmp/build/

RUN git clone https://martalribeiro@bitbucket.org/martalribeiro/devops_g2_maven.git

WORKDIR /tmp/build/devops_g2_maven/personalFinanceManagement

RUN chmod +x ./mvnw

RUN ./mvnw package -Dmaven.test.skip=true

WORKDIR /tmp/build/devops_g2_maven/personalFinanceManagement/target

RUN cp devOpsProject-1.0-SNAPSHOT.war /var/lib/tomcat8/webapps

EXPOSE 8080
