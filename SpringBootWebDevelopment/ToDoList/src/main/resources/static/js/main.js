$(function(){

        const appendDoList = function(data){
            var doListCode = '<a href="#" class="doList-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#doList')
            .append('<div>' + doListCode + '</div>');
        };

        $('#show-add-doList-form').click(function(){
            $('#doList-form').css('display', 'flex');
        });

        $('#doList-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
            }
        });

        $(document).on('click', '.doList-link', function(){
        var link = $(this);
        var doListId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/toDoList/' + doListId,
            success: function(response)
            {
                var code = '<span> Информация: ' + response.about + '<span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Дело не найдено')
                }
            }
        });
        return false;
    });

    $('#save-doList').click(function()
    {
    var data = $('#doList-form form').serialize();
            $.ajax({
                method: "POST",
                url: '/toDoList/',
                data: data,
                success: function(response)
                {
                    $('#doList-form').css('display', 'none');
                    var doList = {};
                    doList.id = response;
                    var dataArray = $('#doList-form form').serializeArray();
                    for(i in dataArray) {
                        doList[dataArray[i]['name']] = dataArray[i]['value'];
                    }
                    appendBook(book);
                }
            });
            return false;
        });

    });