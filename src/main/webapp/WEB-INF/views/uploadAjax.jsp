<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Upload With Ajax</h1>

    <div class="uploadDiv">
        <input type="file" name="uploadFile" multiple>
    </div>

    <div class="uploadResult">
        <ul></ul>
    </div>

    <div class="bigPictureWrapper">
        <div class="bigPicture"></div>
    </div>

    <button id="uploadBtn">Upload</button>

</body>
</html>

<script
        src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"></script>

<script>

    /**
     * 원본 이미지 보여주는 함수
     * @param fileCallPath
     */
    function showImage(fileCallPath) {
        // alert(fileCallPath);
        $(".bigPictureWrapper").css("display", "flex").show();

        $(".bigPicture").html("<img src='/display?fileName=" + encodeURI(fileCallPath) + "'>")
            .animate({width: '100%', height: '100%'}, 1000);

    }

    $(document).ready(function () {

        var regex = new RegExp("(.*?)\(exe|sh|zip|alz)$");
        var maxSize = 5242880;  // 5MB

        function checkExtention (fileName, fileSize) {
            if (fileSize >= maxSize) {
                alert("파일 사이즈 초과");
                return false;
            }

            if (regex.test(fileName)) {
                alert("해당 종류의 파일은 업로드할 수 없습니다.");
                return false;
            }

            return true;
        }

        var uploadResult = $(".uploadResult ul");
        function showUploadedFile(uploadResultArr) {
            var str = "";

            $(uploadResultArr).each(function (i, obj) {
                if (!obj.image) {
                    var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
                    str += "<li><a href='/download?fileName=" + fileCallPath + "'>"
                        + "<img src='/resources/img/attach.png'>" + obj.fileName
                        + "</a></li>";
                } else {
                    var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
                    var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
                    originPath = originPath.replace(new RegExp(/\\/g), "/");

                    str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\"><img src='/display?fileName=" + fileCallPath + "'></a></li>";
                }
            });

            uploadResult.append(str);
        }


        $("#uploadBtn").on("click", function (e) {
            var formData = new FormData();
            var inputFile = $("input[name='uploadFile']");
            var files = inputFile[0].files;

            console.log(files);

            // Add File Data to formData
            for (var i = 0; i < files.length; i++) {
                if (!checkExtention(files[i].name, files[i].size)) {
                    return false;
                }

                formData.append("uploadFile", files[i]);
            }

            $.ajax({
                url: 'uploadAjaxAction',
                processData: false,
                contentType: false,
                data: formData,
                type: 'POST',
                success: function (result) {
                    console.log(result);

                    showUploadedFile(result);
                    inputFile.val('');
                }
            })
        })

        /**
         * 원본 이미지 클릭시 닫는 이벤트
         */
        $(".bigPictureWrapper").on("click", function (e) {
            $(".bigPicture").animate({width: "0%", height: "0%"}, 1000);
            setTimeout(function () {
                $(".bigPictureWrapper").hide();
            }, 1000);
        });
    });
</script>

<style>
    .uploadResult {
        width: 100%;
        background-color: gray;
    }

    .uploadResult ul {
        display: flex;
        flex-flow: row;
        justify-content: center;
        align-items: center;
    }

    .uploadResult ul li {
        list-style: none;
        padding: 10px;
        align-content: center;
        text-align: center;
        display: flex;
    }

    .uploadResult ul li img {
        width: 100px;
    }

    .uploadResult ul li span {
        color: white;
    }

    .bigPictureWrapper {
        position: absolute;
        display: none;
        justify-content: center;
        align-items: center;
        top: 0%;
        width: 100%;
        height: 100%;
        z-index: 100;
        background: rgba(255, 255, 255, 0.5);
    }

    .bigPicture {
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .bigPicture img {
        width: 600px;
    }
</style>