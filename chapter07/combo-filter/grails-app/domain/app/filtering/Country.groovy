package app.filtering

class Country {

	String name

	static hasMany = [cities: City]

    static constraints = {
    }

    static mapping = {
    	cities lazy: false
    }

}
