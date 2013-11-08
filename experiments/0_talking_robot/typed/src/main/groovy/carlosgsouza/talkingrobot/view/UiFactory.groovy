package carlosgsouza.talkingrobot.view

import carlosgsouza.derails.Form
import carlosgsouza.derails.View




public class UiFactory {
	
	View bye(String name) {
		new View("Bye, $name!")
	}
	
	Form nameForm() {
		new Form("My name is Jack Johnson and I am a talking robot. Who are you?", "name")
	}
	
	View hi(String name) {
		new View("Hello, $name!")
	}
	
}
