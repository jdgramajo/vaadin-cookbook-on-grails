package app

import grails.transaction.Transactional

@Transactional
class UserService {

    List<User> getAll() {
        User.getAll()
    }
    
}
