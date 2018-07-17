package apifutsal

class ImageFutsalField {

    String imageName
    String category
    Date lastUpdate

    static belongsTo = [futsalField: FutsalField]

    static constraints = {
        id generator: 'sequence'
        imageName size: 1..100, blank: false, nullable: false
        category size: 1..100, blank: false, nullable: false
    }
}
