$(document).ready(function(){
    /* Set initial layout */
    var layout = "landscape";
    if ($(window).width() <= 1280) {
        goToPortrait();
    }
    /* Specify a function for restricting the
     * number of times resize() is called */
    var waitForFinalEvent = (function () {
        var timers = {};
        return function (callback, ms, uniqueId) {
            if (!uniqueId) {
                uniqueId = "Missing unique ID";
            }
            if (timers[uniqueId]) {
                clearTimeout(timers[uniqueId]);
            }
            timers[uniqueId] = setTimeout(callback, ms);
        }
    })();

    /* Run animations for going to portrait layout  */
    function goToPortrait() {
        layout = "portrait";
        $(".logo-p0").removeClass("logo-p0-to-landscape");
        $(".logo-p1").removeClass("logo-p1-to-landscape");
        $(".logo-p2").removeClass("logo-p2-to-landscape");
        $(".logo-p3").removeClass("logo-p3-to-landscape");
        
        $(".logo-p0").addClass("logo-p0-to-portrait");
        $(".logo-p1").addClass("logo-p1-to-portrait");
        $(".logo-p2").addClass("logo-p2-to-portrait");
        $(".logo-p3").addClass("logo-p3-to-portrait");
        $(".logo-p3").hide();
    }

    /* Run animations for going to landscape layout */
    function goToLandscape() {
        layout = "landscape";
        $(".logo-p3").show();
        $(".logo-p0").removeClass("logo-p0-to-portrait");
        $(".logo-p1").removeClass("logo-p1-to-portrait");
        $(".logo-p2").removeClass("logo-p2-to-portrait");
        $(".logo-p3").removeClass("logo-p3-to-portrait");
     
        $(".logo-p0").addClass("logo-p0-to-landscape");
        $(".logo-p1").addClass("logo-p1-to-landscape");
        $(".logo-p2").addClass("logo-p2-to-landscape");
        $(".logo-p3").addClass("logo-p3-to-landscape");
    }

    /* Transform the layout when resizing the window */
    $(window).resize(function(){
        waitForFinalEvent(function(){
            var width = $(window).width();
            var height = $(window).height();
            console.log(width + ", " + height);
            if (width <= 1280 && layout == "landscape") {
                layout = "portrait";
                console.log("Switching to portrait");
                goToPortrait();
            } else if (width > 1280 && layout == "portrait") {
                layout = "landscape";
                console.log("Switching to landscape");
                goToLandscape();
            }
        }, 250, "Resize event");
    });
});
