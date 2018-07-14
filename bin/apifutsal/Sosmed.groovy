package apifutsal

class Sosmed {

    String name
    String type
    String tag
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        name size: 1..100, blank: false, nullable: false
        type size: 5..20, blank: false, nullable: false
        tag size: 1..20, blank: false, nullable: false
    }
}
