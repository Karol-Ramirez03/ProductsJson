
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
    form.reset()
})

class TablaProd extends HTMLElement {
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
                    <div class="p-3 mb-2 text-center">
                        <button type="button" class="volver btn btn-dark"style="height: 1%; padding: 0.5%;">INICIO</button>
                    </div>
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
                <td>
                <button type="button" class="eliminarButton btn btn-danger"style="height: 2%; padding: 1%; width: 100%;">X</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    }
    eliminar(){
        this.shadowRoot.querySelectorAll(".eliminarButton").forEach(boton =>{
            boton.addEventListener("click",(e)=>{

                if (e.target.classList.contains("eliminarButton")) {

                    let tr = e.target.parentNode.parentNode

                    const codigo = {
                        codigo: tr.id
                    }

                    tr.remove()

                    fetch("http://localHost:8080/eliminar", {
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(codigo)
                    }).then(res => res.json)
                    .catch(error => window.alert(error))
                }

            })

        })
        this.shadowRoot.querySelector(".volver").addEventListener("click", (e) =>{
            location.reload()

        } )
    }
    
}
customElements.define('datos-prod', TablaProd)

botonVer.addEventListener("click", (e)=> {
    document.querySelector('.formulario').innerHTML = ``
  
    const card = document.createElement('datos-prod')
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




