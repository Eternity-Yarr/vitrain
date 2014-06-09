function partialReload(){  $("#content").load("/ #content > *", {}, function(){$("#answer").focus();}); }
$().ready
(
    function()
    {
        $("#namer").submit
        (
            function(ev)
            {
                var name = $("#name").val();
                $.ajax
                (
                    {
                        url: "/ajax?action=setName&name="+name,
                        type: "GET",
                        cache: false,
                        dataType: "json"
                    }
                )
                .success(partialReload)
                .error(function(e){console.log(e)});

                ev.preventDefault();
            }

        );

        $("html").on
        (   "click",
            "#startGame",
            function()
            {
            $.ajax
            (
                {
                    url: "/ajax?action=startGame",
                    type: "GET",
                    cache: false,
                    dataType: "json"
                }
            )
            .success(partialReload)
            .error(function(e){console.log(e)});
            }
        );

        $("html").on
        (   "click",
            "#endGame",
            function()
            {
            $.ajax
            (
                {
                    url: "/ajax?action=endGame",
                    type: "GET",
                    cache: false,
                    dataType: "json"
                }
            )
            .success(partialReload)
            .error(function(e){console.log(e)});
            }
        );
        $("html").on
        (   "submit",
            "#quizer",
            function(ev)
            {
            $.ajax
            (
                {
                    url: "/ajax?action=checkAnswer&answer="+encodeURIComponent($("#answer").val()),
                    type: "GET",
                    cache: false,
                    dataType: "json"
                }
            )
            .success(partialReload)
            .error(function(e){console.log(e)});
            ev.preventDefault();
            }
        );

    }
);
