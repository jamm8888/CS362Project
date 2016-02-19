import java.util.ArrayList;

/**
 * Creates a set of UrlParts as ArrayLists.
 */
public class UrlSet {
	
	ArrayList<UrlPart> urlScheme;
	ArrayList<UrlPart> urlPostScheme;
	ArrayList<UrlPart> urlHierPart;
	ArrayList<UrlPart> urlHost;
	ArrayList<UrlPart> urlTld;
	ArrayList<UrlPart> urlPort;
	ArrayList<UrlPart> urlPath;
	ArrayList<UrlPart> urlQuery;
	ArrayList<UrlPart> urlFragment;
	
	/**
	 * Initializes the UrlSet
	 */
	public UrlSet(){

		urlScheme = new ArrayList<UrlPart>();
		urlPostScheme = new ArrayList<UrlPart>();
		urlHierPart = new ArrayList<UrlPart>();
		urlHost = new ArrayList<UrlPart>();
		urlTld = new ArrayList<UrlPart>();
		urlPort = new ArrayList<UrlPart>();
		urlPath = new ArrayList<UrlPart>();
		urlQuery = new ArrayList<UrlPart>();
		urlFragment = new ArrayList<UrlPart>();
		
	}
	
	/**
	 * adds a part to an ArrayList for scheme given the part and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public void addScheme(String thisPart, boolean isValid){
		urlScheme.add(new UrlPart(thisPart, isValid));	
	}
	
	/**
	 * adds a part to an ArrayList for postScheme given the part and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public void addPostScheme(String thisPart, boolean isValid){
		urlPostScheme.add(new UrlPart(thisPart, isValid));	
	}
	
	/**
	 * adds a part to an ArrayList for hierPart given the part and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public void addHierPart(String thisPart, boolean isValid){
		urlHierPart.add(new UrlPart(thisPart, isValid));	
	}
	
	/**
	 * adds a part to an ArrayList for Host given the part and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public void addHost(String thisPart, boolean isValid){
		urlHost.add(new UrlPart(thisPart, isValid));	
	}
	
	/**
	 * adds a part to an ArrayList for tld given the part and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public void addTld(String thisPart, boolean isValid){
		urlTld.add(new UrlPart(thisPart, isValid));	
	}
	
	/**
	 * adds a part to an ArrayList for port given the part and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public void addPort(String thisPart, boolean isValid){
		urlPort.add(new UrlPart(thisPart, isValid));	
	}
	
	/**
	 * adds a part to an ArrayList for path given the part and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public void addPath(String thisPart, boolean isValid){
		urlPath.add(new UrlPart(thisPart, isValid));	
	}
	
	/**
	 * adds a part to an ArrayList for query given the part and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public void addQuery(String thisPart, boolean isValid){
		urlQuery.add(new UrlPart(thisPart, isValid));	
	}
	
	/**
	 * adds a part to an ArrayList for fragment given the part and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public void addFragment(String thisPart, boolean isValid){
		urlFragment.add(new UrlPart(thisPart, isValid));	
	}
}
