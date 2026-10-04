import DisplayFlag from './guess-flag.js'
const STATE = 'Georgia';

/**
 * Gets a sovereign state from the database.
 * @returns the DisplayFlag component with the flag of the sovereign state.
 */ 
export default async function GetFlag() {
    const state = await fetch(`http://localhost:8080/sovereignState/${STATE}`);
    const state_content = await state.json();
    const url = state_content.flagUrl;

    return <DisplayFlag flag_url={url} />
}