package texasHoldemPoker.Common;

import java.io.File;

import texasHoldemPoker.Model.Card;
import texasHoldemPoker.Model.PokerCard;
import texasHoldemPoker.Model.Suit;

public class FileHelper {
	
	private final static String ImageExtension = ".png";
	
	private String filename;
	
	public FileHelper(String filename)
	{
		this.setFilename(filename);
	}
	
	public boolean fileExists()
	{
		File file = new File(this.getFilename());
		return file.exists() ? true : false;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public static String getImageCard(PokerCard pokerCard) {
		Card card = pokerCard.getCard();
		Suit suit = pokerCard.getSuit();
		
		int cardOrdinal = card.ordinal() + 1;
		
		String cardSuit = suit.toString().substring(0, 1);
		
		String image = Integer.toString(cardOrdinal) + cardSuit;
		return getImagePath(image);
	}
	
	public static String getProjectPath()
	{
		String path = System.getProperty("user.dir");
		return path;
	}
	
	private static String getImagePath(String image) {
		return FileHelper.getProjectPath() + "\\src\\texasHoldemPoker\\UI\\Images\\" + image + ImageExtension;
	}
}
