package apifutsal

class Matchrecord {

    String status
    Date createdDate

    static constraints = {
        id generator: 'sequence'
        status size: 1..20, blank: false, nullable: false
        versus size: 1..100, blank: false, nullable: false
    }
}
