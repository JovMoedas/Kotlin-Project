function scrollUp() {
    const scrollUp = document.getElementById('scroll-up');

    // Mostrar o botão ao rolar 200px para baixo
    if (window.scrollY >= 200) {
        scrollUp.classList.add('show-scroll');
    } else {
        scrollUp.classList.remove('show-scroll');
    }
}

// Adiciona o evento de rolagem
window.addEventListener('scroll', scrollUp);

// Função de rolagem suave ao clicar
document.getElementById('scroll-up').addEventListener('click', () => {
    window.scrollTo({
        top: 0,
        behavior: 'smooth' // Movimento suave
    });
});


