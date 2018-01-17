function comSelect() {
    $(document).on("click", ".gf-select > span", function() {
        $(this).closest(".gf-select").css("z-index", 100), $(".gf-select ul").hide(), $(this).next("ul").children().length > 4 ? $(this).next("ul").css({
            height: 154,
            overflow: "auto"
        }) : $(this).next("ul").css({
            height: "auto"
        }), $(this).next("ul").show()
    }), $(document).on("click", ".gf-select > span > i", function() {
        return $(this).closest("span").next("ul").hide(), !1
    }), $(document).on("click", ".gf-select ul li", function() {
        var t = $(this).closest("ul"),
            e = $(this).closest(".gf-select"),
            i = $(this).attr("data-value"),
            s = $(this).text();
        if ($(this).closest(".gf-select").hasClass("noclick")) return t.hide(), !1;
        e.css("z-index", 1), e.find("em").html(s), e.find("input[type='hidden']").val(0 != i ? i : ""), t.hide()
    }), $(document).on("click", function(t) {
        0 == $(t.target).closest(".gf-select").length && ($(".gf-select").css("z-index", 1), $(".gf-select ul").hide())
    })
}

function selectCity(t) {
    var e = {
            domSelect: ["#province", "#city", "#area"],
            domInit: ["请选择省份", "请选择城市", "请选择区县"]
        },
        i = $.extend(e, t),
        s = {},
        a = function() {
            var t = s,
                e = ["<li>" + i.domInit[1] + "</li>"],
                a = $(this).attr("name");
            if (a && "" != a) {
                for (var n = t.city[a], h = 0; h < n.length; h++) e.push('<li data-value="' + n[h].id + '" name="' + n[h].id + '">' + n[h].name + "</li>");
                $(i.domSelect[1]).find("ul").html(e.join("\n"))
            } else $(i.domSelect[1]).find("ul").html(e.join("\n"));
            $(i.domSelect[1]).find("ul li:first").trigger("click")
        },
        n = function() {
            var t = s,
                e = ["<li>" + i.domInit[2] + "</li>"],
                a = $(this).attr("name");
            if (a && "" != a) {
                for (var n = t.district[a], h = 0; h < n.length; h++) e.push('<li data-value="' + n[h].id + '" name="' + n[h].id + '">' + n[h].name + "</li>");
                $(i.domSelect[2]).find("ul").html(e.join("\n"))
            } else $(i.domSelect[2]).find("ul").html(e.join("\n"));
            $(i.domSelect[2]).find("ul li:first").trigger("click")
        },
        h = function(t) {
            for (var e = ["<li>" + i.domInit[0] + "</li>"], a = t.province, n = $(i.domSelect[0]).find("input").val(), h = $(i.domSelect[1]).find("input").val(), r = $(i.domSelect[2]).find("input").val(), o = 0; o < a.length; o++) e.push('<li data-value="' + a[o].id + '" name="' + a[o].id + '">' + a[o].name + "</li>");
            $(i.domSelect[0]).find("ul").html(e.join("\n")), s = t, "" != n && $(i.domSelect[0]).find("ul li[data-value='" + n + "']").click(), "" != h && $(i.domSelect[1]).find("ul li[data-value='" + h + "']").click(), "" != r && $(i.domSelect[2]).find("ul li[data-value='" + r + "']").click()
        },
        r = {
            url: "http://127.0.0.1/tsds/static/js/allcity.js",
            dataType: "jsonp",
            jsonpCallback: "callback",
            success: h
        };
    $.ajax(r), $(document).on("click", i.domSelect[0] + " li", a), $(document).on("click", i.domSelect[1] + " li", n)
}! function($) {
    function t() {
        return new Date(Date.UTC.apply(Date, arguments))
    }
    var e = function(t, e) {
        var a = this;
        this.element = $(t), this.autoShow = e.autoShow || !0, this.appendTo = e.appendTo || "body", this.closeButton = e.closeButton, this.language = e.language || this.element.data("date-language") || "en", this.language = this.language in i ? this.language : this.language.split("-")[0], this.language = this.language in i ? this.language : "en", this.isRTL = i[this.language].rtl || !1, this.format = s.parseFormat(e.format || this.element.data("date-format") || i[this.language].format || "mm/dd/yyyy"), this.isInline = !1, this.isInput = this.element.is("input"), this.component = !!this.element.is(".date") && this.element.find(".prefix, .postfix"), this.hasInput = this.component && this.element.find("input").length, this.disableDblClickSelection = e.disableDblClickSelection, this.onRender = e.onRender || function() {}, this.component && 0 === this.component.length && (this.component = !1), this.linkField = e.linkField || this.element.data("link-field") || !1, this.linkFormat = s.parseFormat(e.linkFormat || this.element.data("link-format") || "yyyy-mm-dd hh:ii:ss"), this.minuteStep = e.minuteStep || this.element.data("minute-step") || 5, this.pickerPosition = e.pickerPosition || this.element.data("picker-position") || "bottom-right", this.initialDate = e.initialDate || null, this._attachEvents(), this.minView = 0, "minView" in e ? this.minView = e.minView : "minView" in this.element.data() && (this.minView = this.element.data("min-view")), this.minView = s.convertViewMode(this.minView), this.maxView = s.modes.length - 1, "maxView" in e ? this.maxView = e.maxView : "maxView" in this.element.data() && (this.maxView = this.element.data("max-view")), this.maxView = s.convertViewMode(this.maxView), this.startViewMode = "month", "startView" in e ? this.startViewMode = e.startView : "startView" in this.element.data() && (this.startViewMode = this.element.data("start-view")), this.startViewMode = s.convertViewMode(this.startViewMode), this.viewMode = this.startViewMode, "minView" in e || "maxView" in e || this.element.data("min-view") && !this.element.data("max-view") || (this.pickTime = !1, "pickTime" in e && (this.pickTime = e.pickTime), 1 == this.pickTime ? (this.minView = 0, this.maxView = 4) : (this.minView = 2, this.maxView = 4)), this.forceParse = !0, "forceParse" in e ? this.forceParse = e.forceParse : "dateForceParse" in this.element.data() && (this.forceParse = this.element.data("date-force-parse")), this.picker = $(s.template).appendTo(this.isInline ? this.element : this.appendTo).on({
            click: $.proxy(this.click, this),
            mousedown: $.proxy(this.mousedown, this)
        }), this.closeButton ? this.picker.find("a.datepicker-close").show() : this.picker.find("a.datepicker-close").hide(), this.isInline ? this.picker.addClass("datepicker-inline") : this.picker.addClass("datepicker-dropdown dropdown-menu"), this.isRTL && (this.picker.addClass("datepicker-rtl"), this.picker.find(".prev i, .next i").toggleClass("fa-chevron-left fa-chevron-right")), $(document).on("mousedown", function(t) {
            0 === $(t.target).closest(".datepicker.datepicker-inline, .datepicker.datepicker-dropdown").length && a.hide()
        }), this.autoclose = !0, "autoclose" in e ? this.autoclose = e.autoclose : "dateAutoclose" in this.element.data() && (this.autoclose = this.element.data("date-autoclose")), this.keyboardNavigation = !0, "keyboardNavigation" in e ? this.keyboardNavigation = e.keyboardNavigation : "dateKeyboardNavigation" in this.element.data() && (this.keyboardNavigation = this.element.data("date-keyboard-navigation")), this.todayBtn = e.todayBtn || this.element.data("date-today-btn") || !1, this.todayHighlight = e.todayHighlight || this.element.data("date-today-highlight") || !1, this.calendarWeeks = !1, "calendarWeeks" in e ? this.calendarWeeks = e.calendarWeeks : "dateCalendarWeeks" in this.element.data() && (this.calendarWeeks = this.element.data("date-calendar-weeks")), this.calendarWeeks && this.picker.find("tfoot th.today").attr("colspan", function(t, e) {
            return parseInt(e) + 1
        }), this.weekStart = (e.weekStart || this.element.data("date-weekstart") || i[this.language].weekStart || 0) % 7, this.weekEnd = (this.weekStart + 6) % 7, this.startDate = -1 / 0, this.endDate = 1 / 0, this.daysOfWeekDisabled = [], this.setStartDate(e.startDate || this.element.data("date-startdate")), this.setEndDate(e.endDate || this.element.data("date-enddate")), this.setDaysOfWeekDisabled(e.daysOfWeekDisabled || this.element.data("date-days-of-week-disabled")), this.fillDow(), this.fillMonths(), this.update(), this.showMode(), this.isInline && this.show()
    };
    e.prototype = {
        constructor: e,
        _events: [],
        _attachEvents: function() {
            this._detachEvents(), this.isInput ? this._events = [
                [this.element, {
                    focus: this.autoShow ? $.proxy(this.show, this) : function() {},
                    keyup: $.proxy(this.update, this),
                    keydown: $.proxy(this.keydown, this)
                }]
            ] : this.component && this.hasInput ? this._events = [
                [this.element.find("input"), {
                    focus: this.autoShow ? $.proxy(this.show, this) : function() {},
                    keyup: $.proxy(this.update, this),
                    keydown: $.proxy(this.keydown, this)
                }],
                [this.component, {
                    click: $.proxy(this.show, this)
                }]
            ] : this.element.is("div") ? this.isInline = !0 : this._events = [
                [this.element, {
                    click: $.proxy(this.show, this)
                }]
            ], this.disableDblClickSelection && (this._events[this._events.length] = [this.element, {
                dblclick: function(t) {
                    t.preventDefault(), t.stopPropagation(), $(this).blur()
                }
            }]);
            for (var t, e, i = 0; i < this._events.length; i++) t = this._events[i][0], e = this._events[i][1], t.on(e)
        },
        _detachEvents: function() {
            for (var t, e, i = 0; i < this._events.length; i++) t = this._events[i][0], e = this._events[i][1], t.off(e);
            this._events = []
        },
        show: function(t) {
            this.picker.show(), this.height = this.component ? this.component.outerHeight() : this.element.outerHeight(), this.update(), this.place(), $(window).on("resize", $.proxy(this.place, this)), t && (t.stopPropagation(), t.preventDefault()), this.element.trigger({
                type: "show",
                date: this.date
            })
        },
        hide: function(t) {
            this.isInline || this.picker.is(":visible") && (this.picker.hide(), $(window).off("resize", this.place), this.viewMode = this.startViewMode, this.showMode(), this.isInput || $(document).off("mousedown", this.hide), this.forceParse && (this.isInput && this.element.val() || this.hasInput && this.element.find("input").val()) && this.setValue(), this.element.trigger({
                type: "hide",
                date: this.date
            }))
        },
        remove: function() {
            this._detachEvents(), this.picker.remove(), delete this.element.data().datepicker
        },
        getDate: function() {
            var t = this.getUTCDate();
            return new Date(t.getTime() + 6e4 * t.getTimezoneOffset())
        },
        getUTCDate: function() {
            return this.date
        },
        setDate: function(t) {
            this.setUTCDate(new Date(t.getTime() - 6e4 * t.getTimezoneOffset()))
        },
        setUTCDate: function(t) {
            this.date = t, this.setValue()
        },
        setValue: function() {
            var t = this.getFormattedDate();
            this.isInput ? this.element.val(t) : (this.component && this.element.find("input").val(t), this.element.data("date", t))
        },
        getFormattedDate: function(t) {
            return void 0 === t && (t = this.format), s.formatDate(this.date, t, this.language)
        },
        setStartDate: function(t) {
            this.startDate = t || -1 / 0, this.startDate !== -1 / 0 && (this.startDate = s.parseDate(this.startDate, this.format, this.language)), this.update(), this.updateNavArrows()
        },
        setEndDate: function(t) {
            this.endDate = t || 1 / 0, this.endDate !== 1 / 0 && (this.endDate = s.parseDate(this.endDate, this.format, this.language)), this.update(), this.updateNavArrows()
        },
        setDaysOfWeekDisabled: function(t) {
            this.daysOfWeekDisabled = t || [], $.isArray(this.daysOfWeekDisabled) || (this.daysOfWeekDisabled = this.daysOfWeekDisabled.split(/,\s*/)), this.daysOfWeekDisabled = $.map(this.daysOfWeekDisabled, function(t) {
                return parseInt(t, 10)
            }), this.update(), this.updateNavArrows()
        },
        place: function() {
            if (!this.isInline) {
                var t = parseInt(this.element.parents().filter(function() {
                        return "auto" != $(this).css("z-index")
                    }).first().css("z-index")) + 10,
                    e = this.component ? this.component : this.element,
                    i = e.offset(),
                    s = e.outerHeight() + parseInt(e.css("margin-top")),
                    a = e.outerWidth() + parseInt(e.css("margin-left")),
                    n = i.top + s,
                    h = i.left;
                n + this.picker.outerHeight() >= $(window).scrollTop() + $(window).height() && (n = i.top - this.picker.outerHeight()), i.left + this.picker.width() >= $(window).width() && (h = i.left + a - this.picker.width()), this.picker.css({
                    top: n,
                    left: h,
                    zIndex: t
                })
            }
        },
        update: function() {
            var t, e = !1,
                i = this.isInput ? this.element.val() : this.element.data("date") || this.element.find("input").val();
            arguments && arguments.length && ("string" == typeof arguments[0] || arguments[0] instanceof Date) ? (t = arguments[0], e = !0) : t = i || null == this.initialDate ? this.isInput ? this.element.val() : this.element.data("date") || this.element.find("input").val() : this.initialDate, this.date = s.parseDate(t, this.format, this.language), (e || null != this.initialDate) && this.setValue(), this.date < this.startDate ? this.viewDate = new Date(this.startDate.valueOf()) : this.date > this.endDate ? this.viewDate = new Date(this.endDate.valueOf()) : this.viewDate = new Date(this.date.valueOf()), this.fill()
        },
        fillDow: function() {
            var t = this.weekStart,
                e = "<tr>";
            if (this.calendarWeeks) {
                var s = '<th class="cw">&nbsp;</th>';
                e += s, this.picker.find(".datepicker-days thead tr:first-child").prepend(s)
            }
            for (; t < this.weekStart + 7;) e += '<th class="dow">' + i[this.language].daysMin[t++ % 7] + "</th>";
            e += "</tr>", this.picker.find(".datepicker-days thead").append(e)
        },
        fillMonths: function() {
            for (var t = "", e = 0; e < 12;) t += '<span class="month">' + i[this.language].monthsShort[e++] + "</span>";
            this.picker.find(".datepicker-months td").html(t)
        },
        fill: function() {
            if (null != this.date && null != this.viewDate) {
                var e = new Date(this.viewDate.valueOf()),
                    a = e.getUTCFullYear(),
                    n = e.getUTCMonth(),
                    h = e.getUTCDate(),
                    r = e.getUTCHours(),
                    o = e.getUTCMinutes(),
                    d = this.startDate !== -1 / 0 ? this.startDate.getUTCFullYear() : -1 / 0,
                    l = this.startDate !== -1 / 0 ? this.startDate.getUTCMonth() : -1 / 0,
                    c = this.endDate !== 1 / 0 ? this.endDate.getUTCFullYear() : 1 / 0,
                    u = this.endDate !== 1 / 0 ? this.endDate.getUTCMonth() : 1 / 0,
                    p = this.date && this.date.valueOf(),
                    f = new Date;
                i[this.language].titleFormat || i.en.titleFormat;
                this.picker.find(".datepicker-days thead th:eq(1)").text(i[this.language].months[n] + " " + a), this.picker.find(".datepicker-hours thead th:eq(1)").text(h + " " + i[this.language].months[n] + " " + a), this.picker.find(".datepicker-minutes thead th:eq(1)").text(h + " " + i[this.language].months[n] + " " + a), this.picker.find("tfoot th.today").text(i[this.language].today).toggle(!1 !== this.todayBtn), this.updateNavArrows(), this.fillMonths();
                var v = t(a, n - 1, 28, 0, 0, 0, 0),
                    m = s.getDaysInMonth(v.getUTCFullYear(), v.getUTCMonth());
                v.setUTCDate(m), v.setUTCDate(m - (v.getUTCDay() - this.weekStart + 7) % 7);
                var g = new Date(v.valueOf());
                g.setUTCDate(g.getUTCDate() + 42), g = g.valueOf();
                for (var D, w = []; v.valueOf() < g;) {
                    if (v.getUTCDay() == this.weekStart && (w.push("<tr>"), this.calendarWeeks)) {
                        var k = new Date(v.getUTCFullYear(), v.getUTCMonth(), v.getUTCDate() - v.getDay() + 10 - (this.weekStart && this.weekStart % 7 < 5 && 7)),
                            y = new Date(k.getFullYear(), 0, 4),
                            T = ~~((k - y) / 864e5 / 7 + 1.5);
                        w.push('<td class="cw">' + T + "</td>")
                    }
                    D = " " + this.onRender(v) + " ", v.getUTCFullYear() < a || v.getUTCFullYear() == a && v.getUTCMonth() < n ? D += " old" : (v.getUTCFullYear() > a || v.getUTCFullYear() == a && v.getUTCMonth() > n) && (D += " new"), this.todayHighlight && v.getUTCFullYear() == f.getFullYear() && v.getUTCMonth() == f.getMonth() && v.getUTCDate() == f.getDate() && (D += " today"), p && v.valueOf() == p && (D += " active"), (v.valueOf() < this.startDate || v.valueOf() > this.endDate || -1 !== $.inArray(v.getUTCDay(), this.daysOfWeekDisabled)) && (D += " disabled"), w.push('<td class="day' + D + '">' + v.getUTCDate() + "</td>"), v.getUTCDay() == this.weekEnd && w.push("</tr>"), v.setUTCDate(v.getUTCDate() + 1)
                }
                this.picker.find(".datepicker-days tbody").empty().append(w.join("")), w = [];
                for (var C = 0; C < 24; C++) {
                    var U = t(a, n, h, C);
                    D = "", U.valueOf() + 36e5 < this.startDate || U.valueOf() > this.endDate ? D += " disabled" : r == C && (D += " active"), w.push('<span class="hour' + D + '">' + C + ":00</span>")
                }
                this.picker.find(".datepicker-hours td").html(w.join("")), w = [];
                for (var C = 0; C < 60; C += this.minuteStep) {
                    var U = t(a, n, h, r, C);
                    D = "", U.valueOf() < this.startDate || U.valueOf() > this.endDate ? D += " disabled" : Math.floor(o / this.minuteStep) == Math.floor(C / this.minuteStep) && (D += " active"), w.push('<span class="minute' + D + '">' + r + ":" + (C < 10 ? "0" + C : C) + "</span>")
                }
                this.picker.find(".datepicker-minutes td").html(w.join(""));
                var b = this.date && this.date.getUTCFullYear(),
                    M = this.picker.find(".datepicker-months").find("th:eq(1)").text(a).end().find("span").removeClass("active");
                b && b == a && M.eq(this.date.getUTCMonth()).addClass("active"), (a < d || a > c) && M.addClass("disabled"), a == d && M.slice(0, l).addClass("disabled"), a == c && M.slice(u + 1).addClass("disabled"), w = "", a = 10 * parseInt(a / 10, 10);
                var x = this.picker.find(".datepicker-years").find("th:eq(1)").text(a + "-" + (a + 9)).end().find("td");
                a -= 1;
                for (var C = -1; C < 11; C++) w += '<span class="year' + (-1 == C || 10 == C ? " old" : "") + (b == a ? " active" : "") + (a < d || a > c ? " disabled" : "") + '">' + a + "</span>", a += 1;
                x.html(w)
            }
        },
        updateNavArrows: function() {
            var t = new Date(this.viewDate),
                e = t.getUTCFullYear(),
                i = t.getUTCMonth(),
                s = t.getUTCDate(),
                a = t.getUTCHours();
            switch (this.viewMode) {
                case 0:
                    this.startDate !== -1 / 0 && e <= this.startDate.getUTCFullYear() && i <= this.startDate.getUTCMonth() && s <= this.startDate.getUTCDate() && a <= this.startDate.getUTCHours() ? this.picker.find(".prev").css({
                        visibility: "hidden"
                    }) : this.picker.find(".prev").css({
                        visibility: "visible"
                    }), this.endDate !== 1 / 0 && e >= this.endDate.getUTCFullYear() && i >= this.endDate.getUTCMonth() && s >= this.endDate.getUTCDate() && a >= this.endDate.getUTCHours() ? this.picker.find(".next").css({
                        visibility: "hidden"
                    }) : this.picker.find(".next").css({
                        visibility: "visible"
                    });
                    break;
                case 1:
                    this.startDate !== -1 / 0 && e <= this.startDate.getUTCFullYear() && i <= this.startDate.getUTCMonth() && s <= this.startDate.getUTCDate() ? this.picker.find(".prev").css({
                        visibility: "hidden"
                    }) : this.picker.find(".prev").css({
                        visibility: "visible"
                    }), this.endDate !== 1 / 0 && e >= this.endDate.getUTCFullYear() && i >= this.endDate.getUTCMonth() && s >= this.endDate.getUTCDate() ? this.picker.find(".next").css({
                        visibility: "hidden"
                    }) : this.picker.find(".next").css({
                        visibility: "visible"
                    });
                    break;
                case 2:
                    this.startDate !== -1 / 0 && e <= this.startDate.getUTCFullYear() && i <= this.startDate.getUTCMonth() ? this.picker.find(".prev").css({
                        visibility: "hidden"
                    }) : this.picker.find(".prev").css({
                        visibility: "visible"
                    }), this.endDate !== 1 / 0 && e >= this.endDate.getUTCFullYear() && i >= this.endDate.getUTCMonth() ? this.picker.find(".next").css({
                        visibility: "hidden"
                    }) : this.picker.find(".next").css({
                        visibility: "visible"
                    });
                    break;
                case 3:
                case 4:
                    this.startDate !== -1 / 0 && e <= this.startDate.getUTCFullYear() ? this.picker.find(".prev").css({
                        visibility: "hidden"
                    }) : this.picker.find(".prev").css({
                        visibility: "visible"
                    }), this.endDate !== 1 / 0 && e >= this.endDate.getUTCFullYear() ? this.picker.find(".next").css({
                        visibility: "hidden"
                    }) : this.picker.find(".next").css({
                        visibility: "visible"
                    })
            }
        },
        click: function(e) {
            e.stopPropagation(), e.preventDefault(), ($(e.target).hasClass("datepicker-close") || $(e.target).parent().hasClass("datepicker-close")) && this.hide();
            var i = $(e.target).closest("span, td, th");
            if (1 == i.length) {
                if (i.is(".disabled")) return void this.element.trigger({
                    type: "outOfRange",
                    date: this.viewDate,
                    startDate: this.startDate,
                    endDate: this.endDate
                });
                switch (i[0].nodeName.toLowerCase()) {
                    case "th":
                        switch (i[0].className) {
                            case "date-switch":
                                this.showMode(1);
                                break;
                            case "prev":
                            case "next":
                                var a = s.modes[this.viewMode].navStep * ("prev" == i[0].className ? -1 : 1);
                                switch (this.viewMode) {
                                    case 0:
                                        this.viewDate = this.moveHour(this.viewDate, a);
                                        break;
                                    case 1:
                                        this.viewDate = this.moveDate(this.viewDate, a);
                                        break;
                                    case 2:
                                        this.viewDate = this.moveMonth(this.viewDate, a);
                                        break;
                                    case 3:
                                    case 4:
                                        this.viewDate = this.moveYear(this.viewDate, a)
                                }
                                this.fill();
                                break;
                            case "today":
                                var n = new Date;
                                n = t(n.getFullYear(), n.getMonth(), n.getDate(), n.getHours(), n.getMinutes(), n.getSeconds()), this.viewMode = this.startViewMode, this.showMode(0), this._setDate(n)
                        }
                        break;
                    case "span":
                        if (!i.is(".disabled")) {
                            if (i.is(".month"))
                                if (3 === this.minView) {
                                    var h = i.parent().find("span").index(i) || 0,
                                        r = this.viewDate.getUTCFullYear(),
                                        o = 1,
                                        d = this.viewDate.getUTCHours(),
                                        l = this.viewDate.getUTCMinutes(),
                                        c = this.viewDate.getUTCSeconds();
                                    this._setDate(t(r, h, o, d, l, c, 0))
                                } else {
                                    this.viewDate.setUTCDate(1);
                                    var h = i.parent().find("span").index(i);
                                    this.viewDate.setUTCMonth(h), this.element.trigger({
                                        type: "changeMonth",
                                        date: this.viewDate
                                    })
                                }
                            else if (i.is(".year"))
                                if (4 === this.minView) {
                                    var r = parseInt(i.text(), 10) || 0,
                                        h = 0,
                                        o = 1,
                                        d = this.viewDate.getUTCHours(),
                                        l = this.viewDate.getUTCMinutes(),
                                        c = this.viewDate.getUTCSeconds();
                                    this._setDate(t(r, h, o, d, l, c, 0))
                                } else {
                                    this.viewDate.setUTCDate(1);
                                    var r = parseInt(i.text(), 10) || 0;
                                    this.viewDate.setUTCFullYear(r), this.element.trigger({
                                        type: "changeYear",
                                        date: this.viewDate
                                    })
                                }
                            else if (i.is(".hour")) {
                                var d = parseInt(i.text(), 10) || 0,
                                    r = this.viewDate.getUTCFullYear(),
                                    h = this.viewDate.getUTCMonth(),
                                    o = this.viewDate.getUTCDate(),
                                    l = this.viewDate.getUTCMinutes(),
                                    c = this.viewDate.getUTCSeconds();
                                this._setDate(t(r, h, o, d, l, c, 0))
                            } else if (i.is(".minute")) {
                                var l = parseInt(i.text().substr(i.text().indexOf(":") + 1), 10) || 0,
                                    r = this.viewDate.getUTCFullYear(),
                                    h = this.viewDate.getUTCMonth(),
                                    o = this.viewDate.getUTCDate(),
                                    d = this.viewDate.getUTCHours(),
                                    c = this.viewDate.getUTCSeconds();
                                this._setDate(t(r, h, o, d, l, c, 0))
                            }
                            if (0 != this.viewMode) {
                                var u = this.viewMode;
                                this.showMode(-1), this.fill(), u == this.viewMode && this.autoclose && this.hide()
                            } else this.fill(), this.autoclose && this.hide()
                        }
                        break;
                    case "td":
                        if (i.is(".day") && !i.is(".disabled")) {
                            var o = parseInt(i.text(), 10) || 1,
                                r = this.viewDate.getUTCFullYear(),
                                h = this.viewDate.getUTCMonth(),
                                d = this.viewDate.getUTCHours(),
                                l = this.viewDate.getUTCMinutes(),
                                c = this.viewDate.getUTCSeconds();
                            i.is(".old") ? 0 === h ? (h = 11, r -= 1) : h -= 1 : i.is(".new") && (11 == h ? (h = 0, r += 1) : h += 1), this._setDate(t(r, h, o, d, l, c, 0))
                        }
                        var u = this.viewMode;
                        this.showMode(-1), this.fill(), u == this.viewMode && this.autoclose && this.hide()
                }
            }
        },
        _setDate: function(t, e) {
            e && "date" != e || (this.date = t), e && "view" != e || (this.viewDate = t), this.fill(), this.setValue(), this.element.trigger({
                type: "changeDate",
                date: this.date
            });
            var i;
            this.isInput ? i = this.element : this.component && (i = this.element.find("input")), i && (i.change(), this.autoclose)
        },
        moveHour: function(t, e) {
            if (!e) return t;
            var i = new Date(t.valueOf());
            return e = e > 0 ? 1 : -1, i.setUTCHours(i.getUTCHours() + e), i
        },
        moveDate: function(t, e) {
            if (!e) return t;
            var i = new Date(t.valueOf());
            return e = e > 0 ? 1 : -1, i.setUTCDate(i.getUTCDate() + e), i
        },
        moveMonth: function(t, e) {
            if (!e) return t;
            var i, s, a = new Date(t.valueOf()),
                n = a.getUTCDate(),
                h = a.getUTCMonth(),
                r = Math.abs(e);
            if (e = e > 0 ? 1 : -1, 1 == r) s = -1 == e ? function() {
                return a.getUTCMonth() == h
            } : function() {
                return a.getUTCMonth() != i
            }, i = h + e, a.setUTCMonth(i), (i < 0 || i > 11) && (i = (i + 12) % 12);
            else {
                for (var o = 0; o < r; o++) a = this.moveMonth(a, e);
                i = a.getUTCMonth(), a.setUTCDate(n), s = function() {
                    return i != a.getUTCMonth()
                }
            }
            for (; s();) a.setUTCDate(--n), a.setUTCMonth(i);
            return a
        },
        moveYear: function(t, e) {
            return this.moveMonth(t, 12 * e)
        },
        dateWithinRange: function(t) {
            return t >= this.startDate && t <= this.endDate
        },
        keydown: function(t) {
            if (this.picker.is(":not(:visible)")) return void(27 == t.keyCode && this.show());
            var e, i, s, a = !1;
            switch (t.keyCode) {
                case 27:
                    this.hide(), t.preventDefault();
                    break;
                case 37:
                case 39:
                    if (!this.keyboardNavigation) break;
                    e = 37 == t.keyCode ? -1 : 1, t.ctrlKey ? (i = this.moveYear(this.date, e), s = this.moveYear(this.viewDate, e)) : t.shiftKey ? (i = this.moveMonth(this.date, e), s = this.moveMonth(this.viewDate, e)) : (i = new Date(this.date.valueOf()), i.setUTCDate(this.date.getUTCDate() + e), s = new Date(this.viewDate.valueOf()), s.setUTCDate(this.viewDate.getUTCDate() + e)), this.dateWithinRange(i) && (this.date = i, this.viewDate = s, this.setValue(), this.update(), t.preventDefault(), a = !0);
                    break;
                case 38:
                case 40:
                    if (!this.keyboardNavigation) break;
                    e = 38 == t.keyCode ? -1 : 1, t.ctrlKey ? (i = this.moveYear(this.date, e), s = this.moveYear(this.viewDate, e)) : t.shiftKey ? (i = this.moveMonth(this.date, e), s = this.moveMonth(this.viewDate, e)) : (i = new Date(this.date.valueOf()), i.setUTCDate(this.date.getUTCDate() + 7 * e), s = new Date(this.viewDate.valueOf()), s.setUTCDate(this.viewDate.getUTCDate() + 7 * e)), this.dateWithinRange(i) && (this.date = i, this.viewDate = s, this.setValue(), this.update(), t.preventDefault(), a = !0);
                    break;
                case 13:
                    this.hide(), t.preventDefault();
                    break;
                case 9:
                    this.hide()
            }
            if (a) {
                this.element.trigger({
                    type: "changeDate",
                    date: this.date
                });
                var n;
                this.isInput ? n = this.element : this.component && (n = this.element.find("input")), n && n.change()
            }
        },
        showMode: function(t) {
            if (t) {
                var e = Math.max(0, Math.min(s.modes.length - 1, this.viewMode + t));
                e >= this.minView && e <= this.maxView && (this.viewMode = e)
            }
            this.picker.find(">div").hide().filter(".datepicker-" + s.modes[this.viewMode].clsName).css("display", "block"), this.updateNavArrows()
        },
        reset: function(t) {
            this._setDate(null, "date")
        }
    }, $.fn.fdatepicker = function(t) {
        var i = Array.apply(null, arguments);
        return i.shift(), this.each(function() {
            var s = $(this),
                a = s.data("datepicker"),
                n = "object" == typeof t && t;
            a || s.data("datepicker", a = new e(this, $.extend({}, $.fn.fdatepicker.defaults, n))), "string" == typeof t && "function" == typeof a[t] && a[t].apply(a, i)
        })
    }, $.fn.fdatepicker.defaults = {
        onRender: function(t) {
            return ""
        }
    }, $.fn.fdatepicker.Constructor = e;
    var i = $.fn.fdatepicker.dates = {
            en: {
                days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
                daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
                daysMin: ["日", "一", "二", "三", "四", "五", "六"],
                months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                monthsShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
                today: "今天",
                titleFormat: "MM yyyy"
            }
        },
        s = {
            modes: [{
                clsName: "minutes",
                navFnc: "Hours",
                navStep: 1
            }, {
                clsName: "hours",
                navFnc: "Date",
                navStep: 1
            }, {
                clsName: "days",
                navFnc: "Month",
                navStep: 1
            }, {
                clsName: "months",
                navFnc: "FullYear",
                navStep: 1
            }, {
                clsName: "years",
                navFnc: "FullYear",
                navStep: 10
            }],
            isLeapYear: function(t) {
                return t % 4 == 0 && t % 100 != 0 || t % 400 == 0
            },
            getDaysInMonth: function(t, e) {
                return [31, s.isLeapYear(t) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][e]
            },
            validParts: /hh?|ii?|ss?|dd?|mm?|MM?|yy(?:yy)?/g,
            nonpunctuation: /[^ -\/:-@\[\u3400-\u9fff-`{-~\t\n\r]+/g,
            parseFormat: function(t) {
                var e = t.replace(this.validParts, "\0").split("\0"),
                    i = t.match(this.validParts);
                if (!e || !e.length || !i || 0 === i.length) throw new Error("Invalid date format.");
                return {
                    separators: e,
                    parts: i
                }
            },
            parseDate: function(e, s, a) {
                if (e instanceof Date) return new Date(e.valueOf() - 6e4 * e.getTimezoneOffset());
                if (/^\d{4}\-\d{1,2}\-\d{1,2}$/.test(e) && (s = this.parseFormat("yyyy-mm-dd")), /^\d{4}\-\d{1,2}\-\d{1,2}[T ]\d{1,2}\:\d{1,2}$/.test(e) && (s = this.parseFormat("yyyy-mm-dd hh:ii")), /^\d{4}\-\d{1,2}\-\d{1,2}[T ]\d{1,2}\:\d{1,2}\:\d{1,2}[Z]{0,1}$/.test(e) && (s = this.parseFormat("yyyy-mm-dd hh:ii:ss")), /^[-+]\d+[dmwy]([\s,]+[-+]\d+[dmwy])*$/.test(e)) {
                    var n, h, r = /([-+]\d+)([dmwy])/,
                        o = e.match(/([-+]\d+)([dmwy])/g);
                    e = new Date;
                    for (var d = 0; d < o.length; d++) switch (n = r.exec(o[d]), h = parseInt(n[1]), n[2]) {
                        case "d":
                            e.setUTCDate(e.getUTCDate() + h);
                            break;
                        case "m":
                            e = Datetimepicker.prototype.moveMonth.call(Datetimepicker.prototype, e, h);
                            break;
                        case "w":
                            e.setUTCDate(e.getUTCDate() + 7 * h);
                            break;
                        case "y":
                            e = Datetimepicker.prototype.moveYear.call(Datetimepicker.prototype, e, h)
                    }
                    return t(e.getUTCFullYear(), e.getUTCMonth(), e.getUTCDate(), e.getUTCHours(), e.getUTCMinutes(), e.getUTCSeconds())
                }
                var l, c, n, o = e && e.match(this.nonpunctuation) || [],
                    e = new Date,
                    u = {},
                    p = ["hh", "h", "ii", "i", "ss", "s", "yyyy", "yy", "M", "MM", "m", "mm", "d", "dd"],
                    f = {
                        hh: function(t, e) {
                            return t.setUTCHours(e)
                        },
                        h: function(t, e) {
                            return t.setUTCHours(e)
                        },
                        ii: function(t, e) {
                            return t.setUTCMinutes(e)
                        },
                        i: function(t, e) {
                            return t.setUTCMinutes(e)
                        },
                        ss: function(t, e) {
                            return t.setUTCSeconds(e)
                        },
                        s: function(t, e) {
                            return t.setUTCSeconds(e)
                        },
                        yyyy: function(t, e) {
                            return t.setUTCFullYear(e)
                        },
                        yy: function(t, e) {
                            return t.setUTCFullYear(2e3 + e)
                        },
                        m: function(t, e) {
                            for (e -= 1; e < 0;) e += 12;
                            for (e %= 12, t.setUTCMonth(e); t.getUTCMonth() != e;) t.setUTCDate(t.getUTCDate() - 1);
                            return t
                        },
                        d: function(t, e) {
                            return t.setUTCDate(e)
                        }
                    };
                if (f.M = f.MM = f.mm = f.m, f.dd = f.d, e = t(e.getFullYear(), e.getMonth(), e.getDate(), 0, 0, 0), o.length == s.parts.length) {
                    for (var d = 0, v = s.parts.length; d < v; d++) {
                        if (l = parseInt(o[d], 10), n = s.parts[d], isNaN(l)) switch (n) {
                            case "MM":
                                c = $(i[a].months).filter(function() {
                                    var t = this.slice(0, o[d].length);
                                    return t == o[d].slice(0, t.length)
                                }), l = $.inArray(c[0], i[a].months) + 1;
                                break;
                            case "M":
                                c = $(i[a].monthsShort).filter(function() {
                                    var t = this.slice(0, o[d].length);
                                    return t == o[d].slice(0, t.length)
                                }), l = $.inArray(c[0], i[a].monthsShort) + 1
                        }
                        u[n] = l
                    }
                    for (var m, d = 0; d < p.length; d++)(m = p[d]) in u && !isNaN(u[m]) && f[m](e, u[m])
                }
                return e
            },
            formatDate: function(t, e, s) {
                if (null == t) return "";
                var a = {
                    h: t.getUTCHours(),
                    i: t.getUTCMinutes(),
                    s: t.getUTCSeconds(),
                    d: t.getUTCDate(),
                    m: t.getUTCMonth() + 1,
                    M: i[s].monthsShort[t.getUTCMonth()],
                    MM: i[s].months[t.getUTCMonth()],
                    yy: t.getUTCFullYear().toString().substring(2),
                    yyyy: t.getUTCFullYear()
                };
                a.hh = (a.h < 10 ? "0" : "") + a.h, a.ii = (a.i < 10 ? "0" : "") + a.i, a.ss = (a.s < 10 ? "0" : "") + a.s, a.dd = (a.d < 10 ? "0" : "") + a.d, a.mm = (a.m < 10 ? "0" : "") + a.m;
                for (var t = [], n = $.extend([], e.separators), h = 0, r = e.parts.length; h < r; h++) n.length && t.push(n.shift()), t.push(a[e.parts[h]]);
                return t.join("")
            },
            convertViewMode: function(t) {
                switch (t) {
                    case 4:
                    case "decade":
                        t = 4;
                        break;
                    case 3:
                    case "year":
                        t = 3;
                        break;
                    case 2:
                    case "month":
                        t = 2;
                        break;
                    case 1:
                    case "day":
                        t = 1;
                        break;
                    case 0:
                    case "hour":
                        t = 0
                }
                return t
            },
            headTemplate: '<thead><tr><th class="prev"><i class="fa fa-chevron-left fi-arrow-left"/></th><th colspan="5" class="date-switch"></th><th class="next"><i class="fa fa-chevron-right fi-arrow-right"/></th></tr></thead>',
            contTemplate: '<tbody><tr><td colspan="7"></td></tr></tbody>',
            footTemplate: '<tfoot><tr><th colspan="7" class="today"></th></tr></tfoot>'
        };
    s.template = '<div class="datepicker"><div class="datepicker-minutes"><table class=" table-condensed">' + s.headTemplate + s.contTemplate + s.footTemplate + '</table></div><div class="datepicker-hours"><table class=" table-condensed">' + s.headTemplate + s.contTemplate + s.footTemplate + '</table></div><div class="datepicker-days"><table class=" table-condensed">' + s.headTemplate + "<tbody></tbody>" + s.footTemplate + '</table></div><div class="datepicker-months"><table class="table-condensed">' + s.headTemplate + s.contTemplate + s.footTemplate + '</table></div><div class="datepicker-years"><table class="table-condensed">' + s.headTemplate + s.contTemplate + s.footTemplate + '</table></div><a class="button datepicker-close tiny alert right" style="width:auto;"><i class="fa fa-remove fa-times fi-x"></i></a></div>', $.fn.fdatepicker.DPGlobal = s
}(window.jQuery);
