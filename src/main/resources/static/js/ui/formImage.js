import $ from "../$.js";

(() => {
    $("#imagen").addEventListener("change", e => {
        const imagen = e.currentTarget.files[0];

        if (Boolean(imagen)) {
            mostrarImagen(imagen);
        }
    })

    function mostrarImagen(imagen) {
        const src = URL.createObjectURL(imagen);
        const imagenNodo = $(".form__imagen-subida");
        imagenNodo.src = src;
    }

})()