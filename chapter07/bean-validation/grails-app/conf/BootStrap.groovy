import app.validation.Product

class BootStrap {

    def init = { servletContext ->
    	new Product(code: 1, name: "Tablet", price: 299.99 as double).save()
    }
    def destroy = {
    }
}
