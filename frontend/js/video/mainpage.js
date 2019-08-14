loadDateVideoCards = function () {
        console.log("loadDateVideoCards")
        api.get("http://localhost:8080/api/vi/videos?filter=view&page=1&limit=6")
        .then(response => response.json())
        .then(json => addVideoCardTemplates(json,"dateVideoCard") )
    }
    // loadPopularVideoCards = function () {
    //     console.log("popularVideoCard")
    //     fetch("http://localhost:8080/api/v1/videos?filter=view&page=1&limit=6", {
    //         method: "GET",
    //         headers: header,
    //     }).then(response => response.json())
    //         .then(json => addVideoCardTemplate(json, "popularVideoCard"))
    // }

window.onload = function () {
    console.log("window Onload")
     loadDateVideoCards()
}

