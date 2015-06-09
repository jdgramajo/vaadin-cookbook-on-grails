import app.complextable.Product

class BootStrap {

    def init = { servletContext ->
    	int currentProductId = 0
    	new Product(productId: currentProductId++, name: "Computer", price: 599.90).save()
		new Product(productId: currentProductId++, name: "Phone", price: 14.5).save()
		new Product(productId: currentProductId++, name: "Tablet", price: 99.90).save()
		new Product(productId: currentProductId++, name: "Mouse", price: 0.99).save()
    }
    def destroy = {
    }
}
