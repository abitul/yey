package apifutsal


class ErrorHandler {

    def objectError = []
    def listError = []


    def setError(error, description){
        objectError = [
            error: error,
            description: description 
        ]
        listError.push(objectError)
    }

    def getListError(){
        return listError
    }
}
