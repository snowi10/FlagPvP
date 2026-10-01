import styles from './flag.module.css'
const STATE = 'Georgia';

async function GetState() {
    const state = await fetch(`http://localhost:8080/sovereignState/${STATE}`);
    const state_content = await state.json();
    const image = state_content.flagImage; 

    return (
        <div id={styles.flag_container}>
            <img id={styles.flag} src={image}></img>
        </div>
    ) 
}

export default function DisplayFlag() {
    return (
        <div>
            <GetState/> 
        </div>
    )
}