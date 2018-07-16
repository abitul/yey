package apifutsal

class MatchRecord {

    String status
    Integer score
    Date createdDate
    static belongsTo = [team: Team]

    static constraints = {
        id generator: 'sequence'
        status size: 1..20, blank: false, nullable: false
        score size: 1..3, blank: false, nullable: false
    }
}
