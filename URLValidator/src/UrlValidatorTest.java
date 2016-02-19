/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import junit.framework.TestCase;



/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.
   private boolean PRFAILONLY = true;
   private boolean DEBUG = false;
   
   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   public void testManualTest1()
   {
	   UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		
	   // A few URLs for Allow ALL
	   Url[] urls = {
			new Url("htps://www.google.com", false),
			new Url("https://google.com/something.php", true),
			new Url("https://google.com/something.php", true),
			new Url("https://mail.google.com/mail/u/0/#inbox", true),
			new Url("https://oregonstate.instructure.com/courses/1568425/assignments/6656123?module_item_id=16591766", true),
			new Url("https://oregonstate.instructure.com/courses//assignments/6656123?module_item_id=16591766",false),
			new Url("https//oregonstate.instructure.com/courses/",false),
			new Url("https://oregonstate.instructure/courses",false),
			new Url("http://oregonstate.instructure.com/courses/1568425/",true),
			new Url("http://localhost:8080/_ah/api/explorer",true),
			new Url("https://altavista.com",true),
			new Url("http://www.altavista.comwww.altavista.com",true),
			new Url("http://www",false),
			new Url("3tp://www.altavista.com",false),
			new Url("http://www.mydomain.com\test",false),
			new Url("ftps://www.mydomain.com/|value/file",false),
			new Url("http://altavista.com/index.php?iam=aparam",true),
			new Url("ftp://user@test.com:80",true),
			new Url("ftp://user:password@test.com:80",true),
			new Url("http://altavista.d/", false),
			new Url("http://google.de/index.html>file", false)
		};
		
		// check all schemes
		for (int i = 0; i < urls.length; i++) 
		{
			urls[i].result = urlValidator.isValid(urls[i].url);
		}
		
		for (int i = 0; i < urls.length; i++) 
		{
			String testResult = "PASS";
			
			if(urls[i].expected != urls[i].result)
			{
				testResult = "FAIL";
			}
			
			System.out.println(testResult);
			System.out.println("Url: " + urls[i].url);
			System.out.println("expected: " + urls[i].expected + " result: " + urls[i].result);
			System.out.println();
		}
  
	   //failure verification
	   //assertTrue(urlVal.isValid("http://altavista.com/index.php?iam=aparam&meto=alsoparam"));	   	   
	   //assertTrue(urlVal.isValid("http://myname@test.com:80"));
	   //assertTrue(urlVal.isValid("http://myname:mypass@test.com:80"));

	   
   }
   
   public void testManualTest2()
   {
	   	UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_LOCAL_URLS);
				
		// a few URLS for Allow local
		Url[] urls = {
			new Url("http://staff.ustc.edu.cn/~csli/graduate/algorithms/book6/528_a.gif", true),
			new Url("http://localhost/", true),
			new Url("https://news.google.com/", true),
			new Url("https://news.google.com/news/section?cf=all&pz=1&topic=w&siidp=15d80b47ba7f54d65c4eea90b676dc0c6c36&ict=ln", false),
			new Url("amazon.com", false),
			new Url("http://www.amazon.com/Cracking-Coding-Interview-6th-Programming/dp/0984782850/", true),
			new Url("http://www.amazon.com/Cracking-Coding-Interview-6th-Programming/dp/0984782850/ref=sr_1_1?s=books&ie=UTF8&qid=1455399403&sr=1-1&keywords=cracking+the+coding+interview", false),
			new Url("http://kcna.kp/kcna.user.home.retrieveHomeInfoList.kcmsf", false),
			new Url("http://www.kcna.kp/", true),
		};

		// check all schemes
		for (int i = 0; i < urls.length; i++) 
		{
			urls[i].result = urlValidator.isValid(urls[i].url);
		}
		
		for (int i = 0; i < urls.length; i++) 
		{
			String testResult = "PASS";
			
			if(urls[i].expected != urls[i].result)
			{
				testResult = "FAIL";
			}
			
			System.out.println(testResult);
			System.out.println("Url: " + urls[i].url);
			System.out.println("expected: " + urls[i].expected + " result: " + urls[i].result);
			System.out.println();
		}
			   
   	}

	/**
	 * Function: testTWayInputPartitionLocal
	 * Description:  tests a series of indexes with URL Validator set to ALLOW_LOCAL_URLS
	 * Indexes are identified in TWayIndex.java
	 * @throws FileNotFoundException
	 */ 
	public void testTWayInputPartitionLocal() throws FileNotFoundException
	{
		// Setup a URL Validator object
		UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_LOCAL_URLS);
		
		// Setup a UrlSet and initialize with partitions
		UrlSet urlSet = initializeTWayTestAllowLocal();
		UrlPart tempUrlPart;
		
		// Create a TWayIndex which will return a url based on the index provided
		TWayIndex urlIndex = new TWayIndex("twayIndexes.txt");
		
		// Create an arrayList to store the Urls in the event we need further inspection
		ArrayList<UrlPart> urlUnderTest = new ArrayList<UrlPart>(); 
		String pf = "PASS";
		
		System.out.println("STARTING TwoWayTest: ALLOW_LOCAL_URLS");
		
		for(int i = 0; i < urlIndex.getIndexCount(); i++){
			
			pf = "PASS";
			
			tempUrlPart = urlIndex.getUrl(urlSet, i);
			tempUrlPart.result = urlValidator.isValid(tempUrlPart.part);
			urlUnderTest.add(tempUrlPart);
			
			if(tempUrlPart.expected != tempUrlPart.result){
				pf = "FAIL";
			}
			
			if((PRFAILONLY == true && tempUrlPart.expected != tempUrlPart.result) || PRFAILONLY == false){
				System.out.println("Test: " + i + ", Status: " + pf);
				System.out.println("Note if null in url full url will stop printing at first null");
				System.out.println("URL: " + tempUrlPart.part);
				System.out.println("Expected: " + tempUrlPart.expected + ", Result: " + tempUrlPart.result);		
			} 
		}
		
		if(DEBUG)
			System.out.println(((UrlPart) urlSet.urlScheme.get(0)).part);
	
	}
	
	/**
	 * Function: testTWayInputPartitionLocal
	 * Description:  tests a series of indexes with URL Validator set to ALLOW_ALL_SCHEMES
	 * Indexes are identified in TWayIndex.java
	 * @throws FileNotFoundException
	 */
	
	public void testTWayInputPartitionAll() throws FileNotFoundException
	{
		// Setup a URL Validator object
		UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

		// Setup a UrlSet and initialize with partitions
		UrlSet urlSet = initializeTWayTestAllowLocal();
		UrlPart tempUrlPart;
		
		// Create a TWayIndex which will return a url based on the index provided
		TWayIndex urlIndex = new TWayIndex("twayIndexes.txt");
		
		// Create an arrayList to store the Urls in the event we need further inspection
		ArrayList<UrlPart> urlUnderTest = new ArrayList<UrlPart>(); 
		String pf = "PASS";

		
		System.out.println("STARTING TwoWayTest: ALLOW_ALL_SCHEMES");
		
		for(int i = 0; i < urlIndex.getIndexCount(); i++){

			pf = "PASS";
			
			tempUrlPart = urlIndex.getUrl(urlSet, i);
			tempUrlPart.result = urlValidator.isValid(tempUrlPart.part);
			urlUnderTest.add(tempUrlPart);
			
			if(tempUrlPart.expected != tempUrlPart.result){
				pf = "FAIL";
			}
			
			if((PRFAILONLY == true && tempUrlPart.expected != tempUrlPart.result) || PRFAILONLY == false){
				System.out.println("Test: " + i + ", Status: " + pf);
				System.out.println("Note if null in url full url will stop printing at first null");
				System.out.println("URL: " + tempUrlPart.part);
				System.out.println("Expected: " + tempUrlPart.expected + ", Result: " + tempUrlPart.result);
			} 
		}
		
		if(DEBUG)
			System.out.println(((UrlPart) urlSet.urlScheme.get(0)).part);
	
	}
   
   
   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
   }

   public void testAnyOtherUnitTest()
   {

   }
 
   public void testIsValidScheme()
   {
	   String[] allowedSchemes = {"http", "https", "ftp"};
	   UrlValidator urlValidator1 = new UrlValidator(allowedSchemes, 0);
	   int i;
	   boolean result;
	   
	   List<ResultPair> schemesToTest = new Vector();
	   schemesToTest.add(new ResultPair("http", true));
	   schemesToTest.add(new ResultPair("HTTP", true));
	   schemesToTest.add(new ResultPair("https", true));
	   schemesToTest.add(new ResultPair("HTTPS", true));
	   schemesToTest.add(new ResultPair("ftp", true));
	   schemesToTest.add(new ResultPair("FTP", true));
	   schemesToTest.add(new ResultPair("http:", false));
	   schemesToTest.add(new ResultPair("https:/", false));
	   schemesToTest.add(new ResultPair("localhost:", false));
	   schemesToTest.add(new ResultPair("gtp", false));
	   schemesToTest.add(new ResultPair("httpa", false));
	   schemesToTest.add(new ResultPair("fttp", false));
	   schemesToTest.add(new ResultPair("", true));
	   schemesToTest.add(new ResultPair("httphttp", false));
	   schemesToTest.add(new ResultPair("localhost", false));
	   schemesToTest.add(new ResultPair("LOCALHOST", false));
	   
	   
	   for (i = 0; i < schemesToTest.size(); i++) {
		   result = urlValidator1.isValidScheme(schemesToTest.get(i).item);
		   assertEquals(schemesToTest.get(i).item, schemesToTest.get(i).valid, result);
		   if (schemesToTest.get(i).item == "localhost" || schemesToTest.get(i).item == "LOCALHOST") {
			   schemesToTest.remove(i);
		   }
	   }
	   
	   UrlValidator urlValidator2 = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   schemesToTest.add(new ResultPair("file", true));
	   schemesToTest.add(new ResultPair("FILE", true));
	   schemesToTest.add(new ResultPair("localhost", true));
	   schemesToTest.add(new ResultPair("LOCALHOST", true)); 
	   
	   for (i = 0; i < schemesToTest.size(); i++) {
		   result = urlValidator2.isValidScheme(schemesToTest.get(i).item);
		   assertEquals(schemesToTest.get(i).item, schemesToTest.get(i).valid, result);
	   }
   }
   
   public void testIsValidFragment()
   {
	   UrlValidator urlValidator = new UrlValidator(UrlValidator.NO_FRAGMENTS);
	   List<ResultPair> fragments = new Vector();
	   fragments.add(new ResultPair("", true));
	   fragments.add(new ResultPair(null, true));
	   fragments.add(new ResultPair("something", false));
	   fragments.add(new ResultPair("?hello", false));
	   fragments.add(new ResultPair("1", false));
	   fragments.add(new ResultPair("http", false));
	   fragments.add(new ResultPair(".", false));
	   
	   int i;
	   boolean result;
	   
	   for (i = 0; i < fragments.size(); i++) {
		   result = urlValidator.isValidFragment(fragments.get(i).item);
		   assertEquals(fragments.get(i).item, fragments.get(i).valid, result);
	   }
	   
	   fragments.clear();
	   fragments.add(new ResultPair("", true));
	   fragments.add(new ResultPair(null, true));
	   fragments.add(new ResultPair("something", true));
	   fragments.add(new ResultPair("?hello", true));
	   fragments.add(new ResultPair("1", true));
	   fragments.add(new ResultPair("http", true));
	   fragments.add(new ResultPair(".", true));
	   
	   UrlValidator urlValidator2 = new UrlValidator();
	   
	   for (i = 0; i < fragments.size(); i++) {
		   result = urlValidator2.isValidFragment(fragments.get(i).item);
		   assertEquals(fragments.get(i).item, fragments.get(i).valid, result);
	   }
	   
   }
   
   /**
    * Initialize tests for TWay testing with Allow All Schemes
    * @return
    */
	public UrlSet initializeTWayTestAllowAll(){
	   
		UrlSet UrlSet = new UrlSet();
	   
		// add Schemes
		UrlSet.addScheme("ftp", true);
		UrlSet.addScheme("3fp", false);
		UrlSet.addScheme("file", true);
		UrlSet.addScheme("", false);
		UrlSet.addScheme("\0", false);
	   
		// add postSchemes
		UrlSet.addPostScheme(":", true);
		UrlSet.addPostScheme("$", false);
		UrlSet.addPostScheme("", false);
		UrlSet.addPostScheme("\0", false);
		
		// add hierParts
		UrlSet.addHierPart("//", true);
		UrlSet.addHierPart("/", false);
		UrlSet.addHierPart("", false);
		UrlSet.addHierPart("\0", false); // "\0"
	   
		// add Host
		UrlSet.addHost("www.test", true);
		UrlSet.addHost("3ww", false);
		UrlSet.addHost("192.168.0.1", true);
		UrlSet.addHost("256.256.256.256", false);
		UrlSet.addHost("", false);
		UrlSet.addHost("\0", false); // "\0"
	   
		// add Tld
		// load the valid Tlds
		// all Tlds will be tested in the programmatical section
		UrlSet.addTld(".arpa", true);
		UrlSet.addTld(".root", true);
		UrlSet.addTld(".aero", true);
		UrlSet.addTld(".museum", true);
		UrlSet.addTld(".ad", true);
		UrlSet.addTld(".jm", true);
		UrlSet.addTld(".tt", true);
		UrlSet.addTld(".localhost", false);
		UrlSet.addTld(".localdomain", false);
		UrlSet.addTld(".999", false);
		UrlSet.addTld(".", false);
		UrlSet.addTld("." + "\0", false); // "\0"
			   
		// add port
		UrlSet.addPort(":8080", true);
		UrlSet.addPort(":1234567", false);
		UrlSet.addPort(":abcd", false);
		UrlSet.addPort("", true);
		UrlSet.addPort(":" + "\0", false); // "\0"
	   
		// add path
		UrlSet.addPath("/path/to/something", true);
		UrlSet.addPath("/../path/to/something", true);
		UrlSet.addPath("/path//to/something", false);
		UrlSet.addPath("/...../path/to/something", false);
		UrlSet.addPath("", true);
		UrlSet.addPath("/" + "\0", false); // "\0" path is not allowed
	   
		// add query
		UrlSet.addQuery("?ima=aquery", true);
		UrlSet.addQuery("?|<>ima=query|=ami", true);
		UrlSet.addQuery("", true);
		UrlSet.addQuery("?" + "\0", true); // "\0" allowed
	   
		// add fragment
		UrlSet.addFragment("#fragment", true);
		UrlSet.addFragment("#", true);
		UrlSet.addFragment("#|", true);
		UrlSet.addFragment("", true);
		UrlSet.addFragment("#" + "\0", true); // "\0" allowed
		//UrlSet.addFragment("#"\0"", false); // "\0"
		
		return UrlSet;
	}

   /**
    * Initialize tests for TWay testing with Allow Local
    * @return
    */
	public UrlSet initializeTWayTestAllowLocal(){
		
		UrlSet UrlSet = new UrlSet();
		
		// add Schemes
		UrlSet.addScheme("ftp", true);
		UrlSet.addScheme("3fp", false);
		UrlSet.addScheme("file", false);
		UrlSet.addScheme("", false);
		UrlSet.addScheme("\0", false); // "\0"
		
		// add postSchemes
		UrlSet.addPostScheme(":", true);
		UrlSet.addPostScheme("$", false);
		UrlSet.addPostScheme("", false);
		UrlSet.addPostScheme("\0", false); // "\0"
		
		// add hierParts
		UrlSet.addHierPart("//", true);
		UrlSet.addHierPart("/", false);
		UrlSet.addHierPart("", false);
		UrlSet.addHierPart("\0", false); // "\0"
		
		// add Host
		UrlSet.addHost("www.test", true);
		UrlSet.addHost("3ww", false);
		UrlSet.addHost("192.168.0.1", true);
		UrlSet.addHost("256.256.256.256", false);
		UrlSet.addHost("", false);
		UrlSet.addHost("\0", false); // "\0"
		
		// add Tld
		// load the valid Tlds
		// all Tlds will be tested in the programmatical section
		UrlSet.addTld(".arpa", true);
		UrlSet.addTld(".root", true);
		UrlSet.addTld(".aero", true);
		UrlSet.addTld(".museum", true);
		UrlSet.addTld(".ad", true);
		UrlSet.addTld(".jm", true);
		UrlSet.addTld(".tt", true);
		UrlSet.addTld(".localhost", true);
		UrlSet.addTld(".localdomain", true);
		UrlSet.addTld(".999", false);
		UrlSet.addTld(".", false);
		UrlSet.addTld("." + "\0", false); // "\0"
		
		// add port
		UrlSet.addPort(":8080", true);
		UrlSet.addPort(":1234567", false);
		UrlSet.addPort(":abcd", false);
		UrlSet.addPort("", true);
		UrlSet.addPort(":" + "\0", false); // "\0"
		
		// add path
		UrlSet.addPath("/path/to/something", true);
		UrlSet.addPath("/../path/to/something", true);
		UrlSet.addPath("/path//to/something", false);
		UrlSet.addPath("/...../path/to/something", false);
		UrlSet.addPath("", true);
		UrlSet.addPath("/" + "\0", false); // "\0" path is allowed
		
		// add query
		UrlSet.addQuery("?ima=aquery", true);
		UrlSet.addQuery("?ima=query&=ami", true);
		UrlSet.addQuery("", true);
		UrlSet.addQuery("?" + "\0", true); // "\0" query is allowed
		
		// add fragment
		UrlSet.addFragment("#fragment", true);
		UrlSet.addFragment("#", true);
		UrlSet.addFragment("#\\", true);
		UrlSet.addFragment("", true);
		UrlSet.addFragment("#" + "\0", true); // "\0" fragment is allowed
		
		return UrlSet;
	}

}
