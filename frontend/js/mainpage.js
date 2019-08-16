loadDateVideoCards = function () {
    api.requestVideos()
    .then(response => response.json())
    .then(json => addVideoCardTemplates(json,'dateVideoCard') )
    }
    loadDateVideoCards()

