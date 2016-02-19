import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import junit.framework.Test;

/**
 * Class to generate the TWayCombinations
 */
public class TWayIndex {	
	
	public Boolean DEBUG = false;
	private ArrayList<ArrayList<Integer>> indexArray;
	
	String lineToProcess;
	
	/**
	 * TWayIndex creates a new 2D ArrayList of integers and reads indexes
	 * in from the file.
	 * @throws FileNotFoundException
	 * @param fName fileName to get the indexes from
	 */
	public TWayIndex(String fName) throws FileNotFoundException {
		indexArray = new ArrayList<ArrayList<Integer>>();
		readIndexFile(fName);
	}
	
	/**
	 * readIndexFile reads a file of indexes into a 2D ArrayList
	 * @param fName
	 * @throws FileNotFoundException
	 */
	public void readIndexFile(String fName) throws FileNotFoundException{
		
		URL url = Test.class.getClassLoader().getResource(fName);
		
		// Since this is a testcase we need to get the path to the file in
		// the test environment
		// http://stackoverflow.com/questions/2792870/java-cant-find-file-when-running-through-eclipse
		File fileName = new File(url.getPath());
		
		if(fileName.exists() && !fileName.isDirectory())
		{
			// Scan the file and get the line
			Scanner indexFile = new Scanner(fileName);
			int lineCount = 0;
			int nextInt;

			while(indexFile.hasNextLine()){
			
				// While getting each line scan each value delimited by ,
				indexArray.add(new ArrayList<Integer>());
				lineToProcess = indexFile.nextLine();
				
				Scanner delimitData = new Scanner(lineToProcess);
				delimitData.useDelimiter(",");
				
				while(delimitData.hasNextInt()){
					
					// Add the value to the array
					nextInt = delimitData.nextInt();
					((ArrayList<Integer>) indexArray.get(lineCount)).add(nextInt);
				}
				
				// close delimit Scanner
				delimitData.close();
				lineCount++;
				
			}
			
			// close file Scanner
			indexFile.close();
		}
	}
	
	/**
	* Function: isIP4
	* Description:  checks if a string has 4 x digit sets separated by dots
    * @param ip
    * @return true if yes,false if no
    */
	public boolean isIP4(String ip){
		
		// if null or 0 length return false - not an ip
		if(ip == null || ip.length() <= 0)
			return false;
	   
		String[] ipParts = ip.split(".");
	   
		// if less than 4 parts after splitting not an ip
		if(ipParts.length != 4)
			return false;
	   
		// if any of the parts are not numeric - not an ip
		// Since this check is only to verify if tld is req
		// we only check for digits
		for(int i = 0; i < ipParts.length; i++){
			
			if(!Pattern.matches("/^[0-9+$/",ipParts[i]))
			   return false;		  
			
		}
	    
		// nothing else to check - is an ip format
		return true;
	   
	}
	
	/**
	 * getUrl gets parts and generates a url using an index to search in the 
	 * 2D ArrayList indexArray
	 * @param urlSet Arraylist of UrlParts
	 * @param index to search indexArray for values
	 * @return UrlPart with url and expected result
	 */
	public UrlPart getUrl(UrlSet urlSet,int index){
		
		Boolean isValid;
		Boolean isValidHost;
		String hostName;
		String tldPart;
		
		String urlFull;
		
		// get the set of indexes for the url parts at the index in indexArray
		// specified
		ArrayList<Integer> indexes = indexArray.get(index);
	
		// check if the hostname is an IP if so TLD can be blank and pass
		hostName = ((UrlPart) urlSet.urlHost.get(indexes.get(3))).part;
		tldPart = ((UrlPart) urlSet.urlTld.get(indexes.get(4))).part;
		
		// if hostname is not null or empty
		if(((UrlPart) urlSet.urlHost.get(indexes.get(3))).part == "\0" || hostName.length() < 1){
			isValidHost = false;
		
			// if hostname is not null or empty and tld 
		} else if (tldPart.length() != 0 || tldPart == "\0"){
			
			// if hostname is missing return false
			if(hostName.length() <= 0 || hostName == null){
				isValidHost = false;
			}
			
			// else hostname is not missing
			else{
				
				// add tld and check if valid as a combination as checker 
				// will read hostname www.test.com with tld null as valid.
				hostName = hostName + tldPart;
				if(hostName.matches("^([\\p{Alnum}\\-\\.]*)(:\\d*)?(.*)?"))
				{	
					isValidHost = true;	
				} else {					
					isValidHost = ((UrlPart) urlSet.urlHost.get(indexes.get(3))).expected;					
				}
			}	
			
			// else if ip like hostname with tld
		} else if(isIP4(hostName) == true && tldPart.length() != 0){
			isValidHost = true;
			// else all other cases use specified expected value
		} else {		
			isValidHost = ((UrlPart) urlSet.urlHost.get(indexes.get(3))).expected;
		}
		
		// put together the URL
		urlFull = ((UrlPart) urlSet.urlScheme.get(indexes.get(0))).part + 
				((UrlPart) urlSet.urlPostScheme.get(indexes.get(1))).part +
				((UrlPart) urlSet.urlHierPart.get(indexes.get(2))).part +
				((UrlPart) urlSet.urlHost.get(indexes.get(3))).part +
				((UrlPart) urlSet.urlTld.get(indexes.get(4))).part +
				((UrlPart) urlSet.urlPort.get(indexes.get(5))).part +
				((UrlPart) urlSet.urlPath.get(indexes.get(6))).part +
				((UrlPart) urlSet.urlQuery.get(indexes.get(7))).part +
				((UrlPart) urlSet.urlFragment.get(indexes.get(8))).part;
		
		// put together the truth value
		isValid = ((UrlPart) urlSet.urlScheme.get(indexes.get(0))).expected && 
				((UrlPart) urlSet.urlPostScheme.get(indexes.get(1))).expected &&
				((UrlPart) urlSet.urlHierPart.get(indexes.get(2))).expected &&
				isValidHost &&
				((UrlPart) urlSet.urlTld.get(indexes.get(4))).expected &&
				((UrlPart) urlSet.urlPort.get(indexes.get(5))).expected &&
				((UrlPart) urlSet.urlPath.get(indexes.get(6))).expected &&
				((UrlPart) urlSet.urlQuery.get(indexes.get(7))).expected &&
				((UrlPart) urlSet.urlFragment.get(indexes.get(8))).expected;
		
		// create a UrlPart with the data
		UrlPart url = new UrlPart(urlFull,isValid);
		return url;
		
	}
	
	/**
	 * getIndexCount counts the size of the indexArray - how many tests are 
	 * included
	 * @return
	 */
	public int getIndexCount(){
		return indexArray.size();
	}
	
}
