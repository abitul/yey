package apifutsal

class ReviewFutsalField {

    String title
    String comment
    Integer star
    Integer idReviewer
    Date lastUpdate

    static belongsTo = [futsalField: FutsalField]

    static constraints = {
        id generator: 'sequence'
        title size: 1..100, blank: false, nullable: false
        comment size: 1..1000, blank: false, nullable: false
        star size: 1..20, blank: false, nullable: false
        idReviewer size: 1..20, blank: false, nullable: false
    }
}
