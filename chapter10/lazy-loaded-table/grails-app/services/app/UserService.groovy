package app

import grails.transaction.Transactional

@Transactional
class UserService {

    public int size() {
        User.createCriteria().get() {
        	projections {
        		max "id"
        	}
        } as Long
    }

    public Collection list(int startId, int endId) {
        User.createCriteria().list() {
        	between("id", startId.toLong(), endId.toLong())
        } as List<User>
    }
}
