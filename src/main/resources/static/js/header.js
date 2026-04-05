function pagination(callbackFunction) {
  $(".pagenation-ajax")
    .find("a")
    .on("click", function () {
      var cmpnyId = window.location.pathname.split("/").pop();
      var orderBy = $("#orderBy").val();
      var url = $(this).data("url") + "/" + cmpnyId;
      var pageNo = $(this).data("page-no");
      var listSize = $(this).data("exposure-list-size");

      console.log("Page URL:", url);
      console.log("Page Number:", pageNo);
      console.log("List Size:", listSize);

      callList(url, { orderBy }, pageNo, listSize, callbackFunction);
    });
}

function callList(url, param, pageNo, exposureListSize, callbackFunction) {
  var paramObject = {};

  if (location.search) {
    var params = location.search.replace("?", "");
    var paramArray = params.split("&");
    for (var p of paramArray) {
      var keyValue = p.split("=");
      paramObject[keyValue[0]] = decodeURI(keyValue[1]);
    }
  }

  paramObject.currPageNo = pageNo;
  paramObject.exposureListSize = exposureListSize;
  paramObject = { ...paramObject, ...param };

  console.log("Request Parameters:", paramObject);

  var option = {
    url,
    dataType: "json",
    data: paramObject,
    success: function (response) {
      callbackFunction(response);
    },
    error: function (status, error) {
      console.error("AJAX Error: ", status, error);
    },
  };

  $.get(option);
}

$().ready(function () {
  $("#main-logo").on("click", function () {
    location.href = "/";
  });

  $(document).on("click", "#pjttl", function () {
    var pjid = $(this).data("id");
    console.log(pjid);
    location.href = "/project/info/" + pjid;
  });

  $("#sessionA").on("click", function (e) {
    location.href = "/";
  });

  // 이게 아이콘 클릭 이벤트임.
  var membertype = $(".notification-mypage-list").data("membertype");
  var rel = null;
  console.log($("#sessionA").data("email"));
  console.log(membertype);
  if (membertype == 0) {
    // 0이면 기업회원이다.
    rel = "company/" + $("#sessionA").data("cmpid");
  } else if (membertype == 1) {
    // 1이면 프리랜서다.
    rel = "freelancer/" + $("#sessionA").data("email") + "/";
  }
  var id = $(".notification-mypage-list").data("id");

  $(".my-profile").on("click", function () {
    console.log("/member/mypage/" + rel);
    location.href = "/member/mypage/" + rel;
  });

  $(".my-info").on("click", function () {
    location.href = "/member/mypage/myinfo-edit";
  });

  $(".my-project").on("click", function () {
    location.href = "/project/myproject";
  });

  // $(".log-out").on("click", function () {});

  $(".log-out").click(function () {
    var userConfirmed = confirm("로그아웃 하시겠습니까?");

    if (userConfirmed) {
      location.href = "/member/logout";
      alert("로그아웃되었습니다.");
    } else {
      alert("로그아웃이 취소되었습니다.");
    }
  });
});
