package apifutsal

import grails.transaction.Transactional


@Transactional
class TeamService {

    def result 
    def lastUpdate = new Date()
    def grailsApplication 

    def showData(params) {
        try{
            println lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            def listData = params.searchValue == "" ? Team.listOrderByLastUpdate(order: "desc") : Team.findAllByTeamNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "teamName", order: "desc", offset: offset])
            result = []
            listData.each{res->
                def objectData = [
                    id : res.id,
                    teamName : res.teamName,
                    countTeam : res.countTeam,
                    contactNo : res.contactNo,
                    imageName : res.imageName,
                    base64Image : getBase64File(res.imageName),
                    lastUpdate : res.lastUpdate
                ]
                result.push(objectData)
            }

        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data team"]
        }

        return result
    }

    def saveData(params) {
        try{
            def team = new Team()
            print lastUpdate
            team.teamName = params.teamName
            team.countTeam = params.countTeam
            team.contactNo = params.contactNo
            team.imageName = params.teamName+params.contactNo+".png"
            saveBase64ToFile(params.base64Image, team.imageName)
            team.lastUpdate = lastUpdate
            team.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data team"]
        }

        return result
    }

    def updateData(params) {
        try{
            def team = Team.get(params.id)
            print team
            team.teamName = params.teamName
            team.countTeam = params.countTeam
            team.contactNo = params.contactNo
            team.imageName = params.teamName+params.contactNo+".png"
            saveBase64ToFile(params.base64Image, team.imageName)
            team.lastUpdate = lastUpdate
            team.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data team"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def team = Team.get(params.id)
            team.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data team"]
        }

        return result
    }

    def getBase64File(imageName){
        String fileContent = new File(grailsApplication.config.properties.imageUrl+"${imageName}").text
        return fileContent.bytes.encodeBase64().toString()
    }

    def saveBase64ToFile(base64Data,imageName){
        base64Data.replaceAll("\r", "")
        base64Data.replaceAll("\n", "")
        byte[] decoded = base64Data.decodeBase64()
        String filePath = grailsApplication.config.properties.imageUrl+"${imageName}"
        new File(filePath).withOutputStream {
                it.write(decoded);
        }
    }

}
