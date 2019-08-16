const wootubeCtx = {
    util: {
        getUrlParams: function () {
            const params = {};
            window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) { params[key] = value; });
            return params;
        },
        calculateDate: function (responseDate) {
            const videoDate = new Date(responseDate.substr(0, 4), responseDate.substr(4, 2), responseDate.substr(6, 2), responseDate.substr(8, 2))
            const currentDate = new Date()
            const yearDifference = currentDate.getFullYear() - videoDate.getFullYear()
            const monthDifference = currentDate.getMonth() + 1 - videoDate.getMonth()
            const dayDifference = currentDate.getDate() - videoDate.getDate()
            const hourDifference = currentDate.getHours() - videoDate.getHours()
            if (yearDifference != 0) {
                return yearDifference + '년전'
            } else if (monthDifference != 0) {
                return monthDifference + '달전'
            } else if (dayDifference != 0) {
                return dayDifference + '일전'
            } else if (hourDifference != 0) {
                return hourDifference + '시간전'
            } else {
                return '방금전'
            }
        }
    },
}

const Api = function () {
    const defaultHeader = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
    const localhostUrl = `http://localhost:8080`
    const request = (url, method, body) => {
        return fetch(url, {
            method: method,
            headers: defaultHeader,
            body: body
        })
    }

    const nobodyRequest = (url, method) => {
        return fetch(url, {
            method: method,
            headers: defaultHeader,
        })
    }
    
    const requestVideos = () => {
        return nobodyRequest("/v1/videos?filter=date&page=0&limit=6",'GET')
    }

    const requestVideo = (videoId) => {
        return nobodyRequest(`/v1/videos/${videoId}`,'GET')
    }

    const saveVideo = (dataBody) => {
        return request("/v1/videos", 'POST', dataBody)
    }

    return {
        requestVideos: requestVideos,
        requestVideo: requestVideo,
        saveVideo: saveVideo
    }

}
const api = new Api()
