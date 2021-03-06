package carlosgsouza.vinylshop.model;

import java.util.ArrayList;
import java.util.List;

public class Genre {
	public name = "";
	public vinyls = new ArrayList<Vinyl> ();
	
	public Genre() {
	}
	
	@Override
	public String toString() {
		List<String> vinylNames = new ArrayList<String>();
		
		for(Vinyl vinnyl : vinyls) {
			vinylNames.add(vinyl.title);
		}
		
		return name + ", Vinyls:" + vinylNames;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.is(obj))
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		def other = obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vinyls == null) {
			if (other.vinyls != null)
				return false;
		} else if (!vinyls.equals(other.vinyls))
			return false;
		return true;
	}
}