package apifutsal

class Player {
   
    String playerName
    Integer age
    String playerPosition
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
    }

    static mapping = {

    }
}
