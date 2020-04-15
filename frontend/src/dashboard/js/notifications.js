function insertNotification(event)
{
    event.preventDefault();
    const form = $('form#addNotificationForm');
    const notificationText = form.find('#addNotificationText').val();
    
    let notification = {
        text: notificationText,
        createdAt: new Date()
    };

    $.ajax({
        type: "POST",
        dataType: "json",
        url: "http://localhost:3000/api/v1/notifications",
        data: notification,
        success: function(data)
        {
            $('#addNotificationModal').modal('hide');
            addSuccessAlert('Notificación añadida correctamente.', '#addNotification');
            loadNotifications();
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}


function deleteNotification()
{
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: "http://localhost:3000/api/v1/notifications/" + $('#idNotification').val(),
        success: function(data)
        {
            $('#deleteNotificationModal').modal('hide');
            addSuccessAlert('Notificación eliminada correctamente.', '#addNotification');
            loadNotifications();
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function deleteNotificationModal()
{
    const id = $(this).attr('id');
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:3000/api/v1/notifications/" + id,
        success: function(data)
        {
            data = data[0];
            $('#deleteNotificationModalLabel').text('¿Esta seguro de eliminar esta notificación?');
            $('#idNotification').val(id);
            $('#deleteNotificationModal').modal('show');
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function updateNotification(event)
{
    event.preventDefault();
    const form = $('form#updateNotificationForm');
    const notificationText = form.find('#updateNotificationText').val();

    let notification = {
        text: notificationText,
        createdAt: new Date()
    };
    
    $.ajax({
        type: "PUT",
        dataType: "json",
        url: "http://localhost:3000/api/v1/notifications/" + $('#updateIdNotification').val(),
        data: notification,
        success: function(data)
        {
            $('#updateNotificationModal').modal('hide');
            addSuccessAlert('Notificación actualizada correctamente.', '#addNotification');
            loadNotifications();
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function updateNotificationModal()
{
    const id = $(this).attr('id');
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:3000/api/v1/notifications/" + id,
        success: function(data)
        {
            data = data[0];
            $('#updateIdNotification').val(id);

            const form = $('form#updateNotificationForm');
            form.find('#updateNotificationText').val(data.text);
            
            // show the modal
            $('#updateNotificationModal').modal('show');
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function loadNotifications()
{
    const idDiv = 'notifications';
    $('#' + idDiv).empty();
    putNotifications(idDiv, function ()
    {
        $('#table_' + idDiv).DataTable();
        $('.update').click(updateNotificationModal);
        $('.delete').click(deleteNotificationModal);
    });
}