
const botonGuardar = document.getElementById("guardarButton");
const botonVer = document.getElementById("verButton");
const container = document.querySelector(".container");
const form = document.querySelector("#dataForm");


botonGuardar.addEventListener("click", (e)=>{

    const formulario =new FormData(form);
    const codigo = formulario.get('codigo');
    const nombre = formulario.get('nombre');
    const precio = formulario.get('precio');
    const stock = formulario.get('stock')

    window.alert("confirmar")
    const api = "http://localhost:8080/agregar"


    const nuevoRegistro = {
        codigo: codigo,
        nombre: nombre,
        precio: precio,
        stock: stock
    };

    const enviarArchivoJson = async () => {
    
        fetch(api, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoRegistro) 
        })
       
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
    };
    
    enviarArchivoJson();
})
class CardHero extends HTMLElement {
    connectedCallback() {
        this.attachShadow({ mode: 'open' });
        this.shadowRoot.innerHTML = /* html */ `
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <div class="card" style="width: 90%; padding: 4%;">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>Stock</th>
                        </tr>
                    </thead>
                    <tbody id="table-body" >
                    </tbody>
                </table>
            </div>
        `;
    }
    productos(data) {
        const tbody = this.shadowRoot.querySelector("#table-body");
        data.forEach(item => {
            const row = document.createElement("tr");
            row.setAttribute("id", item.codigo)
            row.innerHTML = `
                <td>${item.codigo}</td>
                <td>${item.nombre}</td>
                <td>${item.precio}</td>
                <td>${item.stock}</td>
                <button type="button" class="eliminarButton btn btn-danger"style="height: 2%; padding: 1%; width: 100%;">X</button>
            `;
            tbody.appendChild(row);
        });
    }
    eliminar(){
        const tbody2 = this.shadowRoot.querySelector("#table-body");

        tbody2.addEventListener("click",(e)=>{
        window.alert('validar')
        window.alert(e.id)
})
    }
    
}
customElements.define('card-hero', CardHero)

// location.reload()
botonVer.addEventListener("click", (e)=> {
    document.querySelector('.formulario').innerHTML = ``
    
    window.alert('validar')
    const card = document.createElement('card-hero')
    container.insertAdjacentElement('beforeend', card)

    fetch("http://localhost:8080/data", {
        method: "GET",
        headers: {
            'Content-Type': 'Application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        card.productos(data); 
        card.eliminar();
    })
    .catch(error => console.error("Error", error))
})




