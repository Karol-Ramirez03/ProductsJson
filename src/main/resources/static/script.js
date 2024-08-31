// document.getElementById('registroForm').addEventListener('submit', (e) => {
//    // Función para enviar datos al servidor
// async function createRegistro() {
//     const registro = {
//         codigo: '123',
//         nombre: 'Producto Ejemplo',
//         precio: 29.99,
//         stock: 100
//     };

//     try {
//         const response = await fetch('/api/registro/create', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(registro)
//         });

//         if (response.ok) {
//             const data = await response.json();
//             console.log('Registro guardado:', data);
//         } else {
//             console.error('Error al guardar el registro:', response.statusText);
//         }
//     } catch (error) {
//         console.error('Error en la solicitud:', error);
//     }
// }

// // Llamar a la función cuando sea necesario
// createRegistro();


// });
const botonGuardar = document.getElementById("saveButton");

botonGuardar.addEventListener('click', (e) =>{
    
})
