package apifutsal

class Player {
   
    String playerName
    Integer age
    String playerPosition
    String contactNo
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        playerName size: 1..50, blank: false, nullable: false
        age size: 1..3, blank: false, nullable: false
        playerPosition size: 1..30, blank: false, nullable: false
        contactNo size: 1..15, blank: false, nullable: false

    }

    static mapping = {

    }

}
