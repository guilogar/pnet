const { Database } = require('./routes/database-service');
const databaseServiceNotifications = new Database();
const databaseServiceSpeakers = new Database();
const databaseServiceTimetable = new Database();

// Insert notifications
async function insertNotifications()
{
    console.log('Insert notifications....');
    await databaseServiceNotifications.connectDb(
        function(err)
        {
            if (err)
            {
                console.log('Could not connect with MongoDB – databaseService', err);
                process.exit(1);
            }
        }, 'notifications'
    );
    
    await databaseServiceNotifications.removeAll();
    await databaseServiceNotifications.add(
        {
            text: '¡Aplazado el evento hasta el 30 de Septiembre; la programación se revisará!',
            createdAt: new Date(),
        }
    );
    await databaseServiceNotifications.add(
        {
            text: '¡Nuevo ponente: Alan Touring!',
            createdAt: new Date(),
        }
    );
    await databaseServiceNotifications.add(
        {
            text: '¡Nuevo ponente: Edsger Dijkstra!',
            createdAt: new Date(),
        }
    );
    
    console.log(await databaseServiceNotifications.getAll());

    await databaseServiceNotifications.close();
}

// Insert timetable
async function insertTimetable()
{
    console.log('Insert timetable....');
    await databaseServiceTimetable.connectDb(
        function(err)
        {
            if (err)
            {
                console.log('Could not connect with MongoDB – databaseService', err);
                process.exit(1);
            }
        }, 'timetable'
    );

    const yesterday = new Date(new Date().setDate(new Date().getDate() - 1));
    const today = new Date();
    const tomorrow = new Date(new Date().setDate(new Date().getDate() + 1));
    
    await databaseServiceTimetable.removeAll();
    await databaseServiceTimetable.add(
        {
            day: 29,
            title: 'Programación - Viernes 29',
            lozalization: {
                x: 0.0,
                y: 0.0,
                verbose: 'Calle La Floritura, S/n, código postal: 11540'
            },
            dateOfMake: yesterday,
            table: [
                {
                    hour: '9.30 - 10.00',
                    activity: 'Bienvenida y acreditaciones.'
                },
                {
                    hour: '10.00-11.00',
                    activity: 'Desayuno y networking.'
                },
                {
                    hour: '11.00-2.00',
                    activity: 'Conferencia - El mundo de la concurrencia (Edsger Dijkstra).'
                },
                {
                    hour: '2.00-4.00',
                    activity: 'Descanso y almuerzo.'
                },
                {
                    hour: '4.00-6.00',
                    activity: '¿Qué hacer si me pierdo? Cómo la tecnología te ayuda (Edsger Dijkstra).'
                },
                {
                    hour: '6.00-7.00',
                    activity: 'Descanso, merienda y networking.'
                },
                {
                    hour: '7.00-8.00',
                    activity: 'Acto de finalización del día.'
                },
            ],
            createdAt: new Date(),
        }
    );
    await databaseServiceTimetable.add(
        {
            day: 30,
            title: 'Programación - Sábado 30',
            lozalization: {
                x: 0.0,
                y: 0.0,
                verbose: 'Calle La Floritura, S/n, código postal: 11540'
            },
            dateOfMake: today,
            table: [
                {
                    hour: '9.30 - 10.00',
                    activity: 'Bienvenida y acreditaciones.'
                },
                {
                    hour: '10.00-11.00',
                    activity: 'Desayuno y networking.'
                },
                {
                    hour: '11.00-2.00',
                    activity: 'Taller de criptoanálisis (Alan Turing).'
                },
                {
                    hour: '2.00-4.00',
                    activity: 'Descanso y almuerzo.'
                },
                {
                    hour: '4.00-6.00',
                    activity: 'Taller - ¿Qué soy: un ordenador o una persona? (Alan Turing).'
                },
                {
                    hour: '6.00-7.00',
                    activity: 'Descanso, merienda y networking.'
                },
                {
                    hour: '7.00-8.00',
                    activity: 'Acto de finalización del día.'
                },
            ],
            createdAt: new Date(),
        }
    );
    await databaseServiceTimetable.add(
        {
            day: 1,
            title: 'Programación - Domingo 1',
            lozalization: {
                x: 0.0,
                y: 0.0,
                verbose: 'Calle La Floritura, S/n, código postal: 11540'
            },
            dateOfMake: tomorrow,
            table: [
                {
                    hour: '9.30 - 10.00',
                    activity: 'Bienvenida y acreditaciones.'
                },
                {
                    hour: '10.00-11.00',
                    activity: 'Desayuno y networking.'
                },
                {
                    hour: '11.00-1.00',
                    activity: 'Conferencia - Éxito empresaria (Bill Gates).'
                },
                {
                    hour: '1.00-2.00',
                    activity: 'Conferencia - Gestión de equipos de desarrollo (Bill Gates).'
                },
                {
                    hour: '2.00-4.00',
                    activity: 'Descanso y almuerzo.'
                },
                {
                    hour: '4.00-5.00',
                    activity: 'Conferencia - Las redes y la web (Tim Berners-Lee).'
                },
                {
                    hour: '5.00-6.00',
                    activity: 'Conferencia - Industria tecnológica (Steve Jobs).'
                },
                {
                    hour: '6.00-7.00',
                    activity: 'Descanso, merienda y networking.'
                },
                {
                    hour: '7.00-8.00',
                    activity: 'Acto de finalización del día.'
                },
            ],
            createdAt: new Date(),
        }
    );
    
    console.log(await databaseServiceTimetable.getAll());
    await databaseServiceTimetable.close();
}

// Insert speakers
async function insertSpeakers()
{
    console.log('Insert speakers....');
    await databaseServiceSpeakers.connectDb(
        function(err)
        {
            if (err)
            {
                console.log('Could not connect with MongoDB – databaseService', err);
                process.exit(1);
            }
        }, 'speakers'
    );
    
    await databaseServiceSpeakers.removeAll();
    await databaseServiceSpeakers.add(
        {
            name: 'Steven Paul Jobs',
            researcher: false,
            text: [
                'Steven Paul Jobs (San Francisco, California), más conocido como Steve Jobs, fue un empresario y magnate de los negocios en el sector informático y de la industria del entretenimiento estadounidense. Fue cofundador y presidente ejecutivo de Apple y máximo accionista individual de The Walt Disney Company.',
                'Durante los años 90 transformó una empresa subsidiaria adquirida a Lucasfilm en Pixar, que revolucionó la industria de animación con el lanzamiento de Toy Story. La integración de esta compañía en Disney, de la que era proveedor, convertiría a Jobs en el mayor accionista individual del gigante del entretenimiento. En el año de su muerte, su fortuna se valoraba en 8300 millones de dólares y ocupaba el puesto 110 en la lista de grandes fortunas de la revista Forbes.'
            ],
            images: [
                'https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg/250px-Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg',
                'https://www.swashvillage.org/storage/img/images/steve-jobs-biography_2.jpg',
            ],
            jobs: [
                {
                    position: 'Cofundador',
                    company: 'Apple'
                },
                {
                    position: 'Cofundador',
                    company: 'Pixar'
                },
                {
                    position: 'Fundador',
                    company: 'NeXT Comuters'
                },
            ],
            createdAt: new Date(),
        }
    );

    await databaseServiceSpeakers.add(
        {
            name: 'William Henry Gates III',
            researcher: false,
            text: [
                'William Henry Gates III (Seattle, 28 de octubre de 1955), conocido como Bill Gates, es un multimillonario magnate empresarial, informático y filántropo​ estadounidense, cofundador de la empresa de software Microsoft junto con Paul Allen. Su fortuna se calcula en 96.5 mil millones de dólares (2019) según la revista Forbes, hecho que le coloca como el segundo hombre más rico del mundo después de Jeff Bezos. Antes del estallido de la burbuja de las punto com, su patrimonio neto ascendió a 100 000 millones de dólares, lo que lo convirtió en la décima persona más rica en toda la historia de la humanidad.',
                'Es uno de los empresarios más conocidos que surgieron durante los inicios de los ordenadores personales. Ha sido criticado por sus tácticas de negocios, que han sido consideradas anticompetitivas, una opinión que en algunos casos ha sido mantenida por numerosas sentencias judiciales.'
            ],
            images: [
                'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Bill_Gates_June_2015.png/800px-Bill_Gates_June_2015.png',
            ],
            jobs: [
                {
                    position: 'Cofundador',
                    company: 'Microsoft Corporation'
                },
                {
                    position: 'Copresidente',
                    company: 'Fundación Bill y Melinda Gates'
                },
                {
                    position: 'Copresidente',
                    company: 'TerraPower Asesor tecnológico de Microsoft'
                },
            ],
            createdAt: new Date(),
        }
    );

    await databaseServiceSpeakers.add(
        {
            name: 'John Berners-Lee',
            researcher: false,
            text: [
                'Timothy "Tim" John Berners-Lee,(Londres, Reino Unido, 8 de junio de 1955), conocido como Tim Berners-Lee, es un científico de la computación británica, conocido por ser el padre de la World Wide Web. Estableció la primera comunicación entre un cliente y un servidor usando el protocolo HTTP en noviembre de 1989. En octubre de 1994 fundó el Consorcio de la World Wide Web (W3C) con sede en el MIT, para supervisar y estandarizar el desarrollo de las tecnologías sobre las que se fundamenta la Web y que permiten el funcionamiento de Internet.',
                'Ante la necesidad de distribuir e intercambiar información acerca de sus investigaciones de una manera más efectiva, Berners-Lee desarrolló las ideas fundamentales que estructuran la web. Él y su grupo crearon lo que por sus siglas en inglés se denomina Lenguaje HTML (HyperText Markup Language) o lenguaje de etiquetas de hipertexto, el protocolo HTTP (HyperText Transfer Protocol) y el sistema de localización de objetos en la web URL (Uniform Resource Locator).'
            ],
            images: [
                'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Tim_Berners-Lee_CP_2_head_crop.jpg/220px-Tim_Berners-Lee_CP_2_head_crop.jpg',
            ],
            jobs: [
                {
                    position: 'Informático teórico, físico, programador, profesor universitario, desarrollador web, ingeniero e inventor',
                    company: 'Varias asociaciones'
                },
                {
                    position: 'Miembro',
                    company: 'World Wide Web Consortium, School of Electronics and Computer Science, University of Southampton, Instituto Tecnológico de Massachusetts, Open Data Institute, Plessey (1976-1978), Organización Europea para la Investigación Nuclear (1980), Organización Europea para la Investigación Nuclear'
                },
                {
                    position: 'Copresidente',
                    company: 'TerraPower Asesor tecnológico de Microsoft'
                },
            ],
            createdAt: new Date(),
        }
    );

    await databaseServiceSpeakers.add(
        {
            name: 'Edsger Dijkstra',
            researcher: true,
            text: [
                'Fue un científico de la computación de los Países Bajos. Trabajó en varios proyectos y desarrolló teorías como el algoritmo de Dijkstra, actualmente utilizado en Google Maps o bien los semáforos de Dijkstra, muy importantes en su momento ya que fueron pioneras primitivas de bajo nivel que controlaban la concurrencia, permitiendo a los ordenadores, ejecutar aplicaciones paralelas.',
                'Recibió la distinción ACM PODC Influential Paper Award en computación distribuida por su trabajo en la auto-estabilización en programas computacionales, posteriormente renombrado Premio Dijsktra.'
            ],
            images: [
                'https://66.media.tumblr.com/tumblr_l4nhw73hGD1qz8lbio1_400.jpg',
            ],
            jobs: [
                {
                    invent: 'Semáforo Dijkstra',
                    application: 'Primitiva de concurrencia'
                },
                {
                    invent: 'Algoritmo de Dijkstra',
                    application: 'Google Maps'
                },
                {
                    invent: 'Aportación a la verificación normal',
                    application: 'Mayor conocimiento científico y mucho más'
                },
            ],
            createdAt: new Date(),
        }
    );

    await databaseServiceSpeakers.add(
        {
            name: 'Alan Mathison Turing',
            researcher: true,
            text: [
                'Es considerado uno de los padres de la ciencia de la computación y precursor de la informática moderna. Proporcionó una influyente formalización de los conceptos de algoritmo y computación: la máquina de Turing. Formuló su propia versión que hoy es ampliamente aceptada como la tesis de Church-Turing (1936).',
                'Durante la segunda guerra mundial, trabajó en descifrar los códigos nazis, particularmente los de la máquina Enigma, y durante un tiempo fue el director de la sección Naval Enigma de Bletchley Park. Se ha estimado que su trabajo acortó la duración de esa guerra entre dos y cuatro años.6​ Tras la guerra, diseñó uno de los primeros computadores electrónicos programables digitales en el Laboratorio Nacional de Física del Reino Unido y poco tiempo después construyó otra de las primeras máquinas en la Universidad de Mánchester.'
            ],
            images: [
                'https://4.bp.blogspot.com/-bpaHUYQe0oc/VNc3FjadmEI/AAAAAAAA5Es/tLbR0dxvwUs/s1600/alan-turing1212.jpg',
            ],
            jobs: [
                {
                    invent: 'Uno de los primeros ordenadores en el mundo',
                    application: 'Precursores de los computadores actuales'
                },
                {
                    invent: 'Criptoanálisis',
                    application: 'Precursor de la criptología en los ordenadores actuales'
                },
                {
                    invent: 'Test de Turing',
                    application: 'Barómetro de la inteligencia de una máquina'
                },
            ],
            createdAt: new Date(),
        }
    );
    
    console.log(await databaseServiceSpeakers.getAll());
    await databaseServiceSpeakers.close();
}

// insert all
(async () => {
    await insertNotifications();
    await insertSpeakers();
    await insertTimetable();
}) ();