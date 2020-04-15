
// IMPORTANT: in all code, i use the method append of jquery for add the elements. For more information, see https://api.jquery.com/append/

function putNotifications()
{
    const notificationsUrl = "http://localhost:3000/api/v1/notifications";
    $.ajax({
        type: "GET",
        dataType: "json",
        url: notificationsUrl,
        success: function(data)
        {
            $('aside.news-body').empty();
            $('aside.news-body').hide();
            // iterate notifications...
            for(const notification of data)
            {
                // create the article....
                let article = $('<article class="alert alert-secondary"></article>');
                let span = $('<span class="header-news-body"></span>');
                span.text(notification.text);
                article.append(span);
                let button = $('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
                article.append(button);
                $('aside.news-body').append(article);
            }
            $('aside.news-body').slideDown('slow');
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}