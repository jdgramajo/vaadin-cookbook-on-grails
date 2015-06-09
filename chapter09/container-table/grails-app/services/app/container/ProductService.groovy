package app.container

import grails.transaction.Transactional

@Transactional
class ProductService {

    Collection findAllProducts() {
    	Product.findAll()
    }
}
