package apifutsal

class Player {
   
    String playerName
    Integer age
    String playerPosition
    String contactNo
    String facebook
    String instagram
    String twitter
    Date lastUpdate

    static belongsTo = [team: Team]

    static constraints = {
        id generator: 'sequence'
        playerName size: 1..50, blank: false, nullable: false
        age size: 1..3, blank: false, nullable: false
        playerPosition size: 1..30, blank: false, nullable: false
        facebook size: 1..50, blank: true, nullable: true
        instagram size: 1..50, blank: true, nullable: true
        twitter size: 1..50, blank: true, nullable: true
        contactNo size: 1..15, blank: false, nullable: false

    }

    static mapping = {

    }

}
