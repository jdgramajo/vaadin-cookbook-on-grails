package app.validation

class Product {

	int code
	String name
	double price

    static constraints = {
    	code(min: 1, max: 500)
    	name(minSize: 2, maxSize: 10, blank: false)
    	price(min: 1.0 as double, max: 10000 as double)
    }
}
