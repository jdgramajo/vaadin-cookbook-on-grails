package app

import grails.transaction.Transactional

@Transactional
class SystemStatusService {

    static String getValue() {
    	"Offline"
    }
}
