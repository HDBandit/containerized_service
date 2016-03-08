package com.hdbandit.containerized_service.controller;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hdbandit.containerized_service.Application;

@RunWith(Arquillian.class)
public class HomeControllerTest {
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	@Deployment(testable = false) 
	  public static JavaArchive createDeployment() {
		JavaArchive[] libs = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().as(JavaArchive.class);

	      JavaArchive jar = ShrinkWrap
	              .create(JavaArchive.class, "home-controller.jar")
	              .addClass(Application.class)
	              .addClass(HomeController.class);

	      for (JavaArchive javaArchive : libs) { 
	          jar.merge(javaArchive);
	      }
	      
	      jar.addAsManifestResource(
	              new StringAsset(
	                      "Main-Class: com.hdbandit.containerized_service.Application"
	                              + LINE_SEPARATOR), "MANIFEST.MF");
	      
	      return jar;
	  }
	
	 @Test
	  public void myTest(@ArquillianResource URL base) {
		 Assert.assertTrue(true);
	 }

}
