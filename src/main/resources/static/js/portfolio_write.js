$().ready(function () {
  // 파일 첨부 버튼 클릭.
  $("#add_attr_file").on("click", function () {
    var fileListSize = $(".fileList").length;
    var newFile = $(
      `<input class="fileList" type="file" name="attList[${fileListSize}]" />`
    );
    $(".image-upload").last().append(newFile);
  });
});
