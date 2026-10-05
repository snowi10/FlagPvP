import DisplayFlag from './guess-flag.js'
const STATE = 'Ethiopia';

/**
 * Gets a sovereign state from the database.
 * @returns the DisplayFlag component with the flag of the sovereign state.
 */ 
export default async function GetFlag() {
    const state = await fetch(`http://${process.env.BACKEND}/sovereignState/${STATE}`);
    const content = await state.json();

    return <DisplayFlag state_content={content} />
}