package apifutsal

class ReviewTeamController {

static responseFormats = ['json']

    ReviewTeamService reviewTeamService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = reviewTeamService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = reviewTeamService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = reviewTeamService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = reviewTeamService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
