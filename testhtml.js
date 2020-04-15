const validate = require('html5-validator')

const files = ['index', 'contacto', 'inscripcion', 'localizacion', 'programa'];

for(const f of files)
{
    validate('src/' + f + '.html').then(result => {
        for(let i = 0; i < result.messages.length; i++)
        {
            result.messages[i].file = f + '.html';
        }
        console.log(result);
    });
}
// validate('src/index.html').then(result => {
//     for(const m of result.messages)
//     {
//         if(m.type === 'error')
//         {
//             console.log(m)
//         }
//     }
// });