package app

public class UserService {

    List<User> findAll() {
        def res = [] as List<User>
        res << new User(name: "Jaromir Jagr")
        res << new User(name: "Wayne Gretzky")
        res << new User(name: "David Vyborny")
        res << new User(name: "Jari Kurri")
        res << new User(name: "Martin Straka")
        res << new User(name: "Patrik Elias")
        res << new User(name: "Sidney Crosby")
        return res
    }
}
