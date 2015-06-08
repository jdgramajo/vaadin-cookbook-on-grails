package app.filtering

class City {

	String name

	static belongsTo = [country: Country]

    static constraints = {
    }
}
