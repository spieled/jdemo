FROM tifayuki/java:7
MAINTAINER Golfen Guo <golfen.guo@daocloud.io>

RUN apt-get update && \
    apt-get install -yq --no-install-recommends wget pwgen ca-certificates && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

ENV TOMCAT_MAJOR_VERSION 7
ENV TOMCAT_MINOR_VERSION 7.0.55
ENV MAVEN_MAJOR_VERSION 3
ENV MAVEN_MINOR_VERSION 3.3.9
ENV CATALINA_HOME /tomcat
ENV M2_HOME /maven

# INSTALL TOMCAT
RUN wget --no-check-certificate -q https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_MAJOR_VERSION}/v${TOMCAT_MINOR_VERSION}/bin/apache-tomcat-${TOMCAT_MINOR_VERSION}.tar.gz && \
    wget --no-check-certificate -qO- https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_MAJOR_VERSION}/v${TOMCAT_MINOR_VERSION}/bin/apache-tomcat-${TOMCAT_MINOR_VERSION}.tar.gz.md5 | md5sum -c - && \
    wget --no-check-certificate -q http://mirrors.cnnic.cn/apache/maven/maven-${MAVEN_MAJOR_VERSION}/${MAVEN_MINOR_VERSION}/binaries/apache-maven-${MAVEN_MINOR_VERSION}-bin.tar.gz && \
    wget --no-check-certificate -qO- https://www.apache.org/dist/maven/maven-${MAVEN_MAJOR_VERSION}/${MAVEN_MINOR_VERSION}/binaries/apache-maven-${MAVEN_MINOR_VERSION}-bin.tar.gz.md5 | md5sum -c - && \
    tar zxf apache-maven-*-bin.tar.gz && \
    tar zxf apache-tomcat-*.tar.gz && \
    rm apache-tomcat-*.tar.gz && \
    mv apache-tomcat* tomcat && \
    rm apache-maven-*-bin.tar.gz && \
    mv apache-maven* maven

ADD create_tomcat_admin_user.sh /create_tomcat_admin_user.sh
ADD run.sh /run.sh
RUN chmod +x /*.sh
RUN ${M2_HOME}/bin/mvn package
ADD target/*.war ${CATALINA_HOME}/webapps/ROOT.war

EXPOSE 8080
CMD ["/run.sh"]
