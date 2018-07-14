package apifutsal

class Team {

    String idCard
    String teamName
    Integer countTeam
    String contactNo
    String imageProfile
    String address
    String email
    boolean isReadyToMatch
    Date lastUpdate 

    static hasMany = [images:Image, players: Player, reviews: Review, sosmeds: Sosmed, bookings: Booking, matchrecords: Matchrecord]

    static constraints = {
        id generator: 'sequence'
        idCard size: 5..20, blank: false, nullable: false, unique: true
        teamName size: 5..15, blank: false, nullable: false
        countTeam size: 1..5, blank: false, nullable: false
        contactNo size: 1..15, blank: false, nullable: false
        imageProfile size: 5..100, blank: true, nullable: true
        address size: 5..250, blank: false, nullable: false
        email size: 1..50, email: true, blank: false, nullable: false
        isReadyToMatch size: 1..10, blank: false, nullable: false
    }

    static mapping = {

    }
}
