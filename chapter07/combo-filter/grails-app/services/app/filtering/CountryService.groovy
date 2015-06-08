package app.filtering

import grails.transaction.Transactional

@Transactional
class CountryService {

    Collection findAllCountries() {
    	Country.findAll()
    }
    
}
