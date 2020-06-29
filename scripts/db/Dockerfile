FROM ubuntu

RUN apt-get update -y && \
  apt install postgresql postgresql-contrib -y && \
  apt install libpq-dev -y && \
  apt install python-psycopg2 -y && \
  apt install nano -y

EXPOSE 8082
EXPOSE 5432


