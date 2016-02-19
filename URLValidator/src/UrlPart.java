/**
 * UrlPart
 * Contains part or a full url, with Boolean expected and result
 */
public class UrlPart {
	
	String part;
	boolean expected;
	boolean result;
	
	/**
	 * UrlPart initializes a UrlPart given a url and truth value
	 * @param thisPart
	 * @param isValid
	 */
	public UrlPart(String thisPart, boolean isValid)
	{
		part = thisPart;
		expected = isValid;
	}
	
}
