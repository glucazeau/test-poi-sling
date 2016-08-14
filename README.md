1. Download Sling 8 from http://www-us.apache.org/dist//sling/org.apache.sling.launchpad-8.jar
2. Start Sling: java -jar org.apache.sling.launchpad-8.jar
3. Open the Felix Web console: http://localhost:8080/system/console/bundles (admin/admin)
4. Download the following bundles and install them from the Felix Web console (button "Install/Update", then check "Start bundle" and "Refresh Packages":
  * Commons Codec: http://central.maven.org/maven2/commons-codec/commons-codec/1.10/commons-codec-1.10.jar
  * Apache Servicemix POI: http://central.maven.org/maven2/org/apache/servicemix/bundles/org.apache.servicemix.bundles.poi/3.14_1/
5. Set OUTPUT_DIR value in GetPowerpointDocumentServlet
6. Build and deploy the project, it will create a pptx from the Junit test
`mvn clean install -PautoInstallBundle`
7. Once the bundle is deployed and active, load this URL to create the document from Sling: http://localhost:8080/apps/test/poi.html

While the document from the JUnit contains a table, and can be opened with LibreOffice, the second one from Sling cannot be opened and ppt/slide1.xml is almost empty
