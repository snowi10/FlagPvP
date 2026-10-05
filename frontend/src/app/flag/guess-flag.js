'use client'

import styles from './flag.module.css';
import { useState } from 'react';

/**
 * Displays the screen where users guess the current flag. 
 * 
 * @param {object} param0 - an object with the sovereign state information. 
 * @returns the GetFlag component and the GuessInput component.
 */
export default function DisplayFlag({ state_content }) {
    const [answer, setAnswer] = useState('');
    const [status, setStatus] = useState('not answered');

    const flag_url = state_content.flagUrl;
    const name = state_content.name;

    if (status === 'answered') {
        let state = answer.toLowerCase().trim() === name.toLowerCase() ? 'Correct!' : 'Incorrect...';

        return ( 
            <>  
                <h1 id={styles.answer}>{state}</h1>
                <h1 id={styles.answer}>Your answer: {answer}</h1>
                <h1 id={styles.answer}>Correct answer: {name}</h1>
            </>);

    }
   
    function handleSubmit(e) {
        e.preventDefault();
        setStatus('answered');
    }

    function handleChange(e) {
        setAnswer(e.target.value);
    }

    return (
        <div id={styles.flag_container}>
            <img id={styles.flag} src={flag_url}></img>
            <form id={styles.guess_form} onSubmit={handleSubmit}>
                <label>Guess the flag!</label><br></br>
                <input id={styles.guess_input} type="text" value={answer} onChange={handleChange}></input><br></br>
                <button id={styles.guess_check} type="submit">Check</button>
            </form>
        </div>
    )
}