package app.validation

import grails.transaction.Transactional

@Transactional
class ProductService {

    Product getProductByCode(code) {
    	Product.findByCode(code)
    }

    boolean saveProduct(Product product) {
		product.save()
    }
}
