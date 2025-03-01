FROM amd64/tomcat:8.5.98-jre11-temurin-focal
COPY /target/ROOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]