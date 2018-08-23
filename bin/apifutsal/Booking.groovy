package apifutsal

class Booking {
    
    Date startTime
    Date endTime
    String status
    String bookingCode
    Integer stadionId
    Integer versusTeamId
    Integer duration
    Double  bookingFee
    Date createdDate
    static belongsTo = [futsalField: FutsalField]

    static constraints = {
        id generator: 'sequence'
        startTime size: 1..50, blank: false, nullable: false
        endTime size: 1..50, blank: false, nullable: false
        status size: 1..20, blank: false, nullable: false
        bookingCode size: 1..10, blank: false, nullable: false
        stadionId size: 1..50, blank: false, nullable: true
        duration size: 1..50, blank: false, nullable: true
        versusTeamId size: 1..10, blank: true, nullable: true
        bookingFee size: 1..15, blank: false, nullable: false
    }
}
