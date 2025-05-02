let currentQuestionIndex = 0;
let questions = [];
let correctAnswers = 0; 
let totalQuestions = 0;

function getQuizType(){
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('topic'); // Extracts the "topic" parameter (e.g., "java", "python")
}

async function fetchQuestion() {
    try {
        const quizType = getQuizType();
        const baseUrl = 'http://localhost:8082/api/getQuestions'; // Base API URL
        const url = `${baseUrl}/${quizType}`; // Build dynamic URL

        const response= await fetch(url);
        if(!response.ok){
            throw new Error("Network response was not ok");
        }
        questions=await response.json();
        totalQuestions = questions.length;
        document.getElementById('total-questions').textContent = totalQuestions;
        displayQuestion(); 
    } catch (error) {
        console.log("Error Fetching Questions",error);
        document.getElementById('question-text').textContent = "Failed to load questions. Please try again."
    }
    
}

function displayQuestion() {
    if (questions.length === 0){ 
        document.getElementById('question-text').textContent =
            "No questions to display.";
        return;
    } 

    const questionText = document.getElementById('question-text');
    const optionsContainer = document.getElementById('options-container');
    const currentQuestion = questions[currentQuestionIndex];

    questionText.textContent = currentQuestion.questionText || "No question text available";
    
    optionsContainer.innerHTML = "";
    (currentQuestion.options || []).forEach(option => {
        const button = document.createElement('button');
        button.textContent = option;
        button.className = 'option';
        button.onclick = () => checkAnswer(option);
        optionsContainer.appendChild(button);
    });

    document.getElementById('current-question').textContent = currentQuestionIndex + 1;
}

function checkAnswer(selectedOption) {
    const currentQuestion = questions[currentQuestionIndex];
    
    if(currentQuestion.correctAnswer === selectedOption){
        correctAnswers++;
    }

    nextQuestion();
}

function nextQuestion(){
    if(currentQuestionIndex < questions.length-1){
        currentQuestionIndex++;
        displayQuestion();
    }
    else{
        localStorage.setItem('quizScore',correctAnswers);
        localStorage.setItem('totalQuestions',totalQuestions);
        window.location.href = 'result.html';
    }
}

window.onload = fetchQuestion();