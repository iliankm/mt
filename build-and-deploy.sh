#!/bin/bash

echo "Building application..."
mvn clean install -DskipTests

echo "********************************************"
echo "Removing old deployment files..."
rm -f $WILDFLY_HOME/standalone/deployments/mt.ear.*
rm -f $WILDFLY_HOME/standalone/deployments/mt.ear
rm -R -f $WILDFLY_HOME/standalone/deployments/mt.ear

echo "********************************************"
echo "Copying new .ear to deployment folder..."
unzip mt-ear/target/mt.ear -d $WILDFLY_HOME/standalone/deployments/mt.ear
unzip $WILDFLY_HOME/standalone/deployments/mt.ear/ROOT.war -d $WILDFLY_HOME/standalone/deployments/mt.ear/ROOT
rm -f $WILDFLY_HOME/standalone/deployments/mt.ear/ROOT.war
mv $WILDFLY_HOME/standalone/deployments/mt.ear/ROOT $WILDFLY_HOME/standalone/deployments/mt.ear/ROOT.war

touch $WILDFLY_HOME/standalone/deployments/mt.ear.dodeploy

echo "********************************************"
echo "Application built and deployed successfully!"
echo "********************************************"
