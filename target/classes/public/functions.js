$(document).ready(function() {

    $.ajax({
        url: "http://localhost:8080/"
    }).then(function(data) {
        $("#returnValue").text(data);
    });

    $("#btnSubmitSize").on("click", function() {
        $returnValue = $("#returnValue");
        $.ajax({
            url: "http://localhost:8080/reset",
            type: "POST",
            data: JSON.stringify({"size": $("#inputSize").val()}),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                $returnValue.text(data);
            }
        });
    });

    $("#btnSubmitBooking").on("click", function() {
        $bookingResult = $("#bookingResult");
        $inputStart = $("#inputStart");
        $inputEnd = $("#inputEnd");
        $.ajax({
            url: "http://localhost:8080/booking",
            type: "POST",
            data: JSON.stringify({
                "startDay": $("#inputStart").val(),
                "endDay": $("#inputEnd").val()
            }),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                $inputStart.val("");
                $inputEnd.val("");
                $bookingResult.text(data);
            }
        });
    });
});