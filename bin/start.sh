JAVA_HOME=/smsplat/jdk1.5.0
PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:./lib/activation.jar:./lib/axis1.2.jar:./lib/axis.jar:./lib/axis-ant.jar:./lib/c3p0-0.9.1.2.jar:./lib/commons-collections-3[1].1.jar:./lib/commons-discovery-0.2.jar:./lib/commons-logging-1.0.4.jar:./lib/dom4j-1.6.1.jar:./lib/EUCPCommHTTPSingle.jar:./lib/hjhz.jar:./lib/hjhz_ws.jar:./lib/hjhzEngineMonitor.jar:./lib/HttpClient.jar:./lib/javolution.jar:./lib/jaxen-1.1.1.jar:./lib/jaxrpc.jar:./lib/jdbm.jar:./lib/jtds-1.0.3.jar:./lib/log4j-1.2.8.jar:./lib/ojdbc14.jar:./lib/saaj.jar:./lib/smproxy.jar:./lib/spring.jar:./lib/uuid-2.1.3.jar:./lib/wsdl4j-1.5.1.jar:./lib/xerces-2.4.0.jar:./lib/xercesImpl.jar:./lib/xml-apis.jar:./lib/commons-lang-2.1.jar

java -mx256M -cp $CLASSPATH com.hjhz.mission.MissionCenter
