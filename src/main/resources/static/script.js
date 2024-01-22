//Peticion fetch
const url = 'Api/Contacto';

const options = {
    method: 'GET',
    headers: getheaders()
};

function getheaders() {
    return {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
    }   
}

async function cargarContactos() {
    const request = await fetch(url, options);

    const Contactos = await request.json();

    mostrarContactos(Contactos);
   
}

function mostrarContactos(Contactos) {
    var ContenedorContacto = document.getElementById("Contactos");
    ContenedorContacto.innerHTML = "";

    Contactos.array.forEach(function (Contacto) {
        ContenedorContacto.innerHTML +=
        '<div class="Contacto">'+
            '<div>'+
                '<h2>' +Contacto.nombre +'</h2>'+
                '<p>Telefono ='+ Contacto.telefono +'</p>'+
            '</div>'+
        '</div >'
        ;
    });
}

async function guardarContacto() {
    let Contacto = {};

    Contacto.nombre = document.getElementById('txtNombre').value;
    Contacto.apellido = document.getElementById('txtApellido').value;
    Contacto.telefono = parseInt(document.getElementById('Number').value);
    Contacto.correo = document.getElementById('txtCorreo').value;
    Contacto.direccion = document.getElementById('txtDireccion').value;

    const request = await fetch(url, {
        method: 'POST',
        headers: getheaders(),
        body: JSON.stringify(Contacto)
    });

    if (request.status == 200) {
        alert("El contacto fue guardado");
    } else {
        alert("Error al guardar");
    }
}