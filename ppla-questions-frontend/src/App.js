import React, {useState, useEffect} from 'react';

// Utility function to shuffle array
const shuffleArray = (array) => {
    let newArray = array.slice();
    for (let i = newArray.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [newArray[i], newArray[j]] = [newArray[j], newArray[i]];
    }
    return newArray;
};

// Utility function to shuffle array
const shuffleAnswers = (array) => {
    let mappedAnswers = array.slice().map(function (val, index) {
        return { sourceIdx: index, value: val };
    });

    return shuffleArray(mappedAnswers);
};


const App = () => {
    const [question, setQuestion] = useState(null);
    const [shuffledOptions, setShuffledOptions] = useState([]);
    const [selectedOption, setSelectedOption] = useState(null);
    const [showAnswer, setShowAnswer] = useState(false);
    const [error, setError] = useState(null);

    const fetchQuestion = async () => {
        try {
            const response = await fetch('/api/question/random');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            setQuestion(data);
            setShuffledOptions(shuffleAnswers(data.options));
            setSelectedOption(null);
            setShowAnswer(false);
        } catch (error) {
            setError(error.message);
            console.error(error)
        }
    };

    useEffect(() => {
        fetchQuestion();
    }, []);

    const handleOptionClick = (index) => {
        if (!showAnswer) {
            setSelectedOption(index);
            setShowAnswer(true);
        }
    };

    const getOptionClass = (index) => {
        if (showAnswer && question.answers.includes(index)) return 'list-group-item-success';
        if (index === selectedOption && question.answers.includes(index)) return 'list-group-item-success';
        if (index === selectedOption && !question.answers.includes(index)) return 'list-group-item-danger';
        return '';
    };

    return (
        <div className="container mt-3 mb-3">
            {error && (
                <div className="alert alert-danger" role="alert">{error}</div>
            )}
            {question && (
                <div>
                    <div className="card">
                        <h5 className="card-header">{question.qid} {question.incorrect &&
                            <i className="bi bi-exclamation-triangle text-danger"></i>}
                        </h5>
                        <div className="card-body">
                        <p className="card-text">{question.question}</p>

                            <ul className="list-group">
                                {shuffledOptions.map(option => (
                                    <li
                                        key={option.sourceIdx}
                                        className={`list-group-item ${getOptionClass(option.sourceIdx)}`}
                                        onClick={() => handleOptionClick(option.sourceIdx)}
                                        style={{cursor: showAnswer ? 'default' : 'pointer'}}
                                    >
                                        <i className="bi bi-play-fill"></i> {option.value}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    </div>
                    <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button className="btn btn-secondary mt-3" onClick={fetchQuestion}>Next</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default App;
