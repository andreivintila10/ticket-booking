package ticket.booking;

import java.util.ArrayList;
import java.util.List;

import ticket.booking.dao.ScreenDAO;
import ticket.booking.dao.ScreenImpl;
import ticket.booking.pojo.Screen;

//Class to add records to the Screen table.
public class PopulateScreenDB {

	public static void main(String[] args) {
		List<Screen> screens = new ArrayList<Screen>();
		screens.add(new Screen(1, 100));
		screens.add(new Screen(1, 100));
		screens.add(new Screen(1, 150));
		screens.add(new Screen(1, 200));
		screens.add(new Screen(1, 250));

		ScreenDAO screenDAO = new ScreenImpl();
		for (Screen screen : screens)
			screenDAO.insertScreen(screen);
	}
}
