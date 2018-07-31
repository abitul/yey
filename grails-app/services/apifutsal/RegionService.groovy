package apifutsal

import grails.transaction.Transactional

@Transactional
class RegionService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {

        try{
            print lastUpdate

            def criteriaRegion = Region.createCriteria()
            def region = [ "${params.searchFor}" : criteriaRegion.list{
                        
                        if(params.searchValue && params.searchValue !=""){
                            ilike ("${params.searchBy}","%${params.searchValue}%")
                        }
                        projections{
                            groupProperty("${params.searchFor}")
                        }

                    order("${params.searchFor}", "asc")
            }]

            result = [
                data : region,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data Region", e, "region")
        }

        return result
        
    }

}
