# Crud criado no desafio 21 dias de Java


# deploy
```shell
####### aws rds #######
usuario: admin
senha: a2d3m4o5i1n1
host: desafiojava.meubanco.amazonaws.com
database: desafio21diasjava

MYSQL_HOST="desafiojava.meubanco.amazonaws.com"
MYSQL_DATABASE="desafio21diasjava"
MYSQL_USER="admin"
MYSQL_PASSWORD="a2d3m4o5i1n1"

mysql -u'admin' -p'a2d3m4o5i1n1' -h'desafiojava.meubanco.amazonaws.com'
mysql> show databases;
mysql> create database desafio21diasjava;
mysql> show databases;

####### azure rds #######
usuario: desafioadmin@desafiojava
senha: a5i1n12d3m&4o
host: desafiojava.meubanco.azure.com
database: desafio21diasjava

MYSQL_HOST="desafiojava.meubanco.azure.com"
MYSQL_DATABASE="desafio21diasjava"
MYSQL_USER="desafioadmin@desafiojava"
MYSQL_PASSWORD="a5i1n12d3m&4o"


mysql -u'desafioadmin@desafiojava' -p'a5i1n12d3m&4o' -h'desafiojava.meubanco.azure.com'
mysql> create database desafio21diasjava;
mysql> show databases;
mysql> create database desafio21diasjava;
mysql> show databases;

```

# Apache server site complete app
``` shell
apt install apache2
a2enmod proxy
a2enmod proxy_http

vim /etc/apache2/sites-available/000-default.conf

### dentro do arquivo ###
<VirtualHost *:80>
        ProxyPreserveHost On

        ProxyPass / http://localhost:8080/
        ProxyPassReverse / http://localhost:8080/
</VirtualHost>
```


# Apache api app
``` shell
apt install apache2

vim /etc/apache2/sites-available/000-default.conf

### dentro do arquivo ###
<VirtualHost *:80>
        ServerAdmin webmaster@localhost
        DocumentRoot /var/www/html

        ErrorLog ${APACHE_LOG_DIR}/error.log
        CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>

```


# Apache app docker
``` shell
sudo su -
apt update
apt instal docker.io
apt install docker-compose
apt install maven

mvn package -Dmaven.test.skip

docker build -t didox/crud-java-desafio .

docker compose up --datach

```



# Nginx proxy pass
``` shell
apt install nginx

vim /etc/nginx/sites-available/default

### dentro do arquivo ###
``` nginx
server {
    listen 80 default_server;
    listen [::]:80 default_server;
    
    location = / {
        proxy_pass http://localhost:8080/;
    }
}
```
