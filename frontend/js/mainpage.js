function addVideoCardTemplate(data, number, kategorie) {
    const vidioCardTemplate = `<div class="col-lg-2 padding-2">
    <div class="card bg-transparent no-border">
    <div class="card-media">
    <a href="/videos/${number+1}"> 
    <img class="img-responsive" src="http://img.youtube.com/vi/${data[number].youtubeId}/0.jpg" alt="">
    </a>
    </div>
    <div class="card-block padding-10">
    <h5 class="mrg-btm-10 no-mrg-top text-bold font-size-14 ls-0">${data[number].title}</h5>
    <span class="font-size-13">로비</span>
    <div class="font-size-13">
    <span>조회수</span>
    <span> · </span>
    <span></span>
    </div>
    </div>
    </div>
    </div>`    
    const videoCards = document.getElementById(kategorie)
    videoCards.insertAdjacentHTML("beforeend", vidioCardTemplate)
}

function addVideoCardTemplates(data, kategorie) {
    console.log("addVideoCardTemplates 실행!!")
    for(let i =0; i < 6; i++) {
        addVideoCardTemplate(data, i, kategorie)
    }
}
    loadDateVideoCards = function () {
        console.log("loadDateVideoCards")
        fetch("http://121.165.253.126:8080/videos?filter&date?page&1?limit&6", {
            method: "GET",
            headers: header,
        }).then(response => response.json())
            .then(json => addVideoCardTemplates(json,"dateVideoCard"))
    }
    loadPopularVideoCards = function () {
        console.log("popularVideoCard")
        fetch("http://121.165.253.126:8080/videos?filter&view?page&1?limit&6", {
            method: "GET",
            headers: header,
        }).then(response => response.json())
            .then(json => addVideoCardTemplate(json, "popularVideoCard"))
    }

window.onload = function () {
    console.log("window Onload")
    loadDateVideoCards()
    loadPopularVideoCards()
}

