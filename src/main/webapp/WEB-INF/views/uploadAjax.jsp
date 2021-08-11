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

    <button id="uploadBtn">Upload</button>

</body>
</html>

<script
        src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"></script>

<script>
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
                    str += "<li><img src='/resources/img/attach.png'>" + obj.fileName + "</li>";
                } else {
                    var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
                    str += "<li><img src='/display?fileName=" + fileCallPath + "'></li>";
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
        display: flex;
    }

    .uploadResult ul li img {
        width: 20px;
    }
</style>