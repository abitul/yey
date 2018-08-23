package apifutsal

import grails.transaction.Transactional
import static org.hibernate.sql.JoinType.*
import org.hibernate.criterion.CriteriaSpecification

@Transactional
class RegionService {

    def result 
    def lastUpdate = new Date()
    ErrorHandler errorHandler

    def showData(params) {

        try{
            print lastUpdate
            def region
            if((params.searchCode as Integer) == 1){
                region =  Region.withCriteria(){
                    resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                    if(params.searchValue && params.searchValue !=""){
                        ilike ("${params.searchBy}","%${params.searchValue}%")
                    }
                    projections{
                        property "${params.searchFor}", "${params.searchFor}"
                        groupProperty ("${params.searchFor}")
                    }
                    order("${params.searchFor}", "asc")
                }
            }else if((params.searchCode as Integer) == 2){
               region = Stadion.withCriteria(){
                    resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                    projections{
                        property "districts", "location"
                        groupProperty ("districts")
                    }
                }
            }
            

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
