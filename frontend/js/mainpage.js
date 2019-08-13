const mainpage = (function () {
    const loading = window.onload = function () {
        console.log("window onload 실행!!")
        const data = [
            {
                youtubeId: "S8e1geEpnTA",
                title: "제목1"
            }, {
                youtubeId: "mi-WNHybxx8",
                title: "제목2"
            }, {
                youtubeId: "n0gPIogzZ0A",
                title: "제목3"
            }, {
                youtubeId: "CF-c1K3WWg4",
                title: "제목4"
            }, {
                youtubeId: "-v4M8IyYOKQ",
                title: "제목5"
            }
        ]
        for(let i =0; i < 5; i++) {
            addVideoCardTemplate(data, i)
        }
        
        // fetch("/videos?filter&date?page&1?limit&5", {
        //     method: "GET",
        //     headers: header,
        // }).then(response => response.json())
        //     .then(json => addVideoCardTemplate(json))
    }
    return {
        init: window.onload
    }

})();
