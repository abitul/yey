package apifutsal

import grails.transaction.Transactional

@Transactional
class PlayingService {

    def result 
    def lastUpdate = new Date()
    def playing
    ErrorHandler errorHandler

    def showData(params) {

        try{
            print lastUpdate
            playing = []
            def startTime =  Date.parse("yyyy-MM-dd H:mm:s", params.startTime)
            def endTime = Date.parse("yyyy-MM-dd H:mm:s", params.endTime)
            def listData = params.isBattle.toBoolean() ? Booking.findAllByVersusTeamIdIsNullAndCreatedDateBetween(startTime, endTime) : Booking.findAllByVersusTeamIdIsNotNullAndCreatedDateBetween(startTime, endTime) 
            println listData
            listData.each{res->
                    def stadion = Stadion.get(res.stadionId)
                    def futsalField = FutsalField.get(res.futsalFieldId)
                    def team1 = Team.get(res.teamId)
                    def team2 = res.versusTeamId ? Team.get(res.versusTeamId) : null

                    def objectData = [
                        bookingId: res.id,
                        isBattle : params.isBattle.toBoolean(),
                        team1Id: res.teamId,
                        team1Name: team1.teamName,
                        team2Id: res.versusTeamId ? res.versusTeamId : null,
                        team2Name: res.versusTeamId ? team2.teamName : null,
                        province: stadion.province,
                        districts: stadion.districts,
                        subDistricts: stadion.subDistricts,
                        kelurahan: stadion.kelurahan,
                        zipCode: stadion.zipCode,
                        futsalFieldId: res.futsalFieldId,
                        futsalFieldName: futsalField.name
                    ]

                    playing.push(objectData)
            }

            result = [
                data : playing,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data playing", e, "playing")
        }

        return result

    }

    def saveData(params) {

        try{
            playing = new Playing()
            print lastUpdate
            playing.type = params.type
            playing.idVersus = params.idVersus
            playing.teamVersus = params.teamVersus
            playing.lastUpdate = lastUpdate
            playing.save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(playing, "ERROR_SAVE_DATA", "Error save data", e, "playing")
        }

        return result

    }

    def updateData(params) {

        try{
            playing = Playing.get(params.id)
            print playing
            playing.type = params.type
            playing.idVersus = params.idVersus
            playing.teamVersus = params.teamVersus
            playing.lastUpdate = lastUpdate
            playing.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(playing, "ERROR_UPDATE_DATA", "Failed update data playing", e, "playing")
        }

        return result

    }

    def deleteData(params) {

        try{
            playing = Playing.get(params.id)
            playing.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data playing", e, "playing")
        }

        return result
        
    }
}
