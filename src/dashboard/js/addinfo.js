function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function putDays()
{
    const timetableUrl = "http://localhost:3000/api/v1/timetable";

    $.ajax({
        type: "GET",
        dataType: "json",
        url: timetableUrl,
        success: function(data)
        {
            const daysTimetable = $('#daysTimetable');
            // iterate tables...
            for(const timetable of data)
            {
                try
                {
                    const link = '<a class="collapse-item" href="horario.html?id=' + timetable._id + '">' + timetable.title.split('-')[1].trim() + '</a>';
                    daysTimetable.append(link);
                } catch(err)
                {
                    continue;
                }
            }
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

// main
$(document).ready(function ()
{
    putDays();
});

function addTable(idDiv, idTable, nameColumns, data, callback, hasActions = false)
{
    const table = $('<table class="table table-bordered text-center" id="' + idTable + '" width="100%" cellspacing="0"></table>');

    const thead = $('<thead></thead>');
    const tfoot = $('<tfoot></tfoot>');
    const tr_head = $('<tr></tr>');
    const tr_foot = $('<tr></tr>');

    for(const column of nameColumns)
    {
        tr_head.append('<th>' + column + '</th>');
        tr_foot.append('<th>' + column + '</th>');
    }

    
    if(hasActions)
    {
        tr_head.append('<th>Actualizar</th>');
        tr_head.append('<th>Eliminar</th>');
        tr_foot.append('<th>Actualizar</th>');
        tr_foot.append('<th>Eliminar</th>');
    }

    thead.append(tr_head);
    tfoot.append(tr_foot);

    const tbody = $('<tbody></tbody>');

    for(const row of data)
    {
        const tr_body = $('<tr></tr>');
        
        for(const cell of row)
        {
            const td_body = $('<td></td>');
            td_body.text(cell);
            tr_body.append(td_body);
        }
        
        if(hasActions)
        {
            const td_update = $('<td><a href="#" class="btn btn-warning btn-circle ' + idTable + ' update" id=""><i class="fas fa-exclamation-triangle"></i></a></td>');
            const td_delete = $('<td><a href="#" class="btn btn-danger btn-circle ' + idTable + ' delete" id=""><i class="fas fa-trash"></i></a></td>');
            tr_body.append(td_update);
            tr_body.append(td_delete);
        }

        tbody.append(tr_body);
    }

    table.append(thead);
    table.append(tfoot);
    table.append(tbody);

    $('#' + idDiv).append(table);
    callback();
}

function putTimetable(idObject, idDiv, callback)
{
    const url = "http://localhost:3000/api/v1/timetable/" + idObject;

    $.ajax({
        type: "GET",
        dataType: "json",
        url: url,
        success: function(data)
        {
            data = data[0];
            let arrayData = [];
            for(const item of data['table'])
            {
                arrayData.push([
                    item.hour, item.activity
                ]);
            }
            addTable(idDiv, 'table_' + idDiv, ['Hora', 'Actividad'], arrayData, callback, false);
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function putSpeakers(idDiv, callback)
{
    const url = "http://localhost:3000/api/v1/speakers";

    $.ajax({
        type: "GET",
        dataType: "json",
        url: url,
        success: function(data)
        {
            let arrayData = [];
            for(const item of data)
            {
                let stringJob = '';
                for(const job of item.jobs)
                {
                    if(job.company)
                        stringJob += job.company + ' (' + job.position + ' ), ';
                    else
                        stringJob += job.invent + ' (' + job.application + ' ), ';
                }

                arrayData.push([
                    item.name, (item.researcher) ? 'Investigador' : 'Desarrollador', stringJob
                ]);
            }
            addTable(idDiv, 'table_' + idDiv, ['Nombre', 'Profesión', 'Trabajos'], arrayData, callback, true);
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function putNotifications(idDiv, callback)
{
    const url = "http://localhost:3000/api/v1/notifications";

    $.ajax({
        type: "GET",
        dataType: "json",
        url: url,
        success: function(data)
        {
            let arrayData = [];
            for(const item of data)
            {
                arrayData.push([
                    item.text, new Date(item.createdAt)
                ]);
            }
            addTable(idDiv, 'table_' + idDiv, ['Mensaje', 'Fecha de creación'], arrayData, callback, true);
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function putInscriptions(idDiv, callback)
{
    const url = "http://localhost:3000/api/v1/inscriptions";

    $.ajax({
        type: "GET",
        dataType: "json",
        url: url,
        success: function(data)
        {
            let arrayData = [];
            for(const item of data)
            {
                arrayData.push([
                    item.fname, item.lname, item.ename, item.adname, item.ddname
                ]);
            }
            addTable(idDiv, 'table_' + idDiv, ['Nombre', 'Apellidos', 'Email', 'Fecha de entrada', 'Fecha de salida'], arrayData, callback, true);
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}