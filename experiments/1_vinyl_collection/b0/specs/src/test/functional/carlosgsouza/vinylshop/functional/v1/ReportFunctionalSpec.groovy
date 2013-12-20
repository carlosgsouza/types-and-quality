package carlosgsouza.vinylshop.functional.v1

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report
import carlosgsouza.vinylshop.model.Vinyl

class ReportFunctionalSpec extends Specification {
	
	VinylCollectionApp app 
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should show a genre report"() {
		when:
		app.execute "genre report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Genre Report", new Report(data:["Number of genres":"4", "Top genre":"Rock", "Number of Rock vinyls": "3", "Number of Rock songs":"7"] ) ]
		}
	}
	
	
	def "should show an artist report"() {
		when:
		app.execute "artist report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Artist Report", new Report(data:["Number of artists":"6", "Top artist":"Pearl Jam", "Number of vinyls by Pearl Jam": "2", "Number of songs by Pearl Jam":"5"] ) ]
		}
	}
	
	def "should show an artist report even when there are no artists"() {
		given:
		app.db.reset()
		
		when:
		app.execute "artist report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Artist Report", new Report(data:["Number of artists":"0"] ) ]
		}
	}
}