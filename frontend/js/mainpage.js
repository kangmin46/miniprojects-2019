loadDateVideoCards = function () {
        api.get("http://localhost:8080/api/vi/videos?filter=view&page=1&limit=6")
        .then(response => response.json())
        .then(json => addVideoCardTemplates(json,"dateVideoCard") )
    }
    
window.onload = function () {
     loadDateVideoCards()
}

