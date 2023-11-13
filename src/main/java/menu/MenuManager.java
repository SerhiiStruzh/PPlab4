package menu;

import java.util.Stack;

public class MenuManager 
{	
	private static Stack<Menu> menuStack = new Stack<>();
	
	public static void run() {
		Menu currMenu;
		menuStack.push(new SignInSignUpMenu());
		
		while(!menuStack.isEmpty()) {
			currMenu = menuStack.pop();
			currMenu.displayMenu();
		}
	}
	
	public static void pushMenuStack(Menu menu) {
		menuStack.push(menu);
	}
	
	public static Menu popMenuStack() {
		return menuStack.pop();
	}
}
