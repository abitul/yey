package apifutsal

class Booking {
    
    Date startTime
    Date endTime
    String status
    String bookingCode
    Date createdDate
    static belongsTo = [team: Team]

    static constraints = {
        id generator: 'sequence'
        status size: 1..20, blank: true, nullable: false
    }
}
