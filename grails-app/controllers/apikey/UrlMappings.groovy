package apikey

class UrlMappings {

	static mappings = {
		"/ws/check"(controller:'checkKey', action: 'webserviceCheck')
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
