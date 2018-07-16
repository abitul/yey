package apifutsal

class Stadion {

    String stadionName
    String adress
    String contactNo
    String guard
    Integer countfutsalField
    String email
    String facebook
    String instagram
    String twitter
    String facilities
    Date lastUpdate
    static hasMany = [futsalFields: FutsalField, reviewsStadion : ReviewStadion, imagesStadion: ImageStadion]

    static constraints = {
        id generator: 'sequence'
        stadionName size: 1..100, blank: false, nullable: false
        adress size: 5..250, blank: false, nullable: false
        contactNo size: 1..15, blank: false, nullable: false
        guard size: 1..50, blank: false, nullable: false
        countfutsalField size: 1..3, blank: false, nullable: false
        email size: 1..50, email: true, blank: false, nullable: false
        facebook size: 1..50, blank: true, nullable: true
        instagram size: 1..50, blank: true, nullable: true
        twitter size: 1..50, blank: true, nullable: true
        facilities size: 1..200, blank: false, nullable: false
    }

    static mapping = {

    }
}
