package apifutsal

class Region {

    String province
    String districts
    String subDistricts
    String kelurahan
    Integer zipCode
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        province size: 1..100, blank: false, nullable: false
        districts size: 1..100, blank: false, nullable: false
        subDistricts size: 1..100, blank: false, nullable: false
        kelurahan size: 1..100, blank: false, nullable: false
        zipCode size: 1..10, blank: false, nullable: false
    }
}
