package apifutsal

class UrlMappings {

    static mappings = {
        
        get "/$controller(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller(.$format)?"(action:"update")
        delete "/$controller(.$format)?"(action:"delete")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
