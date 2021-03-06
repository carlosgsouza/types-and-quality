 package carlosgsouza.vinylshop

import carlosgsouza.derails.App
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.controller.ArtistController
import carlosgsouza.vinylshop.controller.GenreController
import carlosgsouza.vinylshop.controller.ReportController
import carlosgsouza.vinylshop.controller.SongController
import carlosgsouza.vinylshop.controller.SummaryController
import carlosgsouza.vinylshop.controller.VinylController
import carlosgsouza.vinylshop.controller.YearController
import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Vinyl
import carlosgsouza.vinylshop.view.UiFactory

class VinylCollectionApp extends App {
	
	static VinylCollectionApp app = new VinylCollectionApp()
	
	VinylController vinylController = new VinylController()
	ArtistController artistController = new ArtistController()
	YearController yearController = new YearController()
	GenreController genreController = new GenreController()
	SongController songController = new SongController()
	SummaryController summaryController = new SummaryController()
	ReportController reportController = new ReportController()
	
	DB db = DB.connect()
	
	private int c = 0
	
	UiFactory uiFactory = new UiFactory()
	
	List<Vinyl> preloadedVinyls = [
			new Vinyl(artist:"Lana Del Rey", title:"Born to Die", songs:["Off to Races", "Radio", "Carmen"], year:"2012", genre:"Pop"),
			new Vinyl(artist:"Bruno Mars", title:"Unorthodox Jukebox", songs:["Gorilla", "Treasure", "Young Girls"], year:"2012", genre:"Pop"),
			new Vinyl(artist:"Pearl Jam", title:"Lightning Bolt", songs:["Getaway", "Mind Your Manners", "Young Sirens"], year:"2013", genre:"Rock"),
			new Vinyl(artist:"Angra", title:"Temple of Shadows", songs:["Deus Le Volt!", "Waiting Silence"], year:"2004", genre:"Metal"),
			new Vinyl(artist:"Luan Santana", title:"Quando Chega a Noite", songs:["Te vivo", "Quimica do Amor"], year:"2010", genre:"Rock"),
			new Vinyl(artist:"Coldplay", title:"Parachutes", songs:["Don't Panic", "Shiver", "Spies"], year:"2000", genre:"Alternative"),
			new Vinyl(artist:"Pearl Jam", title:"Backspacer", songs:["Just Breathe", "Supersonic"], year:"2009", genre:"Rock")]
	
	VinylCollectionApp() {
		super("DJ PopCorn - Amazing Vinyl Collection")
	}
	
	void routeRequest(String controller, String action, String parameter) {
		if(controller == "vinyl") {
			switch(action) {
				case "list":
					def vinyls = vinylController.list()
					console.render uiFactory.listVinyls(vinyls)
					return
				case "create":
					def form = uiFactory.vinylForm()
					console.apply form
					def id = vinylController.create(form.fields)
					def createdVinyl = vinylController.get(id)
					
					console.render uiFactory.showVinyl(createdVinyl)
					return
				case "show":
					def id = Integer.valueOf parameter
					def vinyl = vinylController.get(id)
					
					console.render uiFactory.showVinyl(vinyl)
					return
				case "delete":
					def id = Integer.valueOf parameter
					vinylController.delete(id)
					
					console.render uiFactory.deleteVinyl()
					return
				case "search":
					def result = vinylController.search(parameter)
					console.render uiFactory.searchByTitle(parameter, result)
					return
			}
		} else if(controller == "artist") {
			switch(action) {
				case "list":
					def artists = artistController.list()
					console.render uiFactory.listArtists(artists)
					return
				case "search":
					def artists = artistController.search(parameter)
					console.render uiFactory.searchByArtist(parameter, artists)
					return
			}
		} else if(controller == "song") {
			switch(action) {
				case "list":
					def artists = songController.list()
					console.render uiFactory.listSongs(artists)
					return
			}
		} else if(controller == "year") {
			switch(action) {
				case "search":
					def years = yearController.search(parameter)
					console.render uiFactory.searchByYear(parameter, years)
					return
			}
		} else if(controller == "genre") {
			switch(action) {
				case "list":
					def genres = genreController.list()
					console.render uiFactory.listGenres(genres)
					return
				case "search":
					def genres = genreController.search(parameter)
					console.render uiFactory.searchByGenre(parameter, genres)
					return
			}
		} else if(controller == "summary") {
			switch(action) {
				case "show":
					def summary = summaryController.show()
					console.render uiFactory.showSummary(summary)
					return
			}
		} else if(controller == "report") {
			switch(action) {
				case "genre":
					def report = reportController.genre()
					console.render uiFactory.showReport("Genre", report)
					return
				
				case null:
					if(c < 10) {
						c++
					}
					return
				
				case "artist":
					def report = reportController.artist()
					console.render uiFactory.showReprot("Artist", report)
					return
			}
		}
		
		console.render(new View("command not found"))
	}
	
	void bootstrap() {
		db.reset()
		
		for(vinyl in preloadedVinyls) {
			vinylController.create(vinyl)
		}
	}
	
	public static void main(String[] args) {
		app.run()
	}
}