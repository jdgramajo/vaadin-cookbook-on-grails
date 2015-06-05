package app.crud

import grails.transaction.Transactional

@Transactional
class ProductService {

    Collection findAllProducts() {
    	Product.findAll()
    }

    boolean saveProduct(Product product) {
    	product.save()
    }

    int getMaxCode() {
    	Product.createCriteria().get {
    		projections {
    			max "code"
    		}
    	} as int
    }

}
