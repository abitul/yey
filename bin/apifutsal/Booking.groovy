package apifutsal

class Booking {
    
    Date startTime
    Date endTime
    String status
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        status size: 1..20, blank: true, nullable: false
    }
}
