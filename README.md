# Condominuium Exercise:
Scrivere una classe che definisce un oggetto di tipo "Proprietario" con i seguenti attributi:
- ID autoincrementale
- Nome
- Cognome


"unità immobiliare" con i seguenti attributi:
- ID autoincrementale
- ID_Proprietario
- numero vani
- mq
- millesimi (calcolati in base ai mq rispetto al totale)
- numeroPersone
- consumoAcqua
- consumoRiscaldamento (espresso in ore)


E i seguenti metodi:
- Doccia (aumenta il consumo dell'acqua di 50Litri)
- Riscaldo (aumenta il consumoRiscaldamento) 
- AproFinestre (aumenta il consumo riscaldamento)


Attraverso un ciclo che si ripete 365 volte modificare gli oggetti
richiamando i suoi metodi con le seguenti frequenze:
- Ogni persona fa la doccia da 1 a 7 volte a settimana
- Riscaldo (7 mesi all'anno e da 6 a 10 ore al giorno)
- Finestre da 1 a 5 volte al giorno 


Nel nostro condominio ci sono 10 appartamenti con un numero di occupanti da 1 a 5 per appartamento. La spesa per il riscaldamento è di 4 euro all'ora. La spesa per l'acqua è di 1 euro ogni 100 litri.

Per ogni appartamento calcolare le spese annuali sapendo che il costo totale del riscaldamento viene suddiviso per metà in base ai millesimi e l'altra metà in base alle ore consumate da ogni unità immobiliare.
