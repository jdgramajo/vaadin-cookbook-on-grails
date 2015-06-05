package app.crud

class Product {

	int code
	String name
	double price
	boolean salable

    static constraints = {
    	code(min: 1)
    	name(minSize: 3, maxSize: 20)
    	price(min: 0 as double)
    }
}
