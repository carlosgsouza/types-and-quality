package carlosgsouza.vinylshop

import carlosgsouza.derails.App
import carlosgsouza.vinylshop.controller.ArtistController
import carlosgsouza.vinylshop.controller.GenreController
import carlosgsouza.vinylshop.controller.SongController
import carlosgsouza.vinylshop.controller.VinylController
import carlosgsouza.vinylshop.controller.YearController
import carlosgsouza.vinylshop.model.Vinyl
import carlosgsouza.vinylshop.view.UiFactory

class VinylCollectionApp extends App {
	
	VinylController vinylController = new VinylController()
	ArtistController artistController = new ArtistController()
	YearController yearController = new YearController()
	GenreController genreController = new GenreController()
	SongController songController = new SongController()
	
	UiFactory uiFactory = new UiFactory()
	
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
					
					console.render uiFactory.deleteVinyls()
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
				case "search":
					def artists = songController.search(parameter)
					console.render uiFactory.searchBySong(parameter, artists)
					return
			}
		} else if(controller == "year") {
			switch(action) {
				case "list":
					def years = yearController.list()
					console.render uiFactory.listYears(years)
					return
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
		} 
		
		println "command not found"
	}
	
	void bootstrap() {
		vinylController.create new Vinyl(artist:"Lana Del Rey", title:"Born to Dei", songs:["Off to Races", "Radio", "Carmen"], year:"2012", genre:"Pop")
		vinylController.create new Vinyl(artist:"Bruno Mars", title:"Unorthodox Jukebox", songs:["Gorilla", "Treasure", "Young Girls"], year:"2012", genre:"Pop")
		vinylController.create new Vinyl(artist:"Pearl Jam", title:"Lightning Bolt", songs:["Getaway", "Mind Your Manners", "Young Sirens"], year:"2013", genre:"Rock")
		vinylController.create new Vinyl(artist:"Angra", title:"Temple of Shadows", songs:["Spread Your Fire", "Deus Le Volt!", "Waiting Silence"], year:"2004", genre:"Metal")
		vinylController.create new Vinyl(artist:"Luan Santana", title:"Quando Chega a Noite", songs:["Te Esperando", "Te vivo", "Qu�mica do Amor"], year:"2010", genre:"Rock")
		vinylController.create new Vinyl(artist:"Coldplay", title:"Parachutes", songs:["Don't Panic", "Shiver", "Spies"], year:"2000", genre:"Alternative")
		vinylController.create new Vinyl(artist:"Pearl Jam", title:"Backspacer", songs:["Just Breathe", "Mind Your Amongst the Waves", "Supersonic"], year:"2009", genre:"Rock")
	}
	
	public static void main(String[] args) {
		new VinylCollectionApp().run()
	}
}