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
	
   }
   
   public void testYourSecondPartition(){
	   
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
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

}
