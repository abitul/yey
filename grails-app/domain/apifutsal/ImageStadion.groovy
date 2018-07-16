package apifutsal

class ImageStadion {

    String imageName
    String category
    Date lastUpdate

    static belongsTo = [stadion: Stadion]

    static constraints = {
        id generator: 'sequence'
        imageName size: 1..100, blank: false, nullable: false
        category size: 1..100, blank: false, nullable: false
    }
}
