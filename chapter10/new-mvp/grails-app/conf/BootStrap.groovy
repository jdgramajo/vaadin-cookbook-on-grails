import app.*

class BootStrap {

    def init = { servletContext ->
    	new User(name: "Juan", password: "ejemplo").save()
    	new Tag(name: "Java").save()
    	new Tag(name: "Vaadin").save()
    	new Tag(name: "Groovy").save()
    	new Tag(name: "Grails").save()
    }
    def destroy = {
    }
}
