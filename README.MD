all'interno della cartella resources ho creato una cartella certs e al suo interno c'è un certificato di una chiave privata

per generare questa chiave privata bisogna aprire il terminale all'interno della cartella e digitare i seguenti comandi :

openssl genrsa -out keypair.pem 2048

dopo aver creato questa chiave private possiamo estrarre da essa una chiave pubblica per farlo bisogna aprire il terminale 
sempre nella stessa cartella e digitare il seguente comando:

openssl rsa -in keypair.pem -pubout -out public.pem

in fine decodifico il formato della chiave privata in pkcs8 come richiesto dal programma per farlo apro sempre 
il terminale nella cartella certs (sta per certificati) e digito il seguente comando:

openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem

QUESTO è IL METODO USATO SOLITAMENTE ALLA LUXOR OVVERO LA RICHIESTA DEL TOKEN AVVIENE TRAMITE UNA CHIAMATA CON USER E PASSWORD 
STANDARD, LA CHIAMATA SOLITAMENTE SI FA NELLA PRIMA SCHERMATA, DOPO DI CHE IL TOKEN RICEVUTO VIENE SALVATO E UTILIZZATO PASSATO
NELLE CHIAMATE COME BEARER.

PER FARE LA PRIMA CHIAMATA PER RICHIEDERE IL TOKEN DA FRONT END TRAMITE AXIOS PRIMA BISOGNA CODIFICARE USER E PASSWORD 
IN BASE 64 COME L'ESEMPIO CHE SEGUE:

// Codifica le credenziali in Base64
const username = 'nico';
const password = 'admin';
const auth = Buffer.from(`${username}:${password}`).toString('base64');

// Configura la richiesta con Basic Auth
let config = {
  method: 'post',
  maxBodyLength: Infinity,
  url: 'http://localhost:8080/token',
  headers: {
    'Authorization': `Basic ${auth}` // Aggiunge l'header di Basic Auth
  }
};

async function makeRequest() {
  try {
    const response = await axios.request(config);
    console.log(JSON.stringify(response.data));
  }
  catch (error) {
    console.log(error);
  }
}

makeRequest();