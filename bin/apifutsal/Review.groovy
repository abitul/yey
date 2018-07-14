package apifutsal

class Review {

    String title
    String comment
    String star
    String tag
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        title size: 1..100, blank: false, nullable: false
        comment size: 5..20, blank: false, nullable: false
        star size: 1..20, blank: false, nullable: false
        tag size: 1..20, blank: false, nullable: false
    }
}
