# [litecommerce ](http://teste.filho.xyz:8080/litecommerce)
An example light ecommerce application

## Running
Para gerar o `litecommerce.war` use o seguinte comando na raíz do projeto (assumindo que tens o Maven 3.X instalado):
```
$ mvn package
```
Depois copie o war para a pasta do apache
```
$ cp target/litecommerce.war apache-tomcat-8/webapps
```
E finalmente, inicie o Tomcat
```
$ ./apache-tomcat-8/bin/startup.sh
```

### Testado com o `apache-tomcat-8.0.36`
