// Debido a que muchas funciones de JavaScript y DOM están en inglés, se optó por programas el código
// en inglés (al menos en su mayoría) para mantener el estándar.
// Debido a que el backend es más manipulable en este sentido del lenguaje, hay algunas cosas que se mantienen en español

document.addEventListener('DOMContentLoaded', function() {
    let order = [];
    let isAuthenticated = false;

    // Verificar si el usuario está autenticado
    fetch('/api/auth/status')
        .then(response => response.json())
        .then(data => {
            const authButton = document.getElementById('auth-button');
            isAuthenticated = data.authenticated;
            if (isAuthenticated) {
                authButton.textContent = 'Cerrar Sesión';
                authButton.classList.add('logout');
                authButton.addEventListener('click', function() {
                    fetch('/api/auth/logout', { method: 'POST' })
                        .then(() => {
                            window.location.reload();
                        });
                });
            } else {
                authButton.textContent = 'Iniciar Sesión';
                authButton.classList.remove('logout');
                authButton.addEventListener('click', function() {
                    document.getElementById('login-popup').style.display = 'block';
                });
            }
        });

    // Manejar el formulario de inicio de sesión
    document.getElementById('login-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        fetch('/api/auth/login', {
            method: 'POST',
            body: JSON.stringify({
                usuario: formData.get('username'),
                contrasena: formData.get('password')
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Credenciales incorrectas');
                }
            })
            .then(data => {
                isAuthenticated = true;
                window.location.reload();
            })
            .catch(error => {
                alert(error.message);
            });
    });

    // Obtener y renderizar productos
    fetch('/api/productos')
        .then(response => response.json())
        .then(data => {
            const productList = document.getElementById('product-list');
            data.forEach(product => {
                const productItem = document.createElement('li');
                productItem.classList.add('product-item');

                // Se crea un elemento img para mostrar la imagen del producto
                const productImage = document.createElement('img');
                productImage.src = product.direccion;
                productImage.alt = product.nombre;
                productImage.classList.add('product-image');

                // Nombre del producto
                const productName = document.createElement('h2');
                productName.textContent = product.nombre;
                productName.classList.add('product-name');

                // Origen del producto (se muestra la bandera del pais)
                const productOrigin = document.createElement('p');
                productOrigin.classList.add('product-origin');
                productOrigin.innerHTML = `Origen: ${product.origin} <img src="${product.imagenUrl}" alt="${product.origin}" class="country-flag">`;

                // Cantidad disponible del producto
                const productQuantity = document.createElement('p');
                productQuantity.textContent = `Cantidad disponible: ${product.cantidadDisponible}`;
                productQuantity.classList.add('product-quantity');

                // Precio del producto
                const productPrice = document.createElement('p');
                productPrice.textContent = `Precio: ₡${product.precio}`;
                productPrice.classList.add('product-price');

                // Espacio de empaque del producto para que el usuario pueda ir calculando el espacio total
                const productSpace = document.createElement('p');
                productSpace.textContent = `Espacio de empaque: ${product.espacioEmbalaje}`;
                productSpace.classList.add('product-space');

                // Input para la cantidad de productos a agregar
                const quantityInput = document.createElement('input');
                quantityInput.type = 'number';
                quantityInput.min = '1';
                quantityInput.max = product.cantidadDisponible;
                quantityInput.value = '1';
                quantityInput.classList.add('quantity-input');

                // Botón para agregar el producto al carrito
                const addButton = document.createElement('button');
                addButton.textContent = 'Agregar';
                addButton.classList.add('add-button');
                addButton.addEventListener('click', () => {
                    if (isAuthenticated) {
                        const quantity = parseInt(quantityInput.value);
                        if (quantity > 0 && quantity <= product.cantidadDisponible) {
                            addToOrder(product, quantity);
                        } else {
                            alert('Cantidad no válida');
                        }
                    } else {
                        document.getElementById('login-popup').style.display = 'block';
                    }
                });

                // Se añaden los elementos al contenedor del producto
                productItem.appendChild(productImage);
                productItem.appendChild(productName);
                productItem.appendChild(productOrigin);
                productItem.appendChild(productQuantity);
                productItem.appendChild(productPrice);
                productItem.appendChild(productSpace);
                productItem.appendChild(quantityInput);
                productItem.appendChild(addButton);

                productList.appendChild(productItem);
            });
        })
        .catch(error => console.error('Error fetching products:', error));

    // Se agrega el producto a la orden y se renderiza la tabla de la orden para mostrar el pedido actualizado
    function addToOrder(product, quantity) {
        const existingProduct = order.find(item => item.product.id === product.id);
        if (existingProduct) {
            existingProduct.quantity += quantity;
        } else {
            order.push({ product, quantity, packaging: 'Caja' });
        }
        renderOrderTable();
    }

    // Aqui se quitan los productos de la orden y se actualiza la tabla de la orden
    function removeFromOrder(productId) {
        order = order.filter(item => item.product.id !== productId);
        renderOrderTable();
    }

    // Se renderiza la tabla de la orden con los productos actuales
    function renderOrderTable() {
        const orderTable = document.getElementById('order-table');
        orderTable.innerHTML = `
        <tr>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Tipo de Embalaje</th>
            <th>Espacio de Embalaje</th>
            <th>Precio</th>
            <th>Acciones</th>
        </tr>
    `;
        // Esto es para mostrar los productos en la tabla de la orden
        order.forEach(item => {
            const row = document.createElement('tr');
            row.innerHTML = `
            <td>${item.product.nombre}</td>
            <td>${item.quantity}</td>
            <td>${item.packaging}</td>
            <td>${item.product.espacioEmbalaje}</td>
            <td>₡${item.product.precio * item.quantity}</td>
            <td><button class="remove-button" data-product-id="${item.product.id}">Eliminar</button></td>
        `;
            orderTable.appendChild(row);
        });

        document.querySelectorAll('.remove-button').forEach(button => {
            button.addEventListener('click', () => {
                const productId = parseInt(button.getAttribute('data-product-id'));
                removeFromOrder(productId);
            });
        });

        // Eliminar el botón "Realizar Compra" existente si ya está presente. Esto es para evitar botones duplicados al manipular la tabla de la orden
        const existingPurchaseButton = document.querySelector('.purchase-button');
        if (existingPurchaseButton) {
            existingPurchaseButton.remove();
        }

        // Añadir botón "Realizar Compra"
        const purchaseButton = document.createElement('button');
        purchaseButton.textContent = 'Realizar Compra';
        purchaseButton.classList.add('purchase-button');
        purchaseButton.addEventListener('click', () => {
            if (order.length > 0) {
                // Llamamos a validatePacking antes de realizar la compra
                validatePacking(order)
                    .then(data => {
                        if (data.packedSuccessfully) {
                            // Si el empaque es exitoso, realizamos la compra
                            fetch('/api/orden', {
                                method: 'POST',
                                body: JSON.stringify(order),
                                headers: {
                                    'Content-Type': 'application/json'
                                }
                            })
                                .then(response => response.json())
                                .then(data => {
                                    alert('Compra realizada con éxito y productos embalados correctamente.');
                                    order = []; // Limpiamos el carrito después de la compra
                                    renderOrderTable();
                                })
                                .catch(error => console.error('Error realizando la compra:', error));
                        } else {
                            // Si no es posible embalar sin espacios libres
                            alert('No se pudo embalar todos los productos sin dejar espacios libres. Por favor, ajuste las cantidades.' +
                                '\n Tenga en cuenta que los espacios de embalaje en las cajas son de: ' +
                                '\n 2 - 3 - 5 - 7 - 12');
                        }
                    });
            } else {
                alert('No hay productos en la orden.');
            }
        });
        orderTable.parentElement.appendChild(purchaseButton);
    }

    // Validar el empaque de los productos antes de realizar la compra. Llama a la api del backend para realizar la operacion
    function validatePacking(items) {
        const formattedItems = items.map(item => ({
            producto: {
                id: item.product.id,
                espacioEmbalaje: item.product.espacioEmbalaje
            },
            cantidad: item.quantity
        }));

        return fetch('/api/orden/validate-packing', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formattedItems)
        })
            .then(response => response.json())
            .then(data => data) // Retornamos el resultado del empaque
            .catch(error => console.error("Error al validar el empaque:", error));
    }


});