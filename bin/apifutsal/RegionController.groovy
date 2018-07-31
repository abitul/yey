package apifutsal

class RegionController {

   static responseFormats = ['json']     
   static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    RegionService regionService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = regionService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = regionService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = regionService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = regionService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
