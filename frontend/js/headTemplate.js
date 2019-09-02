const headTemplate = function () {
    const template = `
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no">
    <title>EDD - wootube</title>
    <meta name="robots" content="index, follow">

    <meta name="keyword" content="우아한테크코스, 프로그래밍, 개발자, 백엔드, 프론트엔드, Backend, Frontend, IOS, Android">
    <meta name="description" id="metaDesc" content="우아한테크코스 크루 ooo의 개발 블로그">

    <meta property="og:type" content="website">
    <meta property="og:title" content="우아한테크코스">
    <meta property="og:description" id="metaOgDesc" content="우튜브">
    <meta property="og:image" content="./images/logo/logo-thumnail-bg.jpg">
    <meta property="og:url" content="https://woowacourse.github.io/">

    <meta name="twitter:card" content="website">
    <meta name="twitter:title" content="우아한테크코스">
    <meta name="twitter:description" id="metaTwitterDesc" content="우튜브">
    <meta name="twitter:image" content="./images/logo/logo-thumnail-bg.jpg">
    <meta name="twitter:domain" content="https://woowacourse.github.io/">

    <link rel="shortcut icon" href="./images/logo/favicon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Amiri:400,400i|Source+Sans+Pro:400,400i,600,700'">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    `

    const headElem = document.querySelector('head')
    headElem.insertAdjacentHTML('afterbegin', template)
}

headTemplate()