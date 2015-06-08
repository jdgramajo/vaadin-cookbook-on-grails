package app.filtering

import com.vaadin.grails.Grails
import com.vaadin.data.Property.ValueChangeEvent
import com.vaadin.data.Property.ValueChangeListener
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.shared.ui.combobox.FilteringMode
import com.vaadin.ui.ComboBox
import com.vaadin.ui.FormLayout

@SuppressWarnings("serial")
class CountryForm extends FormLayout {
	
	private BeanItemContainer<Country> countryContainer = new BeanItemContainer<Country>(Country.class)
	private ComboBox countryBox = new ComboBox("Country:", countryContainer)
	private ComboBox cityBox = new ComboBox("City:")
	private CountryService countryService = Grails.get(CountryService)

	CountryForm() {		
		setSizeUndefined()		
		setMargin(true)		

		initComboBoxes()
		addComponent(cityBox)
		addComponent(countryBox)
	}

	private void initComboBoxes() {		
		fillCountryContainer(countryContainer)	
		
		cityBox.with {
			setFilteringMode(FilteringMode.CONTAINS)		
			setNullSelectionAllowed(false)
		}

		updateCityBox(countryContainer.getIdByIndex(0))
		
		countryBox.with {
			select(countryContainer.getIdByIndex(0))
			setImmediate(true)
			setNullSelectionAllowed(false)
			setItemCaptionPropertyId("name")
			addValueChangeListener({
				updateCityBox(countryBox.getValue() as Country)
			} as ValueChangeListener)
		}
	}
	
	private void updateCityBox(Country country) {
		cityBox.with {
			BeanItemContainer<String> citiesContainer = new BeanItemContainer<>(String.class)
			citiesContainer.with {
				country.cities.each { addItem(it.name) }
			}
			setContainerDataSource(citiesContainer)
			select(citiesContainer.getIdByIndex(0))
		}
	}

	private void fillCountryContainer(BeanItemContainer<Country> container) {
		def countries = countryService.findAllCountries()
		container.with {
			countries.each { addItem(it) }
		}
	}	

}
