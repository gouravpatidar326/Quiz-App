function startQuiz(quizType){
    alert(`Starting ${quizType.charAt(0).toUpperCase() + quizType.slice(1)} Quiz!`);

    window.location.href= `quiz.html?topic=${quizType}`;
}

document.addEventListener('DOMContentLoaded', function() {
    const images = document.querySelectorAll('.clickable-image');

    images.forEach(image => {
        image.addEventListener('click', function () {
            const pdfName = image.getAttribute('data-pdf');
            console.log("PDF Name:", pdfName);

            if (!pdfName) {
                alert("PDF name not found!");
                return;
            }

            const pdfUrl = `http://localhost:8082/api/notes/displayNotes/${pdfName}`;
            console.log("Opening URL:", pdfUrl);

            window.open(pdfUrl, '_blank');
        });
    });
});
