package apifutsal

import grails.transaction.Transactional
import static org.hibernate.sql.JoinType.*
import org.hibernate.criterion.CriteriaSpecification

@Transactional
class ScheduleService {

    def result 
    def lastUpdate = new Date()
    def schedule
    ErrorHandler errorHandler

    def showData(params) {

        try{

            println lastUpdate
            schedule = []
            Integer offset = (params.int("page")-1) * params.int("max")
            def startTime = Date.parse("yyyy-MM-dd H:mm:ss", params.startTime)
            def endTime = Date.parse("yyyy-MM-dd H:mm:ss", params.endTime)
            
            schedule =  Booking.withCriteria() {
                createAlias "futsalField","ff", RIGHT_OUTER_JOIN
                createAlias "ff.stadion","s", RIGHT_OUTER_JOIN
                resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                projections {
                    property "s.stadionName", "stadionName"
                    property "ff.name", "futsalFieldName"
                    property "ff.type", "type"
                    property "ff.price", "price"
                    property "ff.id", "futsalFieldId"
                    sqlProjection """COALESCE(this_.stadion_id, ${params.stadionId}) as stadionId,
                                     COALESCE(this_.start_time, '${startTime}') as startTime,
                                     COALESCE(this_.end_time, '${endTime}') as endTime,
                                     COALESCE(this_.status, 'true', 'false') as isAvailable""",
                                     ["stadionId","startTime", "endTime", "isAvailable"],
                                     [INTEGER, TIMESTAMP, TIMESTAMP, BOOLEAN]
                }

                and{
                    or{
                        eq("startTime", startTime) 
                        eq("endTime", endTime) 
                        isNull "id"
                    }   
                    eq("s.id", params.stadionId as Long)
                }
            }

            result = [
                data : schedule,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data Schedule", e, "Schedule")
        }

        return result
        
    }
}
