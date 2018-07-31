package apifutsal


class ErrorHandler {

    def objectError = []
    def listError = []

    def errorChecking(tableData, errorTag, errorMessage, e, tableName){
        
        listError = []

        if(tableData){
            if (tableData.hasErrors()){
                def errorData = Error.findAllByTableNameIlike("%${tableName}%")
                errorData.each{res->
                        if (tableData.errors.hasFieldErrors("${res.errorField}")) {
                            setError("${res.errorTag}", "${res.errorMessage} " + tableData.errors.getFieldError("${res.errorField}").rejectedValue)
                        }
                }
            }else{
                setError("${errorTag}", "${errorMessage} ${e}")
            }

        }else{
            setError("${errorTag}", "${errorMessage} ${e}")
        }
        
        return  [errors: getListError(), isSuccessFull: false]

        
    }

    def setError(error, message){
        objectError = [
            error: error,
            message: message 
        ]
        listError.push(objectError)
    }

    def getListError(){
        return listError
    }
}
