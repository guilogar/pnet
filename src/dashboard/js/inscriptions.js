function deleteInscription()
{
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: "http://localhost:3000/api/v1/inscriptions/" + $('#idInscription').val(),
        success: function(data)
        {
            $('#deleteInscriptionModal').modal('hide');
            addSuccessAlert('Inscripción eliminada correctamente.', '#addInscription');
            loadInscriptions();
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function deleteInscriptionModal()
{
    const id = $(this).attr('id');
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:3000/api/v1/inscriptions/" + id,
        success: function(data)
        {
            data = data[0];
            $('#deleteInscriptionModalLabel').text('¿Esta seguro de eliminar esta inscripción?');
            $('#idInscription').val(id);
            $('#deleteInscriptionModal').modal('show');
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function loadInscriptions()
{
    const idDiv = 'inscriptions';
    $('#' + idDiv).empty();
    putInscriptions(idDiv, function ()
    {
        $('#table_' + idDiv).DataTable();
        $('.delete').click(deleteInscriptionModal);
    });
}