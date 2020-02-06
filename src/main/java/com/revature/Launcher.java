package com.revature;

import com.revature.views.MainMenu;
import com.revature.views.View;

public class Launcher {
	public static void main(String[] args) {
		View view = new MainMenu();
		
		while(view != null) {
			view.showMenu();
			view = view.selectOption();
		}
		
		System.out.println("Bye!");
	}
}
