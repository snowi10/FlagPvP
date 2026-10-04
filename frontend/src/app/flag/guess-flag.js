'use client'

import styles from './flag.module.css';
import { useState } from 'react';

/**
 * Input for the user to guess the flag.
 * @returns an input form.
 */
function GuessInput() {
    return (
        // TODO: Have someone design the "Guess the flag!" label.
        <form id={styles.guess_form}>
            <label id="guess_label">Guess the flag!</label><br></br>
            <input id={styles.guess_input} type="text"></input><br></br>
            <button id={styles.guess_check} type="submit">Check</button>
        </form>
    )
}

/**
 * Displays the screen where users guess the current flag. 
 * @param {string} { flag } - The URL of the of image of the flag. 
 * @returns the GetFlag component and the GuessInput component.
 */
export default function DisplayFlag({ flag_url }) {

    // TODO: Create a form for choosing the regions first.
    function getRandomFlag( {regions} ) {

    }

    return (
        <div id={styles.flag_container}>
            <img id={styles.flag} src={flag_url}></img>
            <GuessInput/>
        </div>
    )
}