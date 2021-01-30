package main.ticket.booking.dao;

import java.util.List;

import main.ticket.booking.pojo.Screen;

public interface ScreenDAO {
	public List<Screen> getAllScreens();
	public Screen getScreen(int id);
	public boolean insertScreen(Screen screen);
	public boolean updateScreen(Screen screen);
	public boolean deleteScreen(Screen screen);
}
