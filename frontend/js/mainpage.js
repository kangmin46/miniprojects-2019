const mainpage = (function () {
    const loading = window.onload = function () {
        console.log("window onload 실행!!")
      
        for(let i =0; i < 6; i++) {
            addVideoCardTemplate(data, i,"dateVideoCard")
        }

        for(let i =0; i < 6; i++) {
            addVideoCardTemplate(data, i,"popularVideoCard")
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
