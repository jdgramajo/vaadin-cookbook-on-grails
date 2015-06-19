import app.User

class BootStrap {

    def init = { servletContext ->
    	1000.times { i ->
            new User(name: "Sara ${i++}").save()
            new User(name: "Nicolas ${i++}").save()
            new User(name: "Matthew ${i++}").save()
            new User(name: "Michaela ${i++}").save()
            new User(name: "Martin ${i++}").save()
            new User(name: "Anna ${i++}").save()
            new User(name: "Ester ${i}").save()
        }
    }
    def destroy = {
    }
}
