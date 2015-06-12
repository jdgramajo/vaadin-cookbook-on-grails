package app

import grails.transaction.Transactional

@Transactional
class TagService {

    Collection getAllTags() {
    	Tags.findAll()
    }
}
