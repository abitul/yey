package apifutsal

class Team {

    String idCard
    String teamName
    Integer countTeam
    String contactNo
    String imageProfile
    String address
    String email
    String facebook
    String instagram
    String twitter
    boolean isReadyToMatch
    Date lastUpdate 

    static hasMany = [imagesTeam:ImageTeam, players: Player, reviewsTeam: ReviewTeam, bookings: Booking, matchRecords: MatchRecord]

    static constraints = {
        id generator: 'sequence'
        idCard size: 5..20, blank: false, nullable: false, unique: true
        teamName size: 5..15, blank: false, nullable: false
        countTeam size: 1..5, blank: false, nullable: false
        contactNo size: 1..15, blank: false, nullable: false
        imageProfile size: 5..100, blank: true, nullable: true
        address size: 5..250, blank: false, nullable: false
        email size: 1..50, email: true, blank: false, nullable: false
        facebook size: 1..50, blank: true, nullable: true
        instagram size: 1..50, blank: true, nullable: true
        twitter size: 1..50, blank: true, nullable: true
        isReadyToMatch size: 1..10, blank: false, nullable: false
    }

    static mapping = {

    }

}
