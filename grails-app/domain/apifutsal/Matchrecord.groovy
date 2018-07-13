package apifutsal

class Matchrecord {

    String status
    Date dateCreated

    static constraints = {
        id generator: 'sequence'
        status size: 1..20, blank: false, nullable: false
        versus size: size: 1..100, blank: false, nullable: false
    }
}
