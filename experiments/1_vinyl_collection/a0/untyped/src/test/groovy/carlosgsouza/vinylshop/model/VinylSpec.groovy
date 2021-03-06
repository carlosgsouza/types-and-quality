package carlosgsouza.vinylshop.model

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class VinylSpec extends Specification {
	
	@Shared def aVinyl = new Vinyl()
	
	def "should validate a valid vinyl"() {
		given:
		def vinyl = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		
		when:
		def valid = vinyl.isValid()
		
		then:
		valid == true
	}
	
	@Unroll
	def "should consider two vinyls equal to each other if they have the same attributes but the id"(equals, vinyl1, vinyl2) {
		expect:
		equals == (vinyl1.equals(vinyl2))
		
		where:
		equals	| vinyl1																			| vinyl2
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| null
		true	| aVinyl																			| aVinyl
		// id
		true	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		true	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		true	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:null, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		true	| new Vinyl(id:null, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")		| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		true	| new Vinyl(id:null, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")		| new Vinyl(id:null, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		// artist                                                                               	
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:1, artist:"BBB", title:"A", songs:["A"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:1, artist:null, title:"A", songs:["A"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:null, title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:1, artist:null, title:"A", songs:["A"], year:"A", genre:"A")
		true	| new Vinyl(id:1, artist:null, title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:1, artist:null, title:"A", songs:["A"], year:"A", genre:"A")
		// title                                                                                	
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"BBB", songs:["A"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:null, songs:["BBB"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:null, songs:[""], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:[""], year:"A", genre:"A")
		true	| new Vinyl(id:1, artist:"A", title:null, songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:null, songs:["A"], year:"A", genre:"A")
		// songs                                                                                	
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["BBB"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A", "BBB"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:[], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:null, year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["BBB"], year:"A", genre:"A")		| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A", "BBB"], year:"A", genre:"A")	| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:[""], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:[""], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:null, year:"A", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:null, year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		true	| new Vinyl(id:1, artist:"A", title:"A", songs:null, year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:null, year:"A", genre:"A")
		// year                                                                                      	
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"BBB", genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:null, genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"BBB", genre:"A")		| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		true	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:null, genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:null, genre:"A")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:null, genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"B", genre:"A")
		// genre
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:"BBB")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:null)			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:"BBB")
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:null)
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"BBB")		| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		true	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:null)			| new Vinyl(id:2, artist:"A", title:"A", songs:["A"], year:"A", genre:null)
		// classes
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| new Double(2)
		false	| new Double(2)																		| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		// null objects
		false	| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")			| null
		false	| null																				| new Vinyl(id:1, artist:"A", title:"A", songs:["A"], year:"A", genre:"A")
		true	| null																				| null
	}
	
	@Unroll
	def "should not validate an invalid vinyl"(artist, title, songs, year, genre) {
		given:
		def vinyl = new Vinyl(artist:artist, title:title, songs:songs, year:year, genre:genre)
		
		when:
		def valid = vinyl.isValid()
		
		then:
		valid == false
		
		where:
		artist	| title	| songs			| year	| genre
		null	| null	| null			| null	| null
		""		| "t"	| ["s"]			| "y"	| "g"
		null	| "t"	| ["s"]			| "y"	| "g"
		"a"		| ""	| ["s"]			| "y"	| "g"
		"a"		| null	| ["s"]			| "y"	| "g"
		"a"		| "t"	| [""]			| "y"	| "g" //
		"a"		| "t"	| []			| "y"	| "g"
		"a"		| "t"	| null			| "y"	| "g"
		"a"		| "t"	| ["", null]	| "y"	| "g" //
		"a"		| "t"	| ["s"]			| ""	| "g"
		"a"		| "t"	| ["s"]			| null	| "g"
		"a"		| "t"	| ["s"]			| "y"	| ""
		"a"		| "t"	| ["s"]			| "y"	| null
		""		| null	| []			| "y"	| "g"
	}
	
	
	def "should generate a string representing the Vinyl"() {
		when:
		def string = new Vinyl(id:1, artist:"The Artist", title:"The Title", songs:["A1", "A2", "A3"], year:"1985", genre:"Samba").toString()
		
		then:
		string == 'id:1, artist:The Artist, title:The Title, songs:[A1, A2, A3], year:1985, genre:Samba'
	}
	
	def "should have a java like fields based constructor for vinyls"() {
		when:
		def vinyl = new Vinyl("A", "T", ["S1", "S2"], "Y", "G");
		
		then:
		vinyl.artist == "A"
		vinyl.title == "T"
		vinyl.songs == ["S1", "S2"]
		vinyl.year == "Y"
		vinyl.genre == "G"
	}
}