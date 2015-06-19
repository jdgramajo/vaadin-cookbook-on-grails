package app

public class UserService {

    private dbFake = []

    public UserService() {
        1000.times { i ->
            dbFake << new User(name: "Sara ${i++}")
            dbFake << new User(name: "Nicolas ${i++}")
            dbFake << new User(name: "Matthew ${i++}")
            dbFake << new User(name: "Michaela ${i++}")
            dbFake << new User(name: "Martin ${i++}")
            dbFake << new User(name: "Anna ${i++}")
            dbFake << new User(name: "Ester ${i}")
        }
    }

    public int size() {
        return dbFake.size()
    }

    public Collection list(int startIndex, int endIndex) {
        dbFake[startIndex..endIndex]
    }
}
