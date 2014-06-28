#!/bin/bash

echo "Building application..."
mvn clean install -DskipTests

echo "********************************************"
echo "Removing old deployment files..."
sudo rm -f $JBOSS_HOME/standalone/deployments/test.ear.*
sudo rm -f $JBOSS_HOME/standalone/deployments/test.ear
sudo rm -R -f $JBOSS_HOME/standalone/deployments/test.ear

echo "********************************************"
echo "Copying new .ear to deployment folder..."
sudo cp test-ear/target/test.ear $JBOSS_HOME/standalone/deployments

sudo touch $JBOSS_HOME/standalone/deployments/test.ear.dodeploy

echo "********************************************"
echo "Application built and deployed successfully!"
echo "********************************************"