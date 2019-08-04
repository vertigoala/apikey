#
# apikey is based on tomcat8 official image
# 
#
FROM tomcat:8.5-jre8-alpine

RUN mkdir -p /data \
	/data/apikey \
	/data/apikey/config

#ARG ARTIFACT_URL=http://192.168.0.14/web/apikey-1.5.war
ARG ARTIFACT_URL=https://nexus.ala.org.au/service/local/repositories/releases/content/au/org/ala/apikey/1.5/apikey-1.5.war
ARG WAR_NAME=apikey
ENV IMAGE_SERVICE_BASE_URL http://localhost:8080/apikey

RUN wget $ARTIFACT_URL -q -O /tmp/$WAR_NAME && \
    apk add --update tini && \
    mkdir -p $CATALINA_HOME/webapps/$WAR_NAME && \
    unzip /tmp/$WAR_NAME -d $CATALINA_HOME/webapps/$WAR_NAME && \
    rm /tmp/$WAR_NAME

# default properties
COPY ./data/apikey/config/apikey-config.properties /data/apikey/config/apikey-config.properties
COPY ./tomcat-conf/logback.groovy $CATALINA_HOME/webapps/$WAR_NAME/WEB-INF/classes/logback.groovy
# Tomcat configs
COPY ./tomcat-conf/* /usr/local/tomcat/conf/	

EXPOSE 8080

#COPY ./scripts/* /opt/

# db schema at https://github.com/AtlasOfLivingAustralia/ala-install/raw/master/ansible/roles/apikey/files/apikey_schema.sql

# NON-ROOT
RUN addgroup -g 101 tomcat && \
    adduser -G tomcat -u 101 -S tomcat && \
    chown -R tomcat:tomcat /usr/local/tomcat && \
    chown -R tomcat:tomcat /data

USER tomcat

#ENV CATALINA_OPTS '-Dgrails.env=production'

ENTRYPOINT ["tini", "--"]
CMD ["catalina.sh", "run"]
