package apifutsal

class Stadion {

    String stadionName
    String adress
    String contactNo
    String guard
    Integer countFutsalField
    String email
    String facilities
    Date lastUpdate
    static hasMany = [futsalfield: Futsalfield, sosmeds: Sosmed, reviews: Review, images: Image]

    static constraints = {
        id generator: 'sequence'
        stadionName size: 1..100, blank: false, nullable: false
        adress size: 5..250, blank: false, nullable: false
        contactNo size: 1..15, blank: false, nullable: false
        guard size: 1..50, blank: false, nullable: false
        countFutsalField size: 1..3, blank: false, nullable: false
        email size: 1..50, email: true, blank: false, nullable: false
        facilities size: 1..200, blank: false, nullable: false
    }

    static mapping = {

    }
}
