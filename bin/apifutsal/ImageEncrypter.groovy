package apifutsal


class ImageEncrypter {


    def getBase64File(filePath){
        String fileContent = new File(filePath).text
        return fileContent.bytes.encodeBase64().toString()
    }

    def saveBase64ToFile(base64Data,filePath){
        base64Data.replaceAll("\r", "")
        base64Data.replaceAll("\n", "")
        byte[] decoded = base64Data.decodeBase64()
        new File(filePath).withOutputStream {
                it.write(decoded);
        }
    }
}
