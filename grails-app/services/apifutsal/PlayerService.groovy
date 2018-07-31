package apifutsal

import grails.transaction.Transactional


@Transactional
class PlayerService {

    def result 
    def lastUpdate = new Date()
    def player
    ErrorHandler errorHandler

    def showData(params) {

        try{
            print lastUpdate
            if(params.teamId){
                def team = Team.get(params.teamId as Integer)
                player = Player.findAllByTeam(team)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                player = params.searchValue  ? Player.findAllByPlayerNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "playerName", order: "desc", offset: offset]) : Player.listOrderByLastUpdate(order: "desc") 
            }

            result = [
                data : player,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data player", e, "player")
        }

        return result

    }

    def saveData(params) {

        try{
            player = new Player()
            print lastUpdate
            player.playerName = params.playerName
            player.age = params.age
            player.playerPosition = params.playerPosition
            player.contactNo = params.contactNo
            player.facebook = params.facebook
            player.instagram = params.instagram
            player.twitter = params.twitter
            player.lastUpdate = lastUpdate
            def team = Team.get(params.teamId)
            team.addToPlayers(player).save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "player")
        }

        return result

    }

    def updateData(params) {

        try{
            player = Player.get(params.id)
            print player
            player.playerName = params.playerName
            player.age = params.age
            player.playerPosition = params.playerPosition
            player.contactNo = params.contactNo
            player.facebook = params.facebook
            player.instagram = params.instagram
            player.twitter = params.twitter
            player.lastUpdate = lastUpdate
            player.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data player", e, "player")
        }

        return result

    }

    def deleteData(params) {

        try{
            player = Player.get(params.id)
            player.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data player", e, "player")
        }

        return result
        
    }
}

