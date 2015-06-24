package app

public class UserService {

    def dbFake = [] as List<User>

    public UserService() {
        1000.times {
            dbFake << new User(name: "Sara ${it++}")
            dbFake << new User(name: "Nicolas ${it++}")
            dbFake << new User(name: "Matthew ${it++}")
            dbFake << new User(name: "Michaela ${it++}")
            dbFake << new User(name: "Martin ${it++}")
            dbFake << new User(name: "Anna ${it++}")
            dbFake << new User(name: "Ester ${it++}")
        }
    }

    public int size() {
        dbFake.size()
    }

    public List<User> list(int startIndex, int endIndex) {
        dbFake[startIndex..endIndex] as List<User>
    }
}