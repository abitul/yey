package apifutsal

class FutsalField {

    String name
    String type
    String startTime
    String endTime
    Double price
    Date lastUpdate

    static belongsTo = [stadion: Stadion]
    static hasMany = [reviewsFutsalField: ReviewFutsalField, imagesFutsalField: ImageFutsalField, bookings: Booking]

    static constraints = {
        id generator: 'sequence'
        name size: 1..50, blank: false, nullable: false
        type size: 1..30, blank: false, nullable: false
        startTime size: 1..10, blank: false, nullable: false
        endTime size: 1..10, blank: false, nullable: false
        price size: 1..15, blank: false, nullable: false
    }

    static mapping = {

    }
}
