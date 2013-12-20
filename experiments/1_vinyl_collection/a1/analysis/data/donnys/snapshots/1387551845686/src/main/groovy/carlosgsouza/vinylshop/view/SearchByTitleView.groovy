package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByTitleView extends View {

	public SearchByTitleView(query, vinylList) {
		items.add("Listing " + vinylList.size() + " items matching '" + query + "'");
		items.addAll(vinylList);
	}

}
