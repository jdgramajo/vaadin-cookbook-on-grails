import app.User

class BootStrap {

    def init = { servletContext ->
    	new User(name: "Jaromir Jagr").save()
        new User(name: "Wayne Gretzky").save()
        new User(name: "David Vyborny").save()
        new User(name: "Jari Kurri").save()
        new User(name: "Martin Straka").save()
        new User(name: "Patrik Elias").save()
        new User(name: "Sidney Crosby").save()
    }
    def destroy = {
    }
}
