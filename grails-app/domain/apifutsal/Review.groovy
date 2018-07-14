package apifutsal

class Review {

    String title
    String comment
    Integer star
    Integer idReviewed
    Integer idReviewer
    Date lastUpdate

    static belongsTo = [team: Team, stadion: Stadion, futsalfield: Futsalfield]

    static constraints = {
        id generator: 'sequence'
        title size: 1..100, blank: false, nullable: false
        comment size: 5..20, blank: false, nullable: false
        star size: 1..20, blank: false, nullable: false
        idReviewed size: 1..20, blank: false, nullable: false
        idReviewer size: 1..20, blank: false, nullable: false
    }
}
