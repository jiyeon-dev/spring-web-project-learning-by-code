console.log("Reply Module .... ");

var replyService = (function() {
    function add(reply, callback, error) {
        console.log("reply .... ");

        $.ajax({
            type: 'post',
            url: '/replies/new',
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) callback(result);
            },
            error: function (xhr, status, err) {
                if (error) error(err);
            }
        });
    }

    function getList(param, callback, error) {
        var bno = param.bno;
        var page = param.page || 1;

        $.getJSON("/replies/pages/"+bno+"/"+page+".json",
            function (data) {
                if (callback) {
                    callback(data);
                }
            }).fail(function (xhr, status, err) {
                if (error) error(err);
        });
    }

    function remove(rno, callback, error) {
        $.ajax({
            type: 'delete',
            url: '/replies/' + rno,
            success: function (deleteResult, status, xhr) {
                if (callback) callback(deleteResult);
            },
            error: function (xhr, status, err) {
                if (error) error(err);
            }
        });
    }

    function update(reply, callback, error) {
        console.log("RNO: " + reply.rno)
        $.ajax({
            type: "put",
            url: "/replies/" + reply.rno,
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) callback(result);
            },
            error: function (xhr, status, err) {
                if (error) error(err);
            }
        })
    }

    function get(rno, callback, error) {
        $.get("/replies/" + rno + ".json", function (result) {
            if (callback) callback(result);
        }).fail(function (xhr, status, err) {
            if (error) error(err);
        })
    }

    function displayTime(timeValue) {
        var today = new Date();
        var gap = today.getTime() - timeValue;
        var dateObj = new Date(timeValue);
        var str = "";

        if (gap < (1000 * 60 * 60 * 24)) {  // 24시간 이전 -> 시간만 표시
            var hh = dateObj.getHours();
            var mm = dateObj.getMinutes();
            var ss = dateObj.getSeconds();

            return [
                (hh > 9 ? '' : '0') + hh, ":",
                (mm > 9 ? '' : '0') + mm, ":",
                (ss > 9 ? '' : '0') + ss
            ].join("");
        } else {  // 24시간 이후 -> 날짜만 표시
            var yy = dateObj.getFullYear();
            var mm = dateObj.getMonth() + 1;
            var dd = dateObj.getDate();

            return [
                yy, "/",
                (mm > 9 ? '' : '0') + mm, "/",
                (dd > 9 ? '' : '0') + dd
            ].join("");
        }
    }

    return {
        add: add,
        getList: getList,
        remove: remove,
        update: update,
        get: get,
        displayTime: displayTime
    };
})();