
// IMPORTANT: in all code, i use the method append of jquery for add the elements. For more information, see https://api.jquery.com/append/

function putTimeTable()
{
    const timetableUrl = "http://localhost:3000/api/v1/timetable";
    $.ajax({
        type: "GET",
        dataType: "json",
        url: timetableUrl,
        success: function(data)
        {
            // iterate tables...
            for(const timetable of data)
            {
                // create the table....
                let table = $('<table></table>');
                // insert rows in table....
                for(const t of timetable.table)
                {
                    let tr = $('<tr></tr>');
                    let td_hour = $('<td>' + t.hour + '</td>');
                    let td_activity = $('<td>' + t.activity + '</td>');
                    
                    tr.append(td_hour);
                    tr.append(td_activity);
                    table.append(tr);
                }
                // insert title before table
                let h1 = $('<h1 class="text-center">' + timetable.title + '</h1>');
                $('section.timetable').append(h1);
                $('section.timetable').append(table);
            }
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}

function putSpeakers()
{
    const speakersUrl = "http://localhost:3000/api/v1/speakers";
    $.ajax({
        type: "GET",
        dataType: "json",
        url: speakersUrl,
        success: function(data)
        {
            console.log(data);
            let h1 = $('<h1 class="text-center">PONENTES</h1>');
            $('section.speakers').append(h1);

            for(const speaker of data)
            {
                let article = $('<article></article>');
                let h2 = $('<h2 class="text-center">' + speaker.name + '</h2>');
                article.append(h2);

                // insert text...
                for(const text of speaker.text)
                {
                    let div = $('<div></div>');
                    let p = $('<p></p>');
                    p.text(text);

                    div.append(p);
                    article.append(div);
                }

                // insert images...
                for(const image of speaker.images)
                {
                    let div = $('<div></div>');
                    let img = $('<img></img>');
                    img.attr('src', image);

                    div.append(img);
                    article.append(div);
                }

                // insert table with bio...
                let table = $('<table></table>');
                let tr = $('<tr></tr>');

                let th_position = undefined;
                let th_company = undefined;

                if(speaker.researcher !== undefined && speaker.researcher === true)
                {
                    th_position = $('<th>Invención</th>');
                    th_company = $('<th>Aplicación</th>');
                } else
                {
                    th_position = $('<th>Ocupación</th>');
                    th_company = $('<th>Empresa</th>');
                }
                
                tr.append(th_position);
                tr.append(th_company);
                table.append(tr);

                // insert rows of table...
                for(const job of speaker.jobs)
                {
                    let tr = $('<tr></tr>');
                    let td_position = undefined;
                    let td_company = undefined;

                    if(speaker.researcher !== undefined && speaker.researcher === true)
                    {
                        td_position = $('<td>' + job.invent + '</td>');
                        td_company = $('<td>' + job.application + '</td>');
                    } else
                    {
                        td_position = $('<td>' + job.position + '</td>');
                        td_company = $('<td>' + job.company + '</td>');
                    }
                    
                    tr.append(td_position);
                    tr.append(td_company);
                    table.append(tr);
                }
                article.append(table);
                
                $('section.speakers').append(article);
            }
        },
        error: function(err)
        {
            console.log(err);
        }
    });
}