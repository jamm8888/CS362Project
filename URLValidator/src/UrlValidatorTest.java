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


import java.util.List;
import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;



/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   int i;
	   boolean result, expected;

       String[][] urls = {
           {"htps://www.google.com", "false"},
           {"https://google.com/something.php", "true"},
           {"https://google.com/something.php", "true"},
           {"https://mail.google.com/mail/u/0/#inbox", "true"},
           {"https://oregonstate.instructure.com/courses/1568425/assignments/6656123?module_item_id=16591766", "true"},
           {"https://oregonstate.instructure.com/courses//assignments/6656123?module_item_id=16591766","false"},
           {"https//oregonstate.instructure.com/courses/","false"},
           {"https://oregonstate.instructure/courses","false"},
           {"http://oregonstate.instructure.com/courses/1568425/","true"},
           {"http://localhost:8080/_ah/api/explorer","true"},
           {"https://altavista.com","true"},
           {"http://www.altavista.comwww.altavista.com","true"},
           {"http://www","false"},
           {"3tp://www.altavista.com","false"},
           {"http://www.mydomain.com\test","false"},
           {"ftps://www.mydomain.com/|value/file","false"},
           {"http://altavista.com/index.php?iam=aparam","true"},
           {"ftp://user@test.com:80","true"},
           {"ftp://user:password@test.com:80","true"},
           {"http://altavista.d/", "false"},
           {"http://google.de/index.html>file", "false"}
       };
       
       for (i = 0; i < urls.length; i++) {
    	   
    	   result = urlVal.isValid(urls[i][0]);
    	   expected = Boolean.valueOf(urls[i][1].toString());
    	   
           if (result == expected) {
                System.out.println("Pass: Returned: " + expected + " url: " + urls[i][0]);
           } else {
                System.out.println("Fail: Returned:" + result + " Expected: " + expected + " url: " + urls[i][0]);
           }
	   }

	      
	   //failure verification
	   //assertTrue(urlVal.isValid("http://altavista.com/index.php?iam=aparam&meto=alsoparam"));	   	   
	   //assertTrue(urlVal.isValid("http://myname@test.com:80"));
	   //assertTrue(urlVal.isValid("http://myname:mypass@test.com:80"));
	   
   }
   
   

public void testYourFirstPartition()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   assertTrue(urlVal.isValid("http://www.amazon.com"));
	   
	   //verify partition
   }
   
   public void testYourSecondPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   assertTrue(urlVal.isValid("http://www.amazon.com"));
	   
	   //verify partition
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
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

}
