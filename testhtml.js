const validate = require('html5-validator')

const files = ['index', 'contacto', 'inscripcion', 'localizacion', 'programa'];

for(const f of files)
{
    validate('src/' + f + '.html').then(result => {
        console.log(result);
    });
}