package carlosgsouza.vinylshop.functional.v2

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report

class GenreFunctionalSpec extends Specification {
	
	VinylCollectionApp app
	def genres = [:]
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
		
		app.db.@genres.each {
			genres[it.name] = it
		}
	}
	
	def "should search for vinyls given the genre"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		def unorthodoxJukebox = app.preloadedVinyls[1]
		
		expect:
		bornToDie.genre == "Pop"
		unorthodoxJukebox.genre == "Pop"
		
		when:
		app.execute "search genre Pop"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 2 with genre matching 'Pop'"] + bornToDie + unorthodoxJukebox
		}
	}
	
	def "should ignore the case when searching for vinyls given the genre"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		def unorthodoxJukebox = app.preloadedVinyls[1]
		
		expect:
		bornToDie.genre == "Pop"
		unorthodoxJukebox.genre == "Pop"
		
		when:
		app.execute "search genre pOP"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 2 with genre matching 'pOP'"] + bornToDie + unorthodoxJukebox
		}
	}
	
	def "should match partially when searching for vinyls given the genre"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		def unorthodoxJukebox = app.preloadedVinyls[1]
		
		expect:
		bornToDie.genre == "Pop"
		unorthodoxJukebox.genre == "Pop"
		
		when:
		app.execute "search genre op"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 2 with genre matching 'op'"] + bornToDie + unorthodoxJukebox
		}
	}
}