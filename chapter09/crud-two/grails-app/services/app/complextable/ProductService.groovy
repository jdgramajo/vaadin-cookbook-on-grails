package app.complextable

import grails.transaction.Transactional

@Transactional
class ProductService {

    Collection getAllProducts() {
    	Product.findAll()
    }

    boolean saveProduct(Product product) {
    	product.save()
    }

    boolean deleteProduct(Product product) {
    	product.delete()
    }

    int getMaxProductId() {
    	Product.createCriteria().get {
    		projections {
    			max "productId"
    		}
    	} as int
    }

}
