import app.filtering.*

class BootStrap {

    def init = { servletContext ->
    	Country country = new Country(name: "England")
    	country.addToCities(name: "London")
    	country.addToCities(name: "Bristol")
    	country.addToCities(name: "Birmingham")
    	country.save()
    	country = new Country(name: "California")
    	country.addToCities(name: "San Francisco")
    	country.addToCities(name: "San Jose")
    	country.addToCities(name: "Mountain View")
    	country.save()
    	country = new Country(name: "Czech Republic")
    	country.addToCities(name: "Prague")
    	country.addToCities(name: "Brno")
    	country.addToCities(name: "Ostrava")
    	country.save()
    }
    def destroy = {
    }
}
