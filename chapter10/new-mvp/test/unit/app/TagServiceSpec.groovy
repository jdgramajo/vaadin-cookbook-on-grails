package app

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TagService)
@Mock(Tag)
class TagServiceSpec extends Specification {

    void "Null collection when no Tag has been pushed"() {
    	
    	given: "No saves are made"
    	
    	when:
    		def coll = service.getAllTags()
    	
    	then:
    		!coll

    }

    void "Collection<Tag> returned when Tags have been pushed"() {

    	given: "Tags are saved"
    		service.saveTag(new Tag(name: "one"))
    		service.saveTag(new Tag(name: "two"))
    		service.saveTag(new Tag(name: "three"))

    	when: "Fetching tags"
    		def coll = service.getAllTags()

    	then: "Got a Collection<Tag>"
    		coll instanceof Collection<Tag>

    }
}
