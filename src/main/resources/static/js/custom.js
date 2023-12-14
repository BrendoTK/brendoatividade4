// custom.js

function formatCRM(input) {
    // Remove caracteres não permitidos (exceto números e letras maiúsculas)
    input.value = input.value.replace(/[^0-9A-Z-]/g, '');

    // Converte para letras maiúsculas
    input.value = input.value.toUpperCase();

    // Aplica a máscara (12345-SP)
    if (input.value.length >= 5) {
        input.value = input.value.slice(0, 5) + '-' + input.value.slice(5, 7);
    }
}