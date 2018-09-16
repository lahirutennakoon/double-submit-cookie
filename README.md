# CSRF double submit cookie

This web application demonstrates how to protect your website against Cross-Site Request Forgery (CSRF) attacks using the double submit cookie pattern.
<br>
<br>

<b>Required technologies to run this application: </b>
<br>
<ul>
  <li>Java 8</li>
  <li>Maven</li>
</ul>

<br>

<b>Procedure:</b>
<ol>
  <li>Download or clone the project.</li>
  <li>Go to the project folder location from the command prompt/terminal.</li>
  <li>Type "mvn clean install" to compile the application.</li>
  <li>After compilation is complete, type "java -jar target/double-submit-cookie-0.0.1-SNAPSHOT.jar" to run the web application.</li>
  <li>Once the application has started, open a web browser and visit "http://localhost:8080/".</li>
</ol>

<br>

You can check out 
<a href="https://1techpro1.blogspot.com/2018/09/cross-site-request-forgery-csrf-prevention-using-double-submit-cookie.html">
this blog post
</a> to see the step-by-step guide on how to implement CSRF protection using the 
synchronizer token pattern.