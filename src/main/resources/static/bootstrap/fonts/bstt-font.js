(function (window) {
    var svgSprite = '<svg><symbol id="icon-chevronright" viewBox="0 0 1024 1024"><path d="M372.95428467 141.21142578l-139.04571533 139.04571534 231.74285889 231.74285888-231.7428589 231.74285888 139.04571534 139.04571534 370.78857421-370.78857422-370.78857421-370.78857422z"  ></path></symbol><symbol id="icon-chevronbottom" viewBox="0 0 1024 1024"><path d="M280.25714112 233.90856933l-139.04571534 139.04571534 370.78857422 370.78857421 370.78857422-370.78857421-139.04571534-139.04571534-231.74285888 231.7428589-231.74285888-231.7428589z"  ></path></symbol><symbol id="icon-refresh" viewBox="0 0 1024 1024"><path d="M936.432 603.424q0 2.848-0.576 4-36.576 153.152-153.152 248.288t-273.152 95.136q-83.424 0-161.44-31.424t-139.136-89.728l-73.728 73.728q-10.848 10.848-25.728 10.848t-25.728-10.848-10.848-25.728l0-256q0-14.848 10.848-25.728t25.728-10.848l256 0q14.848 0 25.728 10.848t10.848 25.728-10.848 25.728l-78.272 78.272q40.576 37.728 92 58.272t106.848 20.576q76.576 0 142.848-37.152t106.272-102.272q6.272-9.728 30.272-66.848 4.576-13.152 17.152-13.152l109.728 0q7.424 0 12.864 5.44t5.44 12.864zM950.736 146.272l0 256q0 14.848-10.848 25.728t-25.728 10.848l-256 0q-14.848 0-25.728-10.848t-10.848-25.728 10.848-25.728l78.848-78.848q-84.576-78.272-199.424-78.272-76.576 0-142.848 37.152t-106.272 102.272q-6.272 9.728-30.272 66.848-4.576 13.152-17.152 13.152l-113.728 0q-7.424 0-12.864-5.44t-5.44-12.864l0-4q37.152-153.152 154.272-248.288t274.272-95.136q83.424 0 162.272 31.712t140 89.44l74.272-73.728q10.848-10.848 25.728-10.848t25.728 10.848 10.848 25.728z"  ></path></symbol><symbol id="icon-list" viewBox="0 0 1024 1024"><path d="M62.360791 138.133034l150.266375 0 0 150.192697-150.266375 0 0-150.192697Z"  ></path><path d="M362.820887 138.133034l596.895529 0 0 150.192697-596.895529 0 0-150.192697Z"  ></path><path d="M62.360791 438.08864l150.266375 0 0 150.193721-150.266375 0 0-150.193721Z"  ></path><path d="M362.820887 438.08864l596.895529 0 0 150.193721-596.895529 0 0-150.193721Z"  ></path><path d="M62.360791 735.7459l150.266375 0 0 150.192697-150.266375 0 0-150.192697Z"  ></path><path d="M362.820887 735.7459l596.895529 0 0 150.192697-596.895529 0 0-150.192697Z"  ></path></symbol></svg>';
    var script = function () {
        var scripts = document.getElementsByTagName("script");
        return scripts[scripts.length - 1]
    }();
    var shouldInjectCss = script.getAttribute("data-injectcss");
    var ready = function (fn) {
        if (document.addEventListener) {
            if (~["complete", "loaded", "interactive"].indexOf(document.readyState)) {
                setTimeout(fn, 0)
            } else {
                var loadFn = function () {
                    document.removeEventListener("DOMContentLoaded", loadFn, false);
                    fn()
                };
                document.addEventListener("DOMContentLoaded", loadFn, false)
            }
        } else if (document.attachEvent) {
            IEContentLoaded(window, fn)
        }

        function IEContentLoaded(w, fn) {
            var d = w.document, done = false, init = function () {
                if (!done) {
                    done = true;
                    fn()
                }
            };
            var polling = function () {
                try {
                    d.documentElement.doScroll("left")
                } catch (e) {
                    setTimeout(polling, 50);
                    return
                }
                init()
            };
            polling();
            d.onreadystatechange = function () {
                if (d.readyState == "complete") {
                    d.onreadystatechange = null;
                    init()
                }
            }
        }
    };
    var before = function (el, target) {
        target.parentNode.insertBefore(el, target)
    };
    var prepend = function (el, target) {
        if (target.firstChild) {
            before(el, target.firstChild)
        } else {
            target.appendChild(el)
        }
    };

    function appendSvg() {
        var div, svg;
        div = document.createElement("div");
        div.innerHTML = svgSprite;
        svgSprite = null;
        svg = div.getElementsByTagName("svg")[0];
        if (svg) {
            svg.setAttribute("aria-hidden", "true");
            svg.style.position = "absolute";
            svg.style.width = 0;
            svg.style.height = 0;
            svg.style.overflow = "hidden";
            prepend(svg, document.body)
        }
    }

    if (shouldInjectCss && !window.__iconfont__svg__cssinject__) {
        window.__iconfont__svg__cssinject__ = true;
        try {
            document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")
        } catch (e) {
            console && console.log(e)
        }
    }
    ready(appendSvg)
})(window)