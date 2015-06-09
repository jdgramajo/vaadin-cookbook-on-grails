import app.container.Product

class BootStrap {

    def init = { servletContext ->
    	int id = 0
		new Product(id: id++, name: "Computer", price: 599.90).save()
		new Product(id: id++, name: "Phone", price: 14.5).save()
		new Product(id: id++, name: "Tablet", price: 99.90).save()
		new Product(id: id++, name: "Mouse", price: 0.99).save()
    }
    def destroy = {
    }
}
