
public class Main {

	public static void main(String[] args) {
		UrlValidator urlValidator = new UrlValidator();
		int i;
		
		String[] urls = {
		    "htps://www.google.com",
			"https://google.com/something.php",
			"https://google.com/something.php",
			"https://mail.google.com/mail/u/0/#inbox",
			"https://oregonstate.instructure.com/courses/1568425/assignments/6656123?module_item_id=16591766",
			"https://oregonstate.instructure.com/courses//assignments/6656123?module_item_id=16591766",
			"https//oregonstate.instructure.com/courses/",
			"https://oregonstate.instructure/courses",
			"http://oregonstate.instructure.com/courses/1568425/",
			"http://localhost:8080/_ah/api/explorer"};
		
		String[] shouldBe = {
				"invalid",
				"valid",
				"valid",
				"valid",
				"valid",
				"invalid",
				"invalid",
				"invalid",
				"valid",
				"valid"};
		
		for (i = 0; i < urls.length; i++) {
			if (urlValidator.isValid(urls[i])) {
				 System.out.println("url is valid");
			} else {
			     System.out.println("url is invalid");
			}
		}
		
		System.out.println("Answers should be:");
		for (i = 0; i < shouldBe.length; i++) {
			System.out.println(shouldBe[i]);
		}

	}

}
