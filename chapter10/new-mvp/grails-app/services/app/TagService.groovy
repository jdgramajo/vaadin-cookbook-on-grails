package app

import grails.transaction.Transactional

@Transactional
class TagService {

    Collection getAllTags() {
    	Tag.findAll()
    }

    boolean saveTag(Tag tag) {
    	tag.save()
    }
}
