function insertSpeaker(event)
{
    event.preventDefault();
    const form = $('form#addSpeakerForm');
    const name = form.find('#addSpeakerName').val();
    const researcher = (form.find('#addSpeakerResearcher').val() === 'true') ? true : false;
    const text = form.find('#addSpeakerText').val();
    let jobs = [];

    const divJobs = form.find('div.job');

    for(let div of divJobs)
    {
        div = $(div);
        const jobname = div.find('#jobname').val();
        const jobcompany = div.find('#jobcompany').val();
        
        if(jobname && jobcompany)
        {
            if(researcher)
                jobs.push({
                    position: jobname,
                    company: jobcompany
                });
            else
                jobs.push({
                    invent: jobname,
                    application: jobcompany
                });
        }
    }

    let speaker = {
        name: name,
        researcher: researcher,
        text: text,
        jobs: jobs,
    };

    const url = "http://localhost:3000/api/v1/speakers";

    $.ajax({
        type: "POST",
        dataType: "json",
        url: url,
        data: speaker,
        success: function(data)
        {
            $('#addSpeakerModal').modal('toggle');

            let alertBoostrap = $('<div class="alert alert-success" role="alert">Ponente añadido con éxito.</div>');
            $('#addSpeaker').before(alertBoostrap);

            loadSpeakers();
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function deleteSpeaker()
{
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: "http://localhost:3000/api/v1/speakers/" + $('#idSpeaker').val(),
        success: function(data)
        {
            $('#deleteSpeakerModal').modal('hide');

            let alertBoostrap = $('<div class="alert alert-success" role="alert">Ponente eliminado con éxito.</div>');
            $('#addSpeaker').before(alertBoostrap);

            loadSpeakers();
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function deleteSpeakerModal()
{
    const id = $(this).attr('id');
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:3000/api/v1/speakers/" + id,
        success: function(data)
        {
            data = data[0];
            $('#deleteSpeakerModalLabel').text('¿Esta seguro de eliminar al ponente "' + data.name + '"?');
            $('#deleteSpeakerButton').click(deleteSpeaker);
            $('#idSpeaker').val(id);
            $('#deleteSpeakerModal').modal('show');
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function updateSpeaker(event)
{
    event.preventDefault();
    const form = $('form#updateSpeakerForm');
    const name = form.find('#updateSpeakerName').val();
    const researcher = (form.find('#updateSpeakerResearcher').val() === 'true') ? true : false;
    const text = form.find('#updateSpeakerText').val();
    let jobs = [];

    const divJobs = form.find('div.job');

    for(let div of divJobs)
    {
        div = $(div);
        const jobname = div.find('#jobname').val();
        const jobcompany = div.find('#jobcompany').val();
        
        if(jobname && jobcompany)
        {
            if(researcher)
                jobs.push({
                    invent: jobname,
                    application: jobcompany
                });
            else
                jobs.push({
                    position: jobname,
                    company: jobcompany
                });
        }
    }

    let speaker = {
        name: name,
        researcher: researcher,
        text: text,
        jobs: jobs,
    };
    
    $.ajax({
        type: "PUT",
        dataType: "json",
        url: "http://localhost:3000/api/v1/speakers/" + $('#updateIdSpeaker').val(),
        data: speaker,
        success: function(data)
        {
            $('#updateSpeakerModal').modal('hide');

            let alertBoostrap = $('<div class="alert alert-success" role="alert">Ponente actualizado con éxito.</div>');
            $('#addSpeaker').before(alertBoostrap);

            loadSpeakers();
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function updateSpeakerModal()
{
    const id = $(this).attr('id');
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:3000/api/v1/speakers/" + id,
        success: function(data)
        {
            data = data[0];
            $('#updateIdSpeaker').val(id);

            const form = $('form#updateSpeakerForm');
            const name = form.find('#updateSpeakerName').val(data.name);
            const researcher = form.find('#updateSpeakerResearcher').val('' + data.researcher).change();
            
            try
            {
                const text = form.find('#updateSpeakerText').val(data.text.join());
            } catch(err)
            {
                const text = form.find('#updateSpeakerText').val(data.text);
            }

            form.find('div.job').remove();

            for(const job of data.jobs)
            {
                let divJob = undefined;
                if(JSON.parse(data.researcher))
                {
                    divJob = '<div class="form-group row job">' +
                        '<div class="col-sm-6 mb-3 mb-sm-0">' +
                            '<input type="text" class="form-control form-control-user" id="jobname" value="' + job.invent + '" placeholder="Ocupación o Investigación" required>' +
                        '</div>' +
                        '<div class="col-sm-6">' +
                            '<input type="text" class="form-control form-control-user" id="jobcompany" value="' + job.application + '" placeholder="Empresa o Aplicación de la Investigación" required>' +
                        '</div>' +
                    '</div>';
                } else
                {
                    divJob = '<div class="form-group row job">' +
                        '<div class="col-sm-6 mb-3 mb-sm-0">' +
                            '<input type="text" class="form-control form-control-user" id="jobname" value="' + job.position + '" placeholder="Ocupación o Investigación" required>' +
                        '</div>' +
                        '<div class="col-sm-6">' +
                            '<input type="text" class="form-control form-control-user" id="jobcompany" value="' + job.company + '" placeholder="Empresa o Aplicación de la Investigación" required>' +
                        '</div>' +
                    '</div>';
                }
                form.find('#addJob').before(divJob);
            }
            
            // show the modal
            $('#updateSpeakerModal').modal('show');
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function actionButtons(element = undefined)
{
    const job = '<div class="form-group row job">' +
                    '<div class="col-sm-6 mb-3 mb-sm-0">' +
                        '<input type="text" class="form-control form-control-user" id="jobname" placeholder="Ocupación o Investigación" required>' +
                    '</div>' +
                    '<div class="col-sm-6">' +
                        '<input type="text" class="form-control form-control-user" id="jobcompany" placeholder="Empresa o Aplicación de la Investigación" required>' +
                    '</div>' +
                '</div>';
    if(element)
    {
        element.find('#addJob').click(function () {
            $(this).before(job);
        });
        element.find('#deleteJob').click(function () {
            if($('.job').length > 1) $('.job').last().remove();
        });
    } else
    {
        $('.addJob').click(function () {
            $(this).before(job);
        });
        $('.deleteJob').click(function () {
            if($('.job').length > 1) $('.job').last().remove();
        });
    }
}

function loadSpeakers()
{
    const idDiv = 'speakers';
    $('#' + idDiv).empty();
    putSpeakers(idDiv, function ()
    {
        $('#table_' + idDiv).DataTable();
        $('.updateSpeaker').click(updateSpeakerModal);
        $('.deleteSpeaker').click(deleteSpeakerModal);
    });
    
}