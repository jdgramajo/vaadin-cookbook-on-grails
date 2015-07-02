package app

import grails.transaction.Transactional

@Transactional
class SystemStatusService {

    String getValue() {
    	"Offline"
    }
}
