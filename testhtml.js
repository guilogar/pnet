const validate = require('html5-validator')

const files = ['index', 'contacto', 'inscripcion', 'localizacion', 'programa'];

for(const f of files)
{
    validate('src/' + f + '.html').then(result => {
        console.log(result);
        return;
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