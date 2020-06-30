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


