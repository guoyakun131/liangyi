function evaluat(){for(var t=document.getElementById("grade"),e=t.getElementsByTagName("span"),n=5,a=0;a<e.length;a++)e[a].setAttribute("data-index",a);onload=function(){for(var t=0;t<e.length;t++)e[t].onmouseover=function(){for(var t=parseInt(this.getAttribute("data-index")),n=0;n<=t;n++)$(e[n]).addClass("am-active");for(var a=t+1;a<5;a++)$(e[a]).removeClass("am-active")};for(var t=0;t<e.length;t++)e[t].onclick=function(){var t=parseInt(this.getAttribute("data-index"));n=t;for(var a=0;a<=t;a++)$(e[a]).addClass("am-active")};document.getElementById("grade").onmouseout=function(){if(n>=0&&n<=4){for(var t=0;t<=n;t++)$(e[t]).addClass("am-active");for(var a=n+1;a<5;a++)$(e[a]).removeClass("am-active")}else for(var t=0;t<e.length;t++)$(e[t]).removeClass("am-active")}}}function getEvaluat(t){t=$("."+t);var e=0;return t.each(function(){$(this).hasClass("am-active")&&e++}),e}function sendCode(t){function e(){t.text("重新发送"+n+"s").unbind(),n-=1,handle=setTimeout(function(){e()},1e3),n<0&&(clearTimeout(handle),t.text("发送验证码").bind("click",function(){sendCode()}))}var t=$("#"+t),n=60;e()}function getUrlRequest(){var t=location.search,e=new Object;if(-1!=t.indexOf("?")){var n=t.substr(1);if(-1!=n.indexOf("&")){strs=n.split("&");for(var a=0;a<strs.length;a++)e[strs[a].split("=")[0]]=unescape(strs[a].split("=")[1])}else{var o=n.substring(0,n.indexOf("=")),i=n.substr(n.indexOf("=")+1);e[o]=decodeURI(i)}}return e}function radioCheck(t,e){var n=$(t).find(e);n.eq(0).prop("check",!0),n.on("click",function(){$(this).prop("check",!0).siblings().prop("check",!1)})}function cBoxCheck(t,e){$(t).find(e).on("click",function(){$(this).prop("check")?$(this).prop("check",!1):$(this).prop("check",!0)})}function checkState(t,e){$(t).find(e).each(function(){$(this).prop("check")&&console.log($(this).val())})}function debounce(t,e,n){var a;return function(){var o=this,i=arguments,c=function(){a=null,n||t.apply(o,i)},r=n&!a;clearTimeout(a),a=setTimeout(c,e),r&&t.apply(o,i)}}function throttle(t,e,n){var a,o=new Date;return function(){var i=this,c=arguments,r=new Date;clearTimeout(a),r-o>=n?(t.apply(i,c),o=r):a=setTimeout(t,e)}}function warn(t){$(".toast-box").show(),$(".toast").html(t).stop().animate({opacity:1},500).animate({opacity:0},500,function(){$(".toast-box").hide()})}function clickTouch(){var t=0,e=0;$(document).on("click",".btn",function(t){var e=$(".left-menu").hasClass("am-active");console.log(e),e?$(".left-menu").removeClass("am-active"):$(".left-menu").addClass("am-active")}),$("article").on("touchstart",function(n){t=n.originalEvent.targetTouches[0].pageX,$("article").on("touchmove",function(t){t.stopPropagation(),e=t.originalEvent.targetTouches[0].pageX})}),$("article").on("touchend",function(n){console.log(e-t),e-t>0?$(".left-menu").addClass("am-active"):$(".left-menu").removeClass("am-active")})}!function(t,e){var n=t.documentElement,a="orientationchange"in window?"orientationchange":"resize",o=function(){var t=n.clientWidth;t&&(n.style.fontSize=t>=640?"100px":t/640*100+"px")};t.addEventListener&&(e.addEventListener(a,o,!1),t.addEventListener("DOMContentLoaded",o,!1))}(document,window);