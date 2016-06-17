#!/bin/bash

echo "Building application..."
mvn clean install -DskipTests

echo "********************************************"
echo "Removing old deployment files..."
rm -f $JBOSS_HOME/standalone/deployments/mt.ear.*
rm -f $JBOSS_HOME/standalone/deployments/mt.ear
rm -R -f $JBOSS_HOME/standalone/deployments/mt.ear

echo "********************************************"
echo "Copying new .ear to deployment folder..."
cp mt-ear/target/mt.ear $JBOSS_HOME/standalone/deployments

touch $JBOSS_HOME/standalone/deployments/mt.ear.dodeploy

echo "********************************************"
echo "Application built and deployed successfully!"
echo "********************************************"
