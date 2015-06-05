import app.crud.Product

class BootStrap {

    def init = { servletContext ->
    	def code = 1
    	new Product(code: code++, name: "Computer", price: 599.90 as double).save()
		new Product(code: code++, name: "Mobile phone", price: 14.5 as double).save()
		new Product(code: code++, name: "Tablet", price: 99.90 as double).save()
		new Product(code: code++, name: "Mouse", price: 0.99 as double).save()
    }
    def destroy = {
    }
}
